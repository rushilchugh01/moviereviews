package com.crejo.moviereviews.service.analysis;

import com.crejo.moviereviews.exceptions.MovieNotFoundException;
import com.crejo.moviereviews.exceptions.NoMoviesReleasedException;
import com.crejo.moviereviews.model.Movie;
import com.crejo.moviereviews.model.Review;
import com.crejo.moviereviews.service.movie.MovieGenre;
import com.crejo.moviereviews.service.movie.MovieRepository;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class AnalysisServiceImpl implements AnalysisService {

    private MovieRepository movieRepository;

    public AnalysisServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> topNMovies(MovieGenre movieGenre, Integer n) {
        return movieRepository.listMovies().stream().filter(movie -> movie.getMovieGenres().contains(movieGenre))
                .sorted(Comparator.comparingInt(this::sumOfReviews).reversed())
                .limit(n)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Double averageReviewScoreByYear(Integer year) {
        Stream<Movie> movieStream = movieRepository.listMovies().stream().filter(movie -> movie.getReleaseYear().equals(year));
        if (movieStream.count() == 0) {
            throw new NoMoviesReleasedException(String.format("No movies released in year %s", year));
        }
        return movieStream
                .map(this::sumOfReviews)
                .mapToInt(i -> i)
                .average().getAsDouble()
                / movieStream.count();
    }

    @Override
    public Double averageReviewScoreByMovie(String movieTitle) {
        return movieRepository.findMovie(movieTitle)
                .map(movie -> this.sumOfReviews(movie) * 1.0 / movie.getReviews().size())
                .orElseThrow(() -> new MovieNotFoundException(String.format("%s movie not found", movieTitle)));
    }

    private Integer sumOfReviews(Movie movie) {
        return movie.getReviews().stream().map(Review::getScore).mapToInt(Integer::intValue).sum();
    }
}
