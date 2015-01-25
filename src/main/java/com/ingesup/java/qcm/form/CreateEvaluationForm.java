package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.builder.EvaluationBuilder;
import com.ingesup.java.qcm.entity.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public class CreateEvaluationForm {

	@NotNull
	private String evaluationName;

	@NotNull
	private Date startDate;

	@NotNull
	private Date endDate;

	@NotNull
	private Qcm evaluationQcm;

	@NotNull
	private Grade grade;

	@NotNull
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
		Evaluation evaluation = new EvaluationBuilder()
				.startDate(startDate)
				.endDate(endDate)
				.grade(grade)
				.course(course).build();

		return evaluation;
	}
}
