package com.crejo.moviereviews.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public UserNotFoundException(String errorMessage) {
        super(errorMessage);
    }


}
