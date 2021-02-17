package com.crejo.moviereviews.exceptions;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public MovieNotFoundException(String errorMessage) {
        super(errorMessage);
    }


}
