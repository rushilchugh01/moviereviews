package com.crejo.moviereviews.service.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@Import({
        UserRepositoryImpl.class,
        UserOnboardingServiceImpl.class
})
@RunWith(SpringRunner.class)
public class UserOnboardingServiceImplTest {
    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private UserOnboardingServiceImpl userOnboardingService;

    public UserOnboardingServiceImplTest() {}

    @Test
    public void testUserAddition() {
        userOnboardingService.addUser("SRK");
        userOnboardingService.addUser("Salman");
        userOnboardingService.addUser("Deepika");

        Assert.assertEquals(userRepository.listUsers().size(), 3);
        Assert.assertEquals(userRepository.listUsers().get(0).getName(), "SRK");
        Assert.assertEquals(userRepository.listUsers().get(1).getName(), "Salman");
        Assert.assertEquals(userRepository.listUsers().get(2).getName(), "Deepika");
    }
}