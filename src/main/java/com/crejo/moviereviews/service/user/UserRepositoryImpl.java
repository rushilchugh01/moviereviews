package com.crejo.moviereviews.service.user;

import com.crejo.moviereviews.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private List<User> userList;

    @Override
    public void addUsers(List<User> users) {
        userList.addAll(users);
    }

    @Override
    public void removeUsers(List<User> users) {
        userList.removeAll(users);
    }

    @Override
    public Optional<User> findUser(String userName) {
        return userList.stream().filter(user -> user.getName().equals(userName)).findFirst();
    }
}
