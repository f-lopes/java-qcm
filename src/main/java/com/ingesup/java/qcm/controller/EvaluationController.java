package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.form.CreateEvaluationForm;
import com.ingesup.java.qcm.security.CurrentUser;
import com.ingesup.java.qcm.service.EvaluationService;
import com.ingesup.java.qcm.service.QcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@Autowired
	private QcmService qcmService;

	@Autowired
	private EvaluationService evaluationService;

	@ModelAttribute("qcmList")
	private List<Qcm> populateCreateEvaluationForm() {
		List<Qcm> qcmList = qcmService.getAll();

		return qcmList != null ? qcmList : new ArrayList<Qcm>();
	}

	@Secured(value = "ROLE_STUDENT")
	@RequestMapping(method = RequestMethod.GET)
	public String availableEvaluations(Model model, @CurrentUser Student student) {
		model.addAttribute("availableEvaluations",
				evaluationService.getAvailableEvaluationsByGrade(student.getGrade()));

		return AVAILABLE_EVALUATIONS_VIEW;
	}

//	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createEvaluationView(Model model) {
		model.addAttribute("createEvaluationForm", new CreateEvaluationForm());

		return CREATE_EVALUATION_VIEW;
	}

//	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
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
	public String takeEvaluation(Model model, @RequestParam String evaluationId) {

		return null;
	}
}
