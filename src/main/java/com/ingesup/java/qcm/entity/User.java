package com.ingesup.java.qcm.entity;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements UserDetails {

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String id;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;

	private String password;

	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "roleId")
	)
	private Set<Role> roles = new HashSet<>();

	public User() {

	}

	public User(String firstName, String name, String email, String password) {
		this.firstName = firstName;
		this.lastName = name;
		this.email = email;
		this.password = password;
	}

	public User(String name, String firstName, String email) {
		this.lastName = name;
		this.firstName = firstName;
		this.email = email;
	}

	public void addRole(RoleEnum roleEnum) {
		if (this.roles == null) {
			this.roles = new HashSet<>();
		}

		this.roles.add(new Role(roleEnum));
	}

	public boolean isAdmin() {
		boolean isAdmin = false;

		for (Role role : roles) {
			if (RoleEnum.ROLE_ADMIN.name().equals(role.getName())) {
				isAdmin = true;
				break;
			}
		}

		return isAdmin;
	}

	public boolean isTeacher() {
		boolean isTeacher = false;

		for (Role role : roles) {
			if (RoleEnum.ROLE_TEACHER.name().equals(role.getName())) {
				isTeacher = true;
				break;
			}
		}

		return isTeacher;
	}

	public boolean isStudent() {
		boolean isStudent = false;

		for (Role role : roles) {
			if (RoleEnum.ROLE_STUDENT.name().equals(role.getName())) {
				isStudent = true;
				break;
			}
		}

		return isStudent;
	}

	public List<String> getRolesAsString(){
		List<String> rolesAsString = new ArrayList<>();

		for (Role role : roles) {
			rolesAsString.add(role.getName());
		}

		return rolesAsString;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String toString() {
		return "User{" +
				"id='" + id + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", roles=" + roles +
				'}';
	}
}
