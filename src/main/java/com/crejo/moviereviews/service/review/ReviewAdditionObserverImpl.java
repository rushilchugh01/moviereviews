package com.crejo.moviereviews.service.review;

import com.crejo.moviereviews.model.User;
import com.crejo.moviereviews.service.user.UserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ReviewAdditionObserverImpl implements ReviewAdditionObserver {

    private Map<User, Integer> userReviewsMap;
    private final Integer criticalNumberVal;

    public ReviewAdditionObserverImpl(
            @Value("${user.reviews.number.critic:3}") Integer criticalNumberVal) {
        this.criticalNumberVal = criticalNumberVal;
        userReviewsMap = new HashMap<>();
    }


    @Override
    public void notify(User user) {
        if (!userReviewsMap.containsKey(user)) {
            userReviewsMap.put(user, 0);
        }

        userReviewsMap.compute(user, (k, v) -> v+1);

        if (userReviewsMap.get(user) >= criticalNumberVal &&
                !user.getUserType().equals(UserType.CRITIC)) {
            user.setUserType(UserType.CRITIC);
        }
    }
}
