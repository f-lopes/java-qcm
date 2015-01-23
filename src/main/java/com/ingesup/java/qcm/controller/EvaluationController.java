package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.*;
import com.ingesup.java.qcm.form.CreateEvaluationForm;
import com.ingesup.java.qcm.form.ValidateQcmForm;
import com.ingesup.java.qcm.security.CurrentUser;
import com.ingesup.java.qcm.service.CourseService;
import com.ingesup.java.qcm.service.EvaluationService;
import com.ingesup.java.qcm.service.GradeService;
import com.ingesup.java.qcm.service.QcmService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/evaluations")
public class EvaluationController {

	private static final String ALL_EVALUATIONS_VIEW = "evaluation/list";
	private static final String AVAILABLE_EVALUATIONS_VIEW = "evaluation/availableList";
	private static final String CREATE_EVALUATION_VIEW = "evaluation/create";
	private static final String TAKE_EVALUATION_VIEW = "evaluation/take";
	private static final String VIEW_EVALUATION_VIEW = "evaluation/view";

	@Autowired
	private QcmService qcmService;

	@Autowired
	private EvaluationService evaluationService;

	@Autowired
	private GradeService gradeService;

	@Autowired
	private CourseService courseService;

	@Secured(value = "ROLE_STUDENT")
	@RequestMapping(method = RequestMethod.GET)
	public String availableEvaluations(Model model, @CurrentUser Student student) {
		model.addAttribute("availableEvaluations",
				evaluationService.getAvailableEvaluationsByGrade(student.getGrade()));

		return AVAILABLE_EVALUATIONS_VIEW;
	}

//	@Secured(value = "ROLE_TEACHER")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createEvaluationView(Model model, @CurrentUser Teacher teacher) {
		model.addAttribute("createEvaluationForm", new CreateEvaluationForm(teacher.getId()));
		model.addAttribute("grades", gradeService.getAll());
		model.addAttribute("qcmList", qcmService.getAll());
		model.addAttribute("courses", courseService.getAll());

		return CREATE_EVALUATION_VIEW;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewEvaluationView(Model model, @PathVariable("id") String evalId) {
		model.addAttribute(evaluationService.get(evalId));

		return VIEW_EVALUATION_VIEW;
	}

//	@Secured("ROLE_TEACHER"})
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createEvaluation(@Valid CreateEvaluationForm createEvaluationForm,
								   BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {

			return CREATE_EVALUATION_VIEW;
		}

		return "redirect:" + ALL_EVALUATIONS_VIEW;
	}

	@Secured(value = "ROLE_STUDENT")
	@RequestMapping(value = "/take", method = RequestMethod.POST)
	public String takeEvaluation(Model model, @RequestParam String evaluationId, RedirectAttributes redirectAttributes) {
		Evaluation evaluation = evaluationService.get(evaluationId);
		if(evaluation != null) {
			model.addAttribute("qcm", evaluation.getQcm());
			model.addAttribute("validateQcmForm", new ValidateQcmForm(evaluation.getId(), evaluation.getQcm().getId()));
			return TAKE_EVALUATION_VIEW;
		}
		return "redirect:" + VIEW_EVALUATION_VIEW;
	}

	@Secured(value = "ROLE_STUDENT")
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validateEvaluation(Model model, @Valid ValidateQcmForm validateQcmForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
			model.addAttribute("flash", MessageUtil.returnDanger("qcm.view.validate.error"));
			return TAKE_EVALUATION_VIEW;
		}

		// TODO : validation du QCM

		return "redirect:" + VIEW_EVALUATION_VIEW;
	}
}
