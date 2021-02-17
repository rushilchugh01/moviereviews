package com.crejo.moviereviews.model;

import java.time.ZonedDateTime;
import java.util.List;

public class Movie {
    private String movieTitle;
    private Integer releaseYear;
    private List<Review> reviews;

    public Movie(String title, Integer releaseYear, List<Review> reviews) {
        this.movieTitle = title;
        this.releaseYear = releaseYear;
        this.reviews = reviews;
    }

    public static class MovieBuilder {
        private String title;
        private Integer releaseYear;
        private List<Review> reviews;

        public MovieBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public MovieBuilder withReleaseDate(Integer releaseDate) {
            this.releaseYear = releaseYear;
            return this;
        }

        public MovieBuilder withReview(Review review) {
            this.reviews.add(review);
            return this;
        }

        public Movie build() {
            return new Movie(title, releaseYear, reviews);
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
}
