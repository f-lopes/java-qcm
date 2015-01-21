package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.service.StudentService;

import java.util.List;

/**
 * Created by flopes on 21/01/2015.
 */
public class StudentServiceImpl implements StudentService {
    @Override
    public List<User> getAllNonAdminUsers() {
        return null;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void add(User entity) {

    }

    @Override
    public User get(String primaryKey) {
        return null;
    }

    @Override
    public BaseRepository getRepository() {
        return null;
    }
}
