package com.ingesup.java.qcm.entity;

import org.dom4j.tree.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "course")
public class Course extends AbstractEntity {

	private String name;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}
