package com.ingesup.java.qcm.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "student_id", referencedColumnName = "id")
public class Student extends User {

	@ManyToOne
	private Grade grade;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private List<EvaluationStudent> evaluations;

	public Student() {
	}

	public Student(String studentName, String studentFirstname, String email) {
		super(studentName, studentFirstname, email);
	}

	public Student(String firstName, String name, String email, String password) {
		super(firstName, name, email, password);
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public List<EvaluationStudent> getEvaluations() {
		return evaluations;
	}

	public void setEvaluations(List<EvaluationStudent> evaluations) {
		this.evaluations = evaluations;
	}
}