package com.crejo.moviereviews.model;

import com.crejo.moviereviews.service.user.UserType;

public class Review {
    private Integer score;
    private User user;
    private UserType userTypeAtReview;

    public Review(User user, Integer score, UserType userTypeAtReview) {
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
