package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.service.QcmService;
import org.apache.commons.lang.StringUtils;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class QuestionForm {

	private String id;

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
		if (question.getQcm() != null) {
			questionForm.setQcmId(question.getQcm().getId());
		}
		questionForm.setId(question.getId());
		questionForm.setLabel(question.getLabel());

		return questionForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		final Question question = new Question(qcmService.get(qcmId), label, points);
		if (StringUtils.isNotEmpty(id)) {
			question.setId(id);
		}

		return question;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
}
