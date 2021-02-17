package com.crejo.moviereviews.service.user;

public enum UserType {
    VIEWER("Viewer"),
    CRITIC("Critic");

    private final String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
};
