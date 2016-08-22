package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Course;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.CourseRepository;
import com.ingesup.java.qcm.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, String> implements CourseService {

	private final CourseRepository courseRepository;

	@Autowired
	public CourseServiceImpl(CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

	@Override
	public BaseRepository<Course, String> getRepository() {
		return courseRepository;
	}

	@Cacheable("courses")
	@Override
	public List<Course> getAll() {
		return super.getAll();
	}

	@CacheEvict("courses")
	@Override
	public Course add(Course entity) {
		return super.add(entity);
	}
}
