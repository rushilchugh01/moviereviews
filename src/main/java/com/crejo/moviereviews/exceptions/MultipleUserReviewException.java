package com.crejo.moviereviews.exceptions;

public class MultipleUserReviewException extends RuntimeException {
    public MultipleUserReviewException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public MultipleUserReviewException(String errorMessage) {
        super(errorMessage);
    }

}
