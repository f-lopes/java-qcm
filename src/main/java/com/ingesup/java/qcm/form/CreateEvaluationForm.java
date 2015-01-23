package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Course;
import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Qcm;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public class CreateEvaluationForm {

	@NotEmpty
	private String evaluationName;

	@NotEmpty
	private Date startDate;

	@NotEmpty
	private Date endDate;

	@NotEmpty
	private Qcm evaluationQcm;

	@NotEmpty
	private Grade grade;

	@NotEmpty
	private Course course;

	@NotEmpty
	private String teacherId;

	public CreateEvaluationForm(String teacherId) {
		this.teacherId = teacherId;
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

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}
}
