package com.ingesup.java.qcm.form;

import javax.validation.constraints.NotNull;

/**
 * Created by lopes_f on 1/29/2015.
 * <florian.lopes@outlook.com>
 */
public class AddAnswersForm {

	@NotNull
	private String questionId;

	@NotNull
	private String content;

	private int answerRate;

	public AddAnswersForm() {
	}

	public AddAnswersForm(String questionId) {
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
}
