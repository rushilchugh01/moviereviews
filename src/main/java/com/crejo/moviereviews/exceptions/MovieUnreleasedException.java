package com.crejo.moviereviews.exceptions;

public class MovieUnreleasedException extends RuntimeException {
    public MovieUnreleasedException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public MovieUnreleasedException(String errorMessage) {
        super(errorMessage);
    }

}
