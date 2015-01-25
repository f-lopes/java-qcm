package com.ingesup.java.qcm.database;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lopes_f on 1/25/2015.
 * <florian.lopes@outlook.com>
 */
@ConfigurationProperties(prefix = QcmAdministrationProperties.PREFIX)
public class QcmAdministrationProperties {

	public static final String PREFIX = "qcm.administration";
	public static final char SEPARATOR = ',';

	private String email;

	private String password;

	private String firstname;

	private String lastname;

	private String grades;

	private String courses;

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

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGrades() {
		return grades;
	}

	public void setGrades(String grades) {
		this.grades = grades;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}
}
