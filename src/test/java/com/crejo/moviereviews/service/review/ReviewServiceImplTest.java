package com.crejo.moviereviews.service.review;

import com.crejo.moviereviews.exceptions.MovieUnreleasedException;
import com.crejo.moviereviews.exceptions.MultipleUserReviewException;
import com.crejo.moviereviews.service.movie.MovieGenre;
import com.crejo.moviereviews.service.movie.MovieOnboardingServiceImpl;
import com.crejo.moviereviews.service.movie.MovieRepository;
import com.crejo.moviereviews.service.movie.MovieRepositoryImpl;
import com.crejo.moviereviews.service.user.UserOnboardingServiceImpl;
import com.crejo.moviereviews.service.user.UserRepository;
import com.crejo.moviereviews.service.user.UserRepositoryImpl;
import com.crejo.moviereviews.service.user.UserType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@Import({
        ReviewServiceImpl.class,
        MovieRepositoryImpl.class,
        UserRepositoryImpl.class,
        ReviewAdditionObserverImpl.class,
        UserOnboardingServiceImpl.class,
        MovieOnboardingServiceImpl.class
})
@RunWith(SpringRunner.class)
public class ReviewServiceImplTest {

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

    private ReviewAdditionObserverImpl reviewAdditionObserver;

    public ReviewServiceImplTest() {
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
    }

    @Test(expected = MultipleUserReviewException.class)
    public void reviewAddition_MultipleReviewException() {
        reviewService.addReview("SRK", "Don", 10);
    }

    @Test(expected = MovieUnreleasedException.class)
    public void reviewAddition_MovieUnreleasedException() {
        reviewService.addReview("Deepika", "Lunchbox", 10);
    }

    @Test
    public void reviewAddition_PromotionToCritic() {
        //A "Viewer" prior to publishing the third review
        Assert.assertEquals(userRepository.findUser("SRK").get().getUserType(), UserType.VIEWER);

        reviewService.addReview("SRK", "Tiger", 5);

        //A "Critic" after publishing the third review
        Assert.assertEquals(userRepository.findUser("SRK").get().getUserType(), UserType.CRITIC);
    }


}