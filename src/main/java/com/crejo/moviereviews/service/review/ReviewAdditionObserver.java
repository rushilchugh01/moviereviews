package com.crejo.moviereviews.service.review;

import com.crejo.moviereviews.model.User;

public interface ReviewAdditionObserver {
    void notify(User user);
}
