package com.crejo.moviereviews.exceptions;

public class UnreleasedException extends RuntimeException {
    public UnreleasedException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }
}
