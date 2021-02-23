package com.crejo.moviereviews.service.review;

import com.crejo.moviereviews.exceptions.MovieNotFoundException;
import com.crejo.moviereviews.exceptions.MovieUnreleasedException;
import com.crejo.moviereviews.exceptions.MultipleUserReviewException;
import com.crejo.moviereviews.exceptions.UserNotFoundException;
import com.crejo.moviereviews.model.Review;
import com.crejo.moviereviews.service.movie.MovieRepository;
import com.crejo.moviereviews.service.user.UserRepository;
import com.crejo.moviereviews.service.user.UserType;
import com.crejo.moviereviews.validation.ValidationPredicates;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ReviewServiceImpl implements ReviewService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final ReviewAdditionObserver reviewAdditionObserver;

    @Inject
    public ReviewServiceImpl(MovieRepository movieRepository,
                             UserRepository userRepository,
                             ReviewAdditionObserver reviewAdditionObserver) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        this.reviewAdditionObserver = reviewAdditionObserver;
    }


    @Override
    public void addReview(String movieTitle, String userName, Integer movieScore) {
        userRepository.findUser(userName)
                .ifPresentOrElse(user -> {
                    movieRepository.findMovie(movieTitle)
                            .ifPresentOrElse(movie -> {
                                if (ValidationPredicates.multipleReviewPredicate.test(movie, user)) {
                                    throw new MultipleUserReviewException(String.format("Review for %s already exists by %s", movie.getMovieTitle(), user.getName()));
                                }

                                if (ValidationPredicates.unreleasedMoviePredicate.test(movie)) {
                                    throw new MovieUnreleasedException(String.format("%s yet to be released", movieTitle));
                                }
                                Review review = new Review(
                                        user,
                                        user.getUserType().equals(UserType.VIEWER) ? movieScore : movieScore * 2,
                                        user.getUserType()
                                );

                                movie.addReview(review); //Adding the actual review
                                reviewAdditionObserver.notify(user); //Notifying the observer about the published review
                            }, () -> {
                                throw new UserNotFoundException(String.format("%s username not found", userName));
                            });
                }, () -> {
                    throw new MovieNotFoundException(String.format("%s movie not found", movieTitle));
                });
    }
}