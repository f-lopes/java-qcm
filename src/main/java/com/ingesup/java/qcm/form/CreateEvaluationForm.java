package com.ingesup.java.qcm.form;

import com.ingesup.java.qcm.builder.EvaluationBuilder;
import com.ingesup.java.qcm.entity.Course;
import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Qcm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
public class CreateEvaluationForm {

	private static final Logger logger = LoggerFactory.getLogger(CreateEvaluationForm.class);

	private DateFormat dateFormatter;

	@NotNull
	private String evaluationName;

	@NotNull
	private Date startDate;

	@NotNull
	private Date endDate;

	@NotNull
	private Qcm evaluationQcm;

	@NotNull
	private Grade grade;

	@NotNull
	private Course course;

	public CreateEvaluationForm() {
	}

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

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Evaluation getEvaluation() {
		if (dateFormatter == null) {
			// Date format : dd/mm/yyyy
			dateFormatter = new SimpleDateFormat("dd/mm/yyyy");
		}
		String frenchDateFormat = dateFormatter.format(startDate);
		try {
			startDate = dateFormatter.parse(frenchDateFormat);
			frenchDateFormat = dateFormatter.format(endDate);
			endDate = dateFormatter.parse(frenchDateFormat);
		}
		catch (ParseException e) {
			logger.error(String.format("Failed to format date {%s}", startDate), e);
		}

		Evaluation evaluation = new EvaluationBuilder()
				.startDate(startDate)
				.endDate(endDate)
				.grade(grade)
				.course(course).build();

		return evaluation;
	}
}
