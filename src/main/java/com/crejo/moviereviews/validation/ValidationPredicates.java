package com.crejo.moviereviews.validation;

import com.crejo.moviereviews.model.Movie;
import com.crejo.moviereviews.model.User;

import java.time.LocalDateTime;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ValidationPredicates {
    public static final BiPredicate<Movie, User> multipleReviewPredicate = (movie, user) ->
            movie.getReviews().stream().anyMatch(review -> review.getUser().getName().equals(user.getName()));

    public static final Predicate<Movie> unreleasedMoviePredicate = movie ->
            LocalDateTime.now().getYear() < movie.getReleaseYear();

}
