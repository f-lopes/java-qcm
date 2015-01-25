package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.Teacher;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class AddUserForm {

	private String firstName;

	private String name;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String password;

	private Grade grade;

	private String userType;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Student getStudent() {
		Student student = new Student(firstName, name, email, password);
		student.setGrade(grade);

		return student;
	}

	public Teacher getTeacher() {
		Teacher teacher = new Teacher(firstName, name, email, password);
		return teacher;
	}
}
