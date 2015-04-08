package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.TeacherRepository;
import com.ingesup.java.qcm.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 1/22/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, String> implements TeacherService {

	private final TeacherRepository teacherRepository;

	@Autowired
	public TeacherServiceImpl(TeacherRepository teacherRepository) {
		this.teacherRepository = teacherRepository;
	}

	@Override
	public BaseRepository<Teacher, String> getRepository() {
		return teacherRepository;
	}
}
