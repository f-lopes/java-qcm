package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class UserServiceImpl implements UserDetailsService {

	private UserRepository userRepository;

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
}
