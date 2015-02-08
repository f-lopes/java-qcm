package com.ingesup.java.qcm.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lopes_f on 1/17/2015.
 * <florian.lopes@outlook.com>
 */
@Entity
@Table(name = "evaluation_student")
public class EvaluationStudent {

	@EmbeddedId
	@AttributeOverrides ({
			@AttributeOverride(name = "evaluationId", column = @Column(name = "evaluationid", nullable = false, insertable = false, updatable = false)),
			@AttributeOverride(name = "studentId", column = @Column(name = "studentid", nullable = false, insertable = false, updatable = false))
	})
	private EvaluationStudentPk evaluationStudentPk;

	private int mark;

	private Date date;

	@ManyToOne
	private Student student;

	@ManyToOne
	private Evaluation evaluation;

	public EvaluationStudent() {
	}

	public EvaluationStudent(Evaluation evaluation, Student student, int evaluationMark, Date takenDate) {
		this.evaluationStudentPk = new EvaluationStudentPk(evaluation, student);
		this.evaluation = evaluation;
		this.student = student;
		this.mark = evaluationMark;
		this.date = takenDate;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {

		EvaluationStudent evaluationStudent;

		public Builder() {
			this.evaluationStudent = new EvaluationStudent();
		}

		public Builder student(Student student) {
			this.evaluationStudent.setStudent(student);
			return this;
		}

		public Builder evaluation(Evaluation evaluation) {
			this.evaluationStudent.setEvaluation(evaluation);
			return this;
		}

		public Builder date(Date date) {
			this.evaluationStudent.setDate(date);
			return this;
		}

		public Builder mark(int mark) {
			this.evaluationStudent.setMark(mark);
			return this;
		}

		public EvaluationStudent build() {
			EvaluationStudentPk evaluationStudentPk =
					new EvaluationStudentPk(this.evaluationStudent.getEvaluation(), this.evaluationStudent.getStudent());
			this.evaluationStudent.setEvaluationStudentPk(evaluationStudentPk);
			return this.evaluationStudent;
		}
	}

	public EvaluationStudentPk getEvaluationStudentPk() {
		return evaluationStudentPk;
	}

	public void setEvaluationStudentPk(EvaluationStudentPk evaluationStudentPk) {
		this.evaluationStudentPk = evaluationStudentPk;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Evaluation getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(Evaluation evaluation) {
		this.evaluation = evaluation;
	}
}
