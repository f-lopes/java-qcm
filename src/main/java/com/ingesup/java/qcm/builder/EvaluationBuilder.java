package com.ingesup.java.qcm.builder;

import com.ingesup.java.qcm.entity.*;

import java.util.Date;
import java.util.List;

/**
 * Created by lopes_f on 1/22/2015.
 * <florian.lopes@outlook.com>
 */
public class EvaluationBuilder implements Builder<Evaluation> {

	private Evaluation evaluation;

	public EvaluationBuilder() {
		this.evaluation = new Evaluation();
	}

	public EvaluationBuilder startDate(final Date startDate) {
		this.evaluation.setStartDate(startDate);
		return this;
	}

	public EvaluationBuilder endDate(final Date endDate) {
		this.evaluation.setEndDate(endDate);
		return this;
	}

	public EvaluationBuilder students(final List<EvaluationStudent> students) {
		this.evaluation.setStudents(students);
		return this;
	}

	public EvaluationBuilder grade(final Grade grade) {
		this.evaluation.setGrade(grade);
		return this;
	}

	public EvaluationBuilder id(final String id) {
		this.evaluation.setId(id);
		return this;
	}

	public EvaluationBuilder course(Course course) {
		this.evaluation.setCourse(course);
		return this;
	}

	public EvaluationBuilder teacher(Teacher teacher) {
		this.evaluation.setTeacher(teacher);
		return this;
	}

	public EvaluationBuilder qcm(Qcm qcm) {
		this.evaluation.setQcm(qcm);
		return this;
	}

	@Override
	public Evaluation build() {
		return this.evaluation;
	}
}
