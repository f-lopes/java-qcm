package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Course;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lopes_f on 1/24/2015.
 * <florian.lopes@outlook.com>
 */
public class AddCourseForm {

	@NotEmpty
	private String name;
	private Course course;

	public AddCourseForm() {
	}

	public AddCourseForm(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Course getCourse() {
		return new Course(this.name);
	}
}
