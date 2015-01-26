package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.form.AddQuestionsForm;
import com.ingesup.java.qcm.form.QcmForm;
import com.ingesup.java.qcm.service.QcmService;
import com.ingesup.java.qcm.service.QuestionService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/qcm")
public class QcmController {

	private static final String ADD_QCM_URL = "/qcm/create";
	private static final String ALL_QCM_URL = "/qcm/";

	private static final String ADD_QCM_VIEW = "/qcm/create";
	private static final String VIEW_QCM_VIEW = "/qcm/view";
	private static final String ALL_QCM_VIEW = "/qcm/list";
	private static final String QCM_VIEW = "/qcm/view";
	private static final String QCM_QUESTIONS_VIEW = "/questions/list";
	private static final String QCM_QUESTION_ANSWERS_VIEW = "/answer/list";
	private static final String ADD_QUESTION_VIEW = "question/create";

	@Autowired
	private QcmService qcmService;

	@Autowired
	private QuestionService questionService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	public QcmController(QcmService qcmService) {
		this.qcmService = qcmService;
	}

	@ModelAttribute("questionsPoints")
	private List<String> populateModelPoints() {
		return Arrays.asList("1", "2", "3");
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String qcmList(Model model) {
		model.addAttribute("qcmList", qcmService.getAll());

		return ALL_QCM_VIEW;
	}

	@RequestMapping(value = "/{id}")
	public String viewQcm(Model model, @PathVariable("id") String qcmId) {
		model.addAttribute("qcm", qcmService.get(qcmId));

		return QCM_VIEW;
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String addQcmView(Model model) {
		model.addAttribute("qcmForm", new QcmForm());

		return ADD_QCM_VIEW;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String saveQcm(Model model, @Valid QcmForm qcmForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// handle errors

			model.addAttribute("flash", MessageUtil.returnDanger("qcm.create.error"));

			return ADD_QCM_VIEW;
		}

		qcmService.add(qcmForm.getQcm());

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("qcm.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + ADD_QCM_URL;
	}

	@RequestMapping(value = "/{id}/questions")
	public String viewQcmQuestions(Model model, @PathVariable("id") String qcmId, RedirectAttributes redirectAttributes) {
		Qcm qcm = qcmService.get(qcmId);

		if (qcm == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("qcm.not.found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + ALL_QCM_VIEW;
		}

		model.addAttribute("qcm", qcm);

		return QCM_QUESTIONS_VIEW;
	}

	@RequestMapping(value = "/{qcmId}/questions/add")
	public String addQuestion(Model model) {


		model.addAttribute("addQuestionForm", new AddQuestionsForm());

		return ADD_QUESTION_VIEW;
	}

	@RequestMapping(value = "/{qcmId}/questions/create", method = RequestMethod.POST)
	public String saveQuestion(Model model, @Valid AddQuestionsForm questionsForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// handle errors

			model.addAttribute("flash", MessageUtil.returnDanger("qcm.create.error"));

			return ADD_QUESTION_VIEW;
		}

		questionService.add(questionsForm.createQuestion());

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("question.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + ADD_QUESTION_VIEW;
	}

	@RequestMapping(value = "/{id}/questions/{questionId}")
	public String viewQcmQuestionAnswers(Model model, @PathVariable("id") String qcmId,
										 @PathVariable("questionId") String questionId, RedirectAttributes redirectAttributes) {
		Question question = questionService.get(questionId);

		// TODO
		if (question == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("question.not.found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + ALL_QCM_VIEW;
		}

		if (qcmId.equals(question.getQcm().getId())) {

			model.addAttribute("answers", question.getAnswers());
		}

		return QCM_QUESTION_ANSWERS_VIEW;
	}

	@RequestMapping(value = "/{id}/questions/{questionId}/answers")
	public String questionAnswers(Model model, @PathVariable("id") String qcmId,
										 @PathVariable("questionId") String questionId, RedirectAttributes redirectAttributes) {
		Question question = questionService.get(questionId);

		// TODO
		if (question == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("question.not.found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + ALL_QCM_VIEW;
		}

		if (qcmId.equals(question.getQcm().getId())) {

			model.addAttribute("answers", question.getAnswers());
		}

		return QCM_QUESTION_ANSWERS_VIEW;
	}
}
