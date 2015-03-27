package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.JavaQcmApplication;
import com.ingesup.java.qcm.entity.Student;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ActiveProfiles (value = "test")
@SpringApplicationConfiguration (classes = JavaQcmApplication.class)
@Sql (scripts = "/sql/test-data.sql" )
public class CacheTests {

	@Autowired
	private StudentService studentService;

	@Test
	public void shouldCacheSecondCallToStudentService() {
		List<Student> students = studentService.getAll();
		int nbStudents = students.size();

		List<Student> cacheStudents = studentService.getAll();
		int nbStudentsForSecondCall = cacheStudents.size();

		Assert.assertEquals(nbStudents, nbStudentsForSecondCall);
	}
}
