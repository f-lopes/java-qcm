package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.service.QcmService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class AddQuestionsForm {

	@Autowired
	private QcmService qcmService;

	private String qcmId;

	private String label;

	public AddQuestionsForm(String qcmId) {
		this.qcmId = qcmId;
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
		return new Question(qcmService.get(qcmId), label);
	}
}
