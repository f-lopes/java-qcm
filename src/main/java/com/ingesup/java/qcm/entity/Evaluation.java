package com.ingesup.java.qcm.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "evaluation")
public class Evaluation extends BaseEntity {

	private Date startDate;

	private Date endDate;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "evaluation_id")
	private List<EvaluationStudent> students;

	@ManyToOne
	private Grade grade;

	@ManyToOne
	private Qcm qcm;

	public Evaluation() {
	}

	public Evaluation(Date startDate, Date endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
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

	public List<EvaluationStudent> getStudents() {
		return students;
	}

	public void setStudents(List<EvaluationStudent> students) {
		this.students = students;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Qcm getQcm() {
		return qcm;
	}

	public void setQcm(Qcm qcm) {
		this.qcm = qcm;
	}
}
