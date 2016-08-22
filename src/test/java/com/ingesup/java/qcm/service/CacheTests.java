package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(value = "test")
@SpringBootTest
@Slf4j
public class CacheTests {

	@Autowired
	private UserService userService;

	// Specify bean name to override Spring Data Jpa one
	@MockBean(name = "userRepository")
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
		Mockito.when(this.userRepository.findAll()).then(new Answer<List<User>>() {
			@Override
			public List<User> answer(InvocationOnMock invocationOnMock) throws Throwable {
				log.info("Retrieving users from database ...");
				Thread.sleep(5000);
				return new ArrayList<>();
			}
		});
	}

	@Test
	public void shouldCacheCallsToUserServiceGetAll() {
	    final long startTime = LocalDateTime.now().getSecond();
		log.info("Retrieving users for the first time");
		List<User> users = this.userService.getAll();
        long durationInSeconds = LocalTime.now().minusSeconds(startTime).getSecond();
		log.info("Retrieved users, took {}s", durationInSeconds);
		Mockito.verify(this.userRepository).findAll();

        final long secondStartTime = LocalDateTime.now().getSecond();
        log.info("Retrieving users for the second time");
        List<User> cachedUsers = this.userService.getAll();
        long secondDurationInSeconds = LocalTime.now().minusSeconds(secondStartTime).getSecond();
        log.info("Retrieved users, took {}s", secondDurationInSeconds);

        Mockito.verifyNoMoreInteractions(this.userRepository);
        assertTrue(secondDurationInSeconds < 5);
		assertEquals(users, cachedUsers);
	}
}
