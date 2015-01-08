package com.ingesup.java.qcm.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "student")
public class Student extends BaseEntity {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}