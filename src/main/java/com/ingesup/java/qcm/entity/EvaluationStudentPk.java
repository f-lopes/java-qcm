package com.ingesup.java.qcm.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by lopes_f on 1/17/2015.
 * <florian.lopes@outlook.com>
 */
@Embeddable
public class EvaluationStudentPk implements Serializable {

	@Column(name = "evaluation_id", insertable = false, updatable = false)
	private String evaluationId;

	@Column(name = "student_id",  insertable = false, updatable = false)
	private String studentId;

	public EvaluationStudentPk() {

	}

	public EvaluationStudentPk(Evaluation evaluation, Student student) {
		this.evaluationId = evaluation.getId();
		this.studentId = student.getId();
	}

	public String getEvaluationId() {
		return evaluationId;
	}

	public String getStudentId() {
		return studentId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof EvaluationStudentPk)) {
			return false;
		}

		EvaluationStudentPk that = (EvaluationStudentPk) o;

		if (evaluationId != null ? !evaluationId.equals(that.evaluationId) : that.evaluationId != null) {
			return false;
		}
		if (studentId != null ? !studentId.equals(that.studentId) : that.studentId != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = evaluationId != null ? evaluationId.hashCode() : 0;
		result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
		return result;
	}
}
