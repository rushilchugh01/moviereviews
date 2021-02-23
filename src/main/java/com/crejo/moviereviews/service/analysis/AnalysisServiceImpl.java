package com.crejo.moviereviews.service.analysis;

import com.crejo.moviereviews.model.Movie;
import com.crejo.moviereviews.model.Review;
import com.crejo.moviereviews.service.movie.MovieGenre;
import com.crejo.moviereviews.service.movie.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnalysisServiceImpl implements AnalysisService {

    private MovieRepository movieRepository;

    public AnalysisServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> topNMovies(MovieGenre movieGenre, Integer n) {
        return movieRepository.listMovies().stream().filter(movie -> movie.getMovieGenres().contains(movieGenre))
                .sorted(Comparator.comparingInt(this::sumOfReviews))
                .limit(n)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Double averageReviewScoreByYear(Integer year) {
        return movieRepository.listMovies().stream().filter(movie -> movie.getReleaseYear().equals(year))
                .map(this::sumOfReviews)
                .mapToInt(i -> i)
                .average().getAsDouble();
    }

    @Override
    public Double averageReviewScoreByMovie(String movieTitle) {
        return movieRepository.listMovies().stream().filter(movie -> movie.getMovieTitle().equals(movieTitle))
                .map(this::sumOfReviews)
                .mapToInt(i -> i)
                .average().getAsDouble();
    }

    private Integer sumOfReviews(Movie movie) {
        return movie.getReviews().stream().map(Review::getScore).mapToInt(Integer::intValue).sum();
    }
}
