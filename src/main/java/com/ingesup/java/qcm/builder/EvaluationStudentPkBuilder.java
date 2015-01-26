package com.ingesup.java.qcm.builder;

import com.ingesup.java.qcm.entity.EvaluationStudentPk;

/**
 * Created by lopes_f on 1/23/2015.
 * <florian.lopes@outlook.com>
 */
public class EvaluationStudentPkBuilder implements Builder<EvaluationStudentPk> {

	private EvaluationStudentPk evaluationStudentPk;

	public EvaluationStudentPkBuilder() {
		this.evaluationStudentPk = new EvaluationStudentPk();
	}

	@Override
	public EvaluationStudentPk build() {
		return evaluationStudentPk;
	}
}
