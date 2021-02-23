package com.crejo.moviereviews.service.movie;

public enum MovieGenre {
    COMEDY("Comedy"),
    DRAMA("Drama"),
    ROMANCE("Romance"),
    ACTION("Action");

    private final String genre;

    MovieGenre(String genre) {
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }
}
