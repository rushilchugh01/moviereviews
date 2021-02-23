package com.crejo.moviereviews.service.analysis;

import com.crejo.moviereviews.model.Movie;
import com.crejo.moviereviews.service.movie.MovieGenre;
import com.crejo.moviereviews.service.movie.MovieOnboardingServiceImpl;
import com.crejo.moviereviews.service.movie.MovieRepositoryImpl;
import com.crejo.moviereviews.service.review.ReviewAdditionObserverImpl;
import com.crejo.moviereviews.service.review.ReviewServiceImpl;
import com.crejo.moviereviews.service.user.UserOnboardingServiceImpl;
import com.crejo.moviereviews.service.user.UserRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@Import({
        ReviewServiceImpl.class,
        MovieRepositoryImpl.class,
        UserRepositoryImpl.class,
        ReviewAdditionObserverImpl.class,
        UserOnboardingServiceImpl.class,
        MovieOnboardingServiceImpl.class,
        AnalysisServiceImpl.class
})
@RunWith(SpringRunner.class)
public class AnalysisServiceImplTest {
    @Autowired
    private UserOnboardingServiceImpl userOnboardingService;

    @Autowired
    private MovieOnboardingServiceImpl movieOnboardingService;

    @Autowired
    private ReviewServiceImpl reviewService;

    @Autowired
    private MovieRepositoryImpl movieRepository;

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private AnalysisServiceImpl analysisService;

    private ReviewAdditionObserverImpl reviewAdditionObserver;

    public AnalysisServiceImplTest() {
        this.reviewAdditionObserver = new ReviewAdditionObserverImpl(3);
    }

    @Before
    public void setUp() {
        movieOnboardingService.onboardMovie("Don", 2006, new ArrayList<>() {{
            add(MovieGenre.ACTION);
            add(MovieGenre.COMEDY);
        }});
        movieOnboardingService.onboardMovie("Tiger", 2008, MovieGenre.DRAMA);
        movieOnboardingService.onboardMovie("Padmaavat", 2006, MovieGenre.COMEDY);
        movieOnboardingService.onboardMovie("Lunchbox", 2022, MovieGenre.DRAMA);
        movieOnboardingService.onboardMovie("Guru", 2006, MovieGenre.DRAMA);
        movieOnboardingService.onboardMovie("Metro", 2006, MovieGenre.ROMANCE);

        userOnboardingService.addUser("SRK");
        userOnboardingService.addUser("Salman");
        userOnboardingService.addUser("Deepika");

        reviewService.addReview("SRK", "Don", 2);
        reviewService.addReview("SRK", "Padmaavat", 8);
        reviewService.addReview("Salman", "Don", 5);
        reviewService.addReview("Deepika", "Don", 9);
        reviewService.addReview("Deepika", "Guru", 6);

        reviewService.addReview("SRK", "Tiger", 5);
        reviewService.addReview("SRK", "Metro", 7);
    }

    @Test
    public void testTopN() {
        Assert.assertEquals(
                analysisService.topNMovies(MovieGenre.COMEDY, 2).stream().map(Movie::getMovieTitle).collect(Collectors.toList()),
                new ArrayList<>() {{
                    add("Don");
                    add("Padmaavat");
                }}
        );
    }
}