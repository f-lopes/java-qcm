package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Course;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
public interface CourseService extends BaseService<Course, String> {

	@Cacheable("coursesCache")
	@Override
	List<Course> getAll();

	@Cacheable("courseCache")
	@Override
	Course get(String primaryKey);
}
