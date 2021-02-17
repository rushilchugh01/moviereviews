package com.crejo.moviereviews.service.movie;

import com.crejo.moviereviews.model.Review;

import java.time.ZonedDateTime;
import java.util.List;

public interface MovieOnboardingService {
    void onboardMovie(String title, Integer releaseYear, MovieGenre movieGenre);
}
