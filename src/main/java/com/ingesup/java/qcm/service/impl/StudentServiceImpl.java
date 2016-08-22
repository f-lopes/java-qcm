package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.StudentRepository;
import com.ingesup.java.qcm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lopes_f on 21/01/2015.
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, String> implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public BaseRepository<Student, String> getRepository() {
        return studentRepository;
    }

	@Override
	public List<Student> getStudentsByGrade(Grade grade) {
		return studentRepository.findByGrade(grade);
	}

	@Cacheable("students")
    @Override
    public List<Student> getAll() {
        return super.getAll();
    }

    @CacheEvict("students")
    @Override
    public Student add(Student entity) {
        return super.add(entity);
    }
}
