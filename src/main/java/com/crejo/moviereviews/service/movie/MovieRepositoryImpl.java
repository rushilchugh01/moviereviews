package com.crejo.moviereviews.service.movie;

import com.crejo.moviereviews.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieRepositoryImpl implements MovieRepository {

    private List<Movie> movieList;
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    public MovieRepositoryImpl() {
        this.movieList = new ArrayList<>();
    }

    @Override
    public void addMovies(List<Movie> movies) {
        movieList.addAll(movies);
    }

    @Override
    public void removeMovies(List<Movie> movies) {
        movies.removeAll(movies);
    }

    @Override
    public Optional<Movie> findMovie(String movieTitle) {
        return movieList.stream().filter(movie -> movie.getMovieTitle().equals(movieTitle)).findFirst();
    }

    @Override
    public List<Movie> listMovies() {
        return movieList;
    }
}
