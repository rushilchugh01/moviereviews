package com.crejo.moviereviews.service.review;

import com.crejo.moviereviews.model.User;
import com.crejo.moviereviews.service.user.UserType;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class ReviewAdditionObserverImpl implements ReviewAdditionObserver {

    private Map<User, Integer> userReviewsMap;

    @Value("${user.reviews.number.critic:3}")
    private Integer criticNumberValue;

    @Override
    public void notify(User user) {
        userReviewsMap.putIfAbsent(user, 1);
        userReviewsMap.computeIfPresent(user, (k, v) -> v+1);

        if (userReviewsMap.get(user) >= criticNumberValue &&
                !user.getUserType().equals(UserType.CRITIC)) {
            user.setUserType(UserType.CRITIC);
        }
    }
}
