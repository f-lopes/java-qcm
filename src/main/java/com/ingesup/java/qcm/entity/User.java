package com.ingesup.java.qcm.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.metamodel.binding.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.InheritanceType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity implements UserDetails {

/*	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator (name = "system-uuid", strategy = "uuid")
	private String id;*/

	protected String firstName;

	protected String lastName;

	protected String email;

	protected String password;

	@ManyToMany (fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "userId"),
			inverseJoinColumns = @JoinColumn(name = "roleId")
	)
	protected Set<Role> roles = new HashSet<>();

	public User() {

	}

	public User(String firstName, String name, String email, String password) {
		this.firstName = firstName;
		this.lastName = name;
		this.email = email;
		this.password = password;
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
}
