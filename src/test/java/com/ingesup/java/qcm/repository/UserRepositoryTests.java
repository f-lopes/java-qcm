package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.JavaQcmApplication;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by lopes_f on 1/21/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(value = "test")
@SpringApplicationConfiguration(classes = JavaQcmApplication.class)
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;
	private User user;

	@Before
	public void setUp() {
		this.user = userRepository.save(new User("firstname", "name", "email@email.com", "password"));
	}

	@Test
	public void shouldRetrieveStudent() {
		Student student = (Student) userRepository.findByEmail("email@email.com");
	}
}
