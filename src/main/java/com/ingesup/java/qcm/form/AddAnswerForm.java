package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Answer;
import com.ingesup.java.qcm.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;

/**
 * Created by lopes_f on 1/29/2015.
 * <florian.lopes@outlook.com>
 */
public class AddAnswerForm {

	@Autowired
	private QuestionService questionService;

	@NotNull
	private String questionId;

	@NotNull
	private String content;

	private int answerRate;

	public AddAnswerForm(String questionId) {
		this.questionId = questionId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getAnswerRate() {
		return answerRate;
	}

	public void setAnswerRate(int answerRate) {
		this.answerRate = answerRate;
	}

	public Answer getAnswer() {
		return new Answer(content, questionService.get(questionId), answerRate);
	}
}
