package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.RepositoryTest;
import com.ingesup.java.qcm.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by lopes_f on 1/21/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringRunner.class)
@RepositoryTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() {
        this.user = userRepository.save(new User("firstname", "name", "email@email.com", "password"));
    }

    @Test
    public void shouldRetrieveUser() {
        User user = userRepository.findByEmail("email@email.com");
        Assert.assertEquals(user.getEmail(), this.user.getEmail());
    }
}
