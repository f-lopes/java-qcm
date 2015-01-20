package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.entity.Qcm;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public class CreateEvaluationForm {

	@NotEmpty
	private String evaluationName;

	@NotEmpty
	private Date startDate;

	@NotEmpty
	private Date endDate;

	@NotEmpty
	private Qcm evaluationQcm;

	public String getEvaluationName() {
		return evaluationName;
	}

	public void setEvaluationName(String evaluationName) {
		this.evaluationName = evaluationName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Qcm getEvaluationQcm() {
		return evaluationQcm;
	}

	public void setEvaluationQcm(Qcm evaluationQcm) {
		this.evaluationQcm = evaluationQcm;
	}
}
