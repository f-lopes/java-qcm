package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.JavaQcmApplication;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lopes_f on 21/01/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(value = "test")
@SpringApplicationConfiguration(classes = JavaQcmApplication.class)
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    private User user;

    @Before
    public void setUp() {
        this.user = studentRepository.save(new User("firstname", "name", "email@email.com", "password"));
    }

    @Test
    public void shouldRetrieveStudent() {
        Student student = studentRepository.findByEmail("email@email.com");
    }
}
