package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.EvaluationRepository;
import com.ingesup.java.qcm.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public class EvaluationServiceImpl extends BaseServiceImpl<Evaluation, String> implements EvaluationService {

	private EvaluationRepository evaluationRepository;

	@Autowired
	public EvaluationServiceImpl(EvaluationRepository evaluationRepository) {
		this.evaluationRepository = evaluationRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return evaluationRepository;
	}
}
