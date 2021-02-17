package com.crejo.moviereviews.service.review;

import com.crejo.moviereviews.exceptions.MovieNotFoundException;
import com.crejo.moviereviews.exceptions.MovieUnreleasedException;
import com.crejo.moviereviews.exceptions.MultipleUserReviewException;
import com.crejo.moviereviews.exceptions.UserNotFoundException;
import com.crejo.moviereviews.model.Review;
import com.crejo.moviereviews.service.movie.MovieRepository;
import com.crejo.moviereviews.service.user.UserRepository;
import com.crejo.moviereviews.validation.ValidationPredicates;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component
public class ReviewServiceImpl implements ReviewService {

    private final MovieRepository movieRepository;
    private final UserRepository userRepository;


    @Inject
    public ReviewServiceImpl(MovieRepository movieRepository,
                             UserRepository userRepository) {
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void addReview(String movieTitle, String userName, Double movieScore) {
        userRepository.findUser(userName)
                .ifPresentOrElse(user -> {
                    movieRepository.findMovie(movieTitle)
                            .ifPresentOrElse(movie -> {
                                Review review = new Review(user, movieScore, user.getUserType());
                                if (ValidationPredicates.multipleReviewPredicate.test(movie, user)) {
                                    throw new MultipleUserReviewException(String.format("Review for %s already exists by %s", movie.getMovieTitle(), user.getName()));
                                }

                                if (ValidationPredicates.unreleasedMoviePredicate.test(movie)) {
                                    throw new MovieUnreleasedException(String.format("%s yet to be released", movieTitle));
                                }

                                movie.addReview(review); //Adding the actual review

                            }, () -> {
                                throw new UserNotFoundException(String.format("%s username not found", userName));
                            });
                }, () -> {
                    throw new MovieNotFoundException(String.format("%s movie not found", movieTitle));
                });
    }
}