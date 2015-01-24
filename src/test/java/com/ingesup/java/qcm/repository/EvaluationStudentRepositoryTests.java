package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.JavaQcmApplication;
import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Student;
import org.junit.Assert;
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
import java.util.List;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith (SpringJUnit4ClassRunner.class)
@ActiveProfiles (value = "test")
@SpringApplicationConfiguration (classes = JavaQcmApplication.class)
public class EvaluationStudentRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EvaluationRepository evaluationRepository;

	@Autowired
	private EvaluationStudentRepository evaluationStudentRepository;

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
	}

	@Test
	public void shouldAddEvaluationStudentEntry() {
		EvaluationStudent evaluationStudent = EvaluationStudent.builder()
				.student(student)
				.evaluation(evaluation)
				.date(new Date())
				.mark(0).build();

		evaluationStudent = evaluationStudentRepository.save(evaluationStudent);

		Assert.assertEquals(evaluationStudent.getStudent().getId(), student.getId());
		Assert.assertEquals(evaluationStudent.getEvaluation().getId(), evaluation.getId());
		Assert.assertEquals(evaluationStudent.getMark(), 0);
	}

	@Test
	public void shouldRetrieveEvaluationsTakenByStudent() {
		EvaluationStudent evaluationStudent = EvaluationStudent.builder()
				.student(student)
				.evaluation(evaluation)
				.date(new Date())
				.mark(0).build();

		evaluationStudent = evaluationStudentRepository.save(evaluationStudent);

		List<EvaluationStudent> takenEvaluationsByStudent = evaluationStudentRepository.findByStudent(student);

		Assert.assertEquals(takenEvaluationsByStudent.size(), 1);
	}
}
