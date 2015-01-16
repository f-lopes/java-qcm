package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.QuestionRepository;
import com.ingesup.java.qcm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question, String> implements QuestionService {

	private QuestionRepository questionRepository;

	@Autowired
	public QuestionServiceImpl(QuestionRepository questionRepository) {
		this.questionRepository = questionRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return questionRepository;
	}
}
