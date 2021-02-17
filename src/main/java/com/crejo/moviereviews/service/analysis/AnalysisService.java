package com.crejo.moviereviews.service.analysis;

import com.crejo.moviereviews.model.Movie;

import java.util.List;

public interface AnalysisService {
    List<Movie> topNMovies(Integer n);
    Double averageReviewScoreByYear(Integer year);
    Double averageReviewScoreByMovie(String movieTitle);
}
