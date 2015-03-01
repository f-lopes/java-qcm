package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.User;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface UserService extends BaseService<User, String> {

	@Cacheable("usersCache")
	@Override
	List<User> getAll();

	public List<User> getAllNonAdminUsers();

	public User getByEmail(String email);
}
