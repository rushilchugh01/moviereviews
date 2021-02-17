package com.crejo.moviereviews.service.analysis;

import com.crejo.moviereviews.model.Movie;
import com.crejo.moviereviews.service.movie.MovieGenre;

import java.util.List;

public interface AnalysisService {
    List<Movie> topNMovies(MovieGenre movieGenre, Integer n);
    Double averageReviewScoreByYear(Integer year);
    Double averageReviewScoreByMovie(String movieTitle);
}
