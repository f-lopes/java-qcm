package com.ingesup.java.qcm.validation;

import com.ingesup.java.qcm.form.CreateEvaluationForm;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by lopes_f on 3/12/2015.
 * <florian.lopes@outlook.com>
 */
public class EvaluationDatesValidationTest {

	private static final String START_DATE_FIELD_NAME = "startDate";
	private static final String END_DATE_FIELD_NAME = "endDate";

	private static final String START_DATE_AFTER_TODAY_VALIDATION_CODE = "validation.evaluation.startDate.after.today";
	private static final String END_DATE_AFTER_START_DATE_VALIDATION_CODE = "validation.evaluation.endDate.afterStartDate";

	private static Validator createEvaluationFormValidator = new CreateEvaluationFormValidator();

	private Calendar calendar;

	@Before
	public void setUp() {
		this.calendar = Calendar.getInstance();
	}

	@Test
	public void startDate_should_be_after_today() throws ParseException {
		final Date startDate = getDateBeforeToday();

		CreateEvaluationForm createEvaluationForm = new CreateEvaluationForm();
		final Errors errors = new BeanPropertyBindingResult(createEvaluationForm, "createEvaluationForm");

		createEvaluationForm.setStartDate(startDate);
		createEvaluationForm.setEndDate(new Date());
		createEvaluationForm.setEvaluationName("evaluationName");

		createEvaluationFormValidator.validate(createEvaluationForm, errors);

		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertEquals(START_DATE_FIELD_NAME, errors.getFieldError().getField());
		assertEquals(START_DATE_AFTER_TODAY_VALIDATION_CODE, errors.getAllErrors().get(0).getCode());
		assertEquals(startDate, errors.getFieldError().getRejectedValue());
	}

	@Test
	public void endDate_should_be_after_startDate() throws ParseException {
		CreateEvaluationForm createEvaluationForm = new CreateEvaluationForm();
		final Date startDate = getDateAfterToday(5);
		final Date endDate = getDateAfterToday(2);

		final Errors errors = new BeanPropertyBindingResult(createEvaluationForm, "createEvaluationForm");

		createEvaluationForm.setStartDate(startDate);
		createEvaluationForm.setEndDate(endDate);
		createEvaluationForm.setEvaluationName("evaluationName");

		createEvaluationFormValidator.validate(createEvaluationForm, errors);

		assertTrue(errors.hasErrors());
		assertEquals(1, errors.getErrorCount());
		assertEquals(END_DATE_FIELD_NAME, errors.getFieldError().getField());
		assertEquals(END_DATE_AFTER_START_DATE_VALIDATION_CODE, errors.getAllErrors().get(0).getCode());
		assertEquals(endDate, errors.getFieldError().getRejectedValue());
	}

	private Date getDateBeforeToday() {
		this.calendar.setTime(new Date());
		this.calendar.add(Calendar.DATE, -1);

		return this.calendar.getTime();
	}

	private Date getDateAfterToday(int nbOfDaysAfter) {
		this.calendar.setTime(new Date());
		this.calendar.add(Calendar.DATE, nbOfDaysAfter);

		return this.calendar.getTime();
	}
}
