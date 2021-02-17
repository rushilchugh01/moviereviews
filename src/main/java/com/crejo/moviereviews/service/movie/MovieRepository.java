package com.crejo.moviereviews.service.movie;

import com.crejo.moviereviews.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    void addMovies(List<Movie> movies);
    void removeMovies(List<Movie> movies);
    Optional<Movie> findMovie(String movieTitle);
    List<Movie> listMovies();

}
