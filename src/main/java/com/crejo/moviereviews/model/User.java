package com.crejo.moviereviews.model;

import com.crejo.moviereviews.service.user.UserType;

import java.util.UUID;

public class User {
    public String name;
    public UserType userType;

    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;

    }
}
