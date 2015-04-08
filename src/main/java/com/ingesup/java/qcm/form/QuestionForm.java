package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.service.QcmService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class QuestionForm {

	private QcmService qcmService;

	private String qcmId;

	private String label;

	private int points;

	public QuestionForm(String qcmId) {
		this.qcmId = qcmId;
	}

	public QuestionForm() {
	}

	public static QuestionForm fromQuestion(Question question) {
		final QuestionForm questionForm = new QuestionForm();
		question.setId(question.getId());
		question.setLabel(question.getLabel());

		return questionForm;
	}

	public QcmService getQcmService() {
		return qcmService;
	}

	public void setQcmService(QcmService qcmService) {
		this.qcmService = qcmService;
	}

	public String getQcmId() {
		return qcmId;
	}

	public void setQcmId(String qcmId) {
		this.qcmId = qcmId;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Question getQuestion() {
		return new Question(qcmService.get(qcmId), label, points);
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
