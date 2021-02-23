package com.crejo.moviereviews.model;

import com.crejo.moviereviews.service.movie.MovieGenre;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Movie {
    private String movieTitle;
    private Integer releaseYear;
    private List<MovieGenre> movieGenres;
    private List<Review> reviews;

    public Movie(String title, Integer releaseYear, List<MovieGenre> movieGenre, List<Review> reviews) {
        this.movieTitle = title;
        this.releaseYear = releaseYear;
        this.movieGenres = movieGenre;
        this.reviews = reviews;
    }

    public static class MovieBuilder {
        private String title;
        private Integer releaseYear;
        private List<MovieGenre> movieGenres;
        private List<Review> reviews;

        public MovieBuilder() {
            this.movieGenres = new ArrayList<>();
            this.reviews = new ArrayList<>();
        }

        public MovieBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder withReleaseDate(Integer releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public MovieBuilder withReview(Review review) {
            this.reviews.add(review);
            return this;
        }

        public MovieBuilder withGenre(MovieGenre movieGenre) {
            this.movieGenres.add(movieGenre);
            return this;
        }

        public MovieBuilder withGenreList(List<MovieGenre> movieGenres) {
            this.movieGenres.addAll(movieGenres);
            return this;
        }

        public Movie build() {
            return new Movie(title, releaseYear, movieGenres, reviews);
        }

    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }

    public List<MovieGenre> getMovieGenres() {
        return movieGenres;
    }

    public void setMovieGenres(List<MovieGenre> movieGenres) {
        this.movieGenres = movieGenres;
    }


}
