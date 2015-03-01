package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by lopes_f on 21/01/2015.
 */
public interface StudentService extends BaseService<Student, String> {

	@Cacheable("studentsCache")
	@Override
	List<Student> getAll();

	@CacheEvict(value = "studentsCache", allEntries = true)
	@Override
	Student add(Student entity);

	List<Student> getStudentsByGrade(Grade grade);
}
