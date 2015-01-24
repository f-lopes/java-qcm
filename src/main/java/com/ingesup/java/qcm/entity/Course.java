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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
