package com.crejo.moviereviews.service.user;

import com.crejo.moviereviews.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void addUsers(List<User> users);
    void removeUsers(List<User> users);
    List<User> listUsers();
    Optional<User> findUser(String userName);
}
