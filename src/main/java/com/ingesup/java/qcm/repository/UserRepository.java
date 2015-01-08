package com.ingesup.java.qcm.repository;

import com.ingesup.java.qcm.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface UserRepository extends CrudRepository<User, String> {

	public User findByEmail(String email);
}
