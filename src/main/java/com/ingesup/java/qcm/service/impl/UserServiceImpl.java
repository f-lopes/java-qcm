package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Role;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.RoleRepository;
import com.ingesup.java.qcm.repository.UserRepository;
import com.ingesup.java.qcm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public BaseRepository<User, String> getRepository() {
		return userRepository;
	}

	@Override
	public User add(User entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		entity.setRoles(getDbRoles(entity.getRoles()));

		return super.add(entity);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userToFound = userRepository.findByEmail(username);

		if (userToFound == null) {
			throw new UsernameNotFoundException(String.format("User with email %s not found.", username));
		}

		return userToFound;
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllNonAdminUsers() {
		List<User> users = getAll();
		List<User> nonAdminUsers = new ArrayList<>();

		for (User user : users) {
			if (!user.isAdmin()) {
				nonAdminUsers.add(user);
			}
		}

		return nonAdminUsers;
	}

	private Set<Role> getDbRoles(Set<Role> roles) {
		Set<Role> dbRoles = new HashSet<>();

		for (Role role : roles) {
			dbRoles.add(roleRepository.findByName(role.getName()));
		}

		return dbRoles;
	}

	@Cacheable("users")
	@Override
	public List<User> getAll() {
		return (List<User>) userRepository.findAll();
	}
}
