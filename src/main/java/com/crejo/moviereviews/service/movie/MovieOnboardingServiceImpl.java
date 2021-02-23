package com.crejo.moviereviews.service.movie;

import com.crejo.moviereviews.model.Movie;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
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
    public void onboardMovie(String title, Integer releaseYear, MovieGenre movieGenre) {
        Movie movie = new Movie.MovieBuilder()
                .withTitle(title)
                .withReleaseDate(releaseYear)
                .withGenre(movieGenre)
                .build();

        movieRepository.addMovies(Collections.singletonList(movie));
    }

    @Override
    public void onboardMovie(String title, Integer releaseYear, List<MovieGenre> movieGenres){
        Movie movie = new Movie.MovieBuilder()
                .withTitle(title)
                .withReleaseDate(releaseYear)
                .withGenreList(movieGenres)
                .build();

        movieRepository.addMovies(Collections.singletonList(movie));
    }
}
