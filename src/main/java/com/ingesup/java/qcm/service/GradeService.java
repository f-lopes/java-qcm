package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Grade;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * Created by lopes_f on 1/19/2015.
 * <florian.lopes@outlook.com>
 */
public interface GradeService extends BaseService<Grade, String> {

	@Cacheable("gradesCache")
	@Override
	List<Grade> getAll();

	Grade getGradeByName(String name);
}
