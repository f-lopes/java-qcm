package com.ingesup.java.qcm.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lopes_f on 1/29/2015.
 * <florian.lopes@outlook.com>
 */
public class AddAnswersForm {

	@NotEmpty
	private String questionId;

	@NotEmpty
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
