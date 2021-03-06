package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by lopes_f on 21/01/2015.
 */
@RunWith (SpringRunner.class)
@DataJpaTest
@Sql (scripts = "/sql/test-data.sql" )
@ActiveProfiles("test")
public class StudentRepositoryTests {

    @Autowired
    private StudentRepository studentRepository;

    private Student student;

    @Before
    public void setUp() {
        this.student = studentRepository.save(new Student("firstname", "name", "email@email.com", "password"));
    }

    @Test
    public void shouldRetrieveStudent() {
        Student student = studentRepository.findByEmail("email@email.com");

        assertEquals(this.student.getEmail(), student.getEmail());
    }
}
