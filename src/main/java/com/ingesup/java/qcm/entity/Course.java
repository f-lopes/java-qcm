package com.ingesup.java.qcm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "course")
public class Course extends BaseEntity {

	private String name;

	public Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Course)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		Course course = (Course) o;

		if (name != null ? !name.equals(course.name) : course.name != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}
}
