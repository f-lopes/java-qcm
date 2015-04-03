package com.ingesup.java.qcm.validation;

import com.ingesup.java.qcm.form.CreateEvaluationForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Created by lopes_f on 3/12/2015.
 * <florian.lopes@outlook.com>
 */
public class CreateEvaluationFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return CreateEvaluationForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "evaluationName", "validation.evaluation.evaluationName.empty");

		CreateEvaluationForm createEvaluationForm = (CreateEvaluationForm) target;

		ValidationUtils.rejectIfEmpty(errors, "startDate", "validation.evaluation.startDate.mandatory");
		ValidationUtils.rejectIfEmpty(errors, "endDate", "validation.evaluation.startDate.mandatory");

		if (createEvaluationForm.getStartDate() != null) {
			if (createEvaluationForm.getStartDate().before(new Date())) {
				errors.rejectValue("startDate", "validation.evaluation.startDate.after.today");
			} else if (createEvaluationForm.getStartDate().after(createEvaluationForm.getEndDate())) {
				errors.rejectValue("endDate", "validation.evaluation.endDate.afterStartDate");
			}
		}
	}
}
