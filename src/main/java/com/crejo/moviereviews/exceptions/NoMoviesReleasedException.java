package com.crejo.moviereviews.exceptions;

public class NoMoviesReleasedException extends RuntimeException {
    public NoMoviesReleasedException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public NoMoviesReleasedException(String errorMessage) {
        super(errorMessage);
    }

}
