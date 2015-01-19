package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.JavaQcmApplication;
import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

/**
 * Created by lopes_f on 1/17/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(value = "test")
@SpringApplicationConfiguration (classes = JavaQcmApplication.class)
@WebAppConfiguration
public class EvaluationRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EvaluationRepository evaluationRepository;

	@Autowired
	private EvaluationStudentRepository evaluationStudentRepository;

	private Student student;
	private Evaluation evaluation;

	@Before
	public void setUp() {
		this.student = new Student("studentName", "studentFirstname", "student@student.fr");
		this.evaluation = new Evaluation(new Date(), new Date());

		student = userRepository.save(student);
		evaluation = evaluationRepository.save(evaluation);
	}

	@Test
	public void shouldAddEvaluationStudentEntry() {
		EvaluationStudent evaluationStudent = EvaluationStudent.builder()
				.student(student)
				.evaluation(evaluation)
				.date(new Date())
				.mark(0).build();

		evaluationStudent = evaluationStudentRepository.save(evaluationStudent);
	}

}
