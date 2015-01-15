package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.UserRepository;
import com.ingesup.java.qcm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService, UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public BaseRepository getRepository() {
		return userRepository;
	}

	@Override
	public void add(User entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));

		super.add(entity);
	}

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
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

	@Override
	public List<Teacher> getAllTeachers() {
		return null;
	}
}
