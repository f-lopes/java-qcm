package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.User;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface UserRepository extends BaseRepository<User, String> {

	public User findByEmail(String email);
}
