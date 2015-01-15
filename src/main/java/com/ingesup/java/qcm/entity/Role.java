package com.ingesup.java.qcm.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity implements GrantedAuthority {

	@Column(name = "name", length = 20)
	private String name;

	public Role() {

	}

	public Role(RoleEnum roleEnum){
		this.name = roleEnum.name();
	}

	public Role(String roleName) {
		this.name = roleName;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
