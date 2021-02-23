package com.crejo.moviereviews.service.review;

import com.crejo.moviereviews.model.Review;
import com.crejo.moviereviews.model.User;

public interface ReviewService {
    void addReview(String userName, String movieTitle, Integer movieScore);
}
