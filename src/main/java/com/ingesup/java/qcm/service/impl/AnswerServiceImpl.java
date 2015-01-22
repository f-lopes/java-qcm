package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Answer;
import com.ingesup.java.qcm.repository.AnswerRepository;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lopes_f on 1/22/2015.
 * <florian.lopes@outlook.com>
 */
public class AnswerServiceImpl extends BaseServiceImpl<Answer, String> implements AnswerService {

	private AnswerRepository answerRepository;

	@Autowired
	public AnswerServiceImpl(AnswerRepository answerRepository) {
		this.answerRepository = answerRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return answerRepository;
	}
}
