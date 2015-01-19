package com.ingesup.java.qcm.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "student")
public class Student extends User {

	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private List<EvaluationStudent> evaluations;

	public Student() {
	}

	public Student(String studentName, String studentFirstname, String email) {
		this.lastName = studentName;
		this.firstName = studentFirstname;
		this.email = email;
	}
}