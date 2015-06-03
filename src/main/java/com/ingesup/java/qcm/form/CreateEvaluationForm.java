package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.builder.EvaluationBuilder;
import com.ingesup.java.qcm.entity.Course;
import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Qcm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public class CreateEvaluationForm {

	private static final Logger logger = LoggerFactory.getLogger(CreateEvaluationForm.class);

	private DateFormat dateFormatter;

	private String evaluationName;

	private Date startDate;

	private Date endDate;

	private Qcm evaluationQcm;

	private Grade grade;

	private Course course;

	public CreateEvaluationForm() {
	}

	public String getEvaluationName() {
		return evaluationName;
	}

	public void setEvaluationName(String evaluationName) {
		this.evaluationName = evaluationName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Qcm getEvaluationQcm() {
		return evaluationQcm;
	}

	public void setEvaluationQcm(Qcm evaluationQcm) {
		this.evaluationQcm = evaluationQcm;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Evaluation getEvaluation() {
		return new EvaluationBuilder()
				.startDate(startDate)
				.endDate(endDate)
				.grade(grade)
				.qcm(evaluationQcm)
				.course(course).build();
	}
}
