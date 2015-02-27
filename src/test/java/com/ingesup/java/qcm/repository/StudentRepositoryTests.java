package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.RepositoryTest;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lopes_f on 21/01/2015.
 */
@RepositoryTest
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    private User user;

    @Before
    public void setUp() {
        this.user = studentRepository.save(new Student("firstname", "name", "email@email.com", "password"));
    }

    @Test
    public void shouldRetrieveStudent() {
        Student student = studentRepository.findByEmail("email@email.com");
    }
}
