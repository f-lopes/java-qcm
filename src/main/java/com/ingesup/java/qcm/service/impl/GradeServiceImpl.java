package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.GradeRepository;
import com.ingesup.java.qcm.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 1/19/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class GradeServiceImpl extends BaseServiceImpl<Grade, String> implements GradeService {

	private GradeRepository gradeRepository;

	@Autowired
	public GradeServiceImpl(GradeRepository gradeRepository) {
		this.gradeRepository = gradeRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return gradeRepository;
	}

	@Override
	public Grade getGradeByName(String name) {
		return gradeRepository.findFirstByName(name);
	}
}
