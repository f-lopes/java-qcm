package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.entity.User;

import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public interface UserService extends BaseService<User, String> {

	public List<User> getAllNonAdminUsers();

	public List<Teacher> getAllTeachers();
}
