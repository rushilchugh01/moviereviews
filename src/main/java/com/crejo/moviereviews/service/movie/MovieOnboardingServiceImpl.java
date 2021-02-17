package com.crejo.moviereviews.service.movie;

import com.crejo.moviereviews.model.Movie;
import com.crejo.moviereviews.model.Review;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;

@Component
public class MovieOnboardingServiceImpl implements MovieOnboardingService {

    private final MovieRepository movieRepository;

    @Inject
    public MovieOnboardingServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void onboardMovie(String title, Integer releaseYear) {
        Movie movie = new Movie.MovieBuilder()
                .withTitle(title)
                .withReleaseDate(releaseYear)
                .build();

        movieRepository.addMovies(Collections.singletonList(movie));

    }
}
