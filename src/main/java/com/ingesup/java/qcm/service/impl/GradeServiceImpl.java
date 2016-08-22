package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.GradeRepository;
import com.ingesup.java.qcm.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lopes_f on 1/19/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class GradeServiceImpl extends BaseServiceImpl<Grade, String> implements GradeService {

	private final GradeRepository gradeRepository;

	@Autowired
	public GradeServiceImpl(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
	}

	@Override
	public BaseRepository<Grade, String> getRepository() {
		return gradeRepository;
	}

	@Override
	public Grade getGradeByName(String name) {
		return gradeRepository.findFirstByName(name);
	}

	@Cacheable("grades")
	@Override
	public List<Grade> getAll() {
		return super.getAll();
	}

	@CacheEvict("grades")
	@Override
	public Grade add(Grade entity) {
		return super.add(entity);
	}
}
