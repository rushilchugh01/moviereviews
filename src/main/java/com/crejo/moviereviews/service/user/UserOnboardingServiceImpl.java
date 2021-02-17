package com.crejo.moviereviews.service.user;

import com.crejo.moviereviews.model.User;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserOnboardingServiceImpl implements UserOnboardingService {

    private final UserRepository userRepository;

    public UserOnboardingServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(String userName) {
        User user = new User(userName, UserType.VIEWER);
        userRepository.addUsers(Collections.singletonList(user));
    }
}
