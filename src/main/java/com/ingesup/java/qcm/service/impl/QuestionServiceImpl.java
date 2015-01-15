package com.ingesup.java.qcm.service.impl;

import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.repository.BaseRepository;
import com.ingesup.java.qcm.repository.QuestionRepository;
import com.ingesup.java.qcm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class QuestionServiceImpl extends BaseServiceImpl<Question, String> implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public BaseRepository getRepository() {
		return questionRepository;
	}
}
