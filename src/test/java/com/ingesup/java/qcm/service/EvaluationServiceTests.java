package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.JavaQcmApplication;
import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.repository.EvaluationRepository;
import com.ingesup.java.qcm.repository.EvaluationStudentRepository;
import com.ingesup.java.qcm.repository.GradeRepository;
import com.ingesup.java.qcm.repository.UserRepository;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ActiveProfiles (value = "test")
@SpringApplicationConfiguration (classes = JavaQcmApplication.class)
public class EvaluationServiceTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EvaluationRepository evaluationRepository;

	@Autowired
	private EvaluationStudentRepository evaluationStudentRepository;

	@Autowired
	private EvaluationService evaluationService;

	@Autowired
	private GradeRepository gradeRepository;

	private Student student;
	private Evaluation evaluation;
	private Grade grade;

	@Before
	public void setUp() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(2015, 11, 11);

		this.student = new Student("studentName", "studentFirstname", "student@student.fr");
		this.grade = gradeRepository.save(new Grade("B1"));

		Grade b2Grade = new Grade("B2");
		b2Grade = gradeRepository.save(b2Grade);

		this.evaluation = new Evaluation(new Date(), calendar.getTime());
		this.evaluation.setGrade(this.grade);

		calendar.set(2014, 11, 11);
		Evaluation secondEvaluation = new Evaluation();
		secondEvaluation.setStartDate(calendar.getTime());
		calendar.set(2014, 11, 22);
		secondEvaluation.setEndDate(calendar.getTime());
		secondEvaluation.setGrade(grade);

		Evaluation thirdEvaluation = new Evaluation();
		BeanUtils.copyProperties(evaluation, thirdEvaluation);
		thirdEvaluation.setGrade(b2Grade);

		student = userRepository.save(student);
		evaluation = evaluationRepository.save(evaluation);
		secondEvaluation = evaluationRepository.save(secondEvaluation);
		thirdEvaluation = evaluationRepository.save(thirdEvaluation);

		EvaluationStudent evaluationStudent = EvaluationStudent.builder()
				.student(student)
				.evaluation(evaluation)
				.date(new Date())
				.mark(0).build();

		evaluationStudent = evaluationStudentRepository.save(evaluationStudent);
	}

	@Test
	public void shouldReturnIfStudentHasTakenGivenEvaluation() {
		boolean hasStudentTakenEvaluation = evaluationService.hasStudentTakenEvaluation(student.getId(), evaluation.getId());
		Assert.assertTrue(hasStudentTakenEvaluation);
	}

	@Test
	public void shouldTakeEvaluation() {
//		EvaluationStudent evaluationStudent = evaluationService.takeEvaluation()

		Assert.fail("Not implemented");
	}
}
