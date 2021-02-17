package com.crejo.moviereviews.model;

import com.crejo.moviereviews.service.user.UserType;

public class Review {
    private Double score;
    private User user;
    private UserType userTypeAtReview;

    public Review(User user, Double score, UserType userTypeAtReview) {
        this.user = user;
        this.score = score;
        this.userTypeAtReview = userTypeAtReview;
    }

    public UserType getUserTypeAtReview() {
        return userTypeAtReview;
    }

    public void setUserTypeAtReview(UserType userTypeAtReview) {
        this.userTypeAtReview = userTypeAtReview;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
