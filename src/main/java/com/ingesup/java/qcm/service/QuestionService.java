package com.ingesup.java.qcm.service;

import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Question;

import java.util.List;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public interface QuestionService extends BaseService<Question, String> {

	List<Question> getQuestionsByQcmId(String qcmId);

	List<Question> getQuestionsByQcm(Qcm qcm);
}
