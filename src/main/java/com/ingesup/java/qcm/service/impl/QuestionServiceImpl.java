package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.QuestionRepository;
import com.ingesup.java.qcm.service.QcmService;
import com.ingesup.java.qcm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question, String> implements QuestionService {

	private QcmService qcmService;

	private QuestionRepository questionRepository;

	@Autowired
	public QuestionServiceImpl(QuestionRepository questionRepository, QcmService qcmService) {
		this.questionRepository = questionRepository;
		this.qcmService = qcmService;
	}

	@Override
	public BaseRepository getRepository() {
		return questionRepository;
	}

	@Override
	public List<Question> getQuestionsByQcmId(String qcmId) {
		Qcm qcm = qcmService.get(qcmId);
		return getQuestionsByQcm(qcm);
	}

	@Override
	public List<Question> getQuestionsByQcm(Qcm qcm) {
		return questionRepository.findByQcm(qcm);
	}
}
