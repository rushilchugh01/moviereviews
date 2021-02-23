package com.crejo.moviereviews.service.movie;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Import({
        MovieRepositoryImpl.class,
        MovieOnboardingServiceImpl.class
})
public class MovieOnboardingServiceImplTest {
    @Autowired
    private MovieRepositoryImpl movieRepository;

    @Autowired
    private MovieOnboardingServiceImpl movieOnboardingService;

    public MovieOnboardingServiceImplTest(){}

    @Test
    public void testMovieAddition() {
        movieOnboardingService.onboardMovie("Tiger", 2008, MovieGenre.DRAMA);
        Assert.assertEquals(movieRepository.listMovies().size(), 1);
        Assert.assertEquals(movieRepository.listMovies().get(0).getMovieTitle(), "Tiger");
    }


}