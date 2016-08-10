package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Answer;
import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.form.AddAnswersForm;
import com.ingesup.java.qcm.form.QcmForm;
import com.ingesup.java.qcm.form.QuestionForm;
import com.ingesup.java.qcm.security.CurrentUser;
import com.ingesup.java.qcm.service.AnswerService;
import com.ingesup.java.qcm.service.QcmService;
import com.ingesup.java.qcm.service.QuestionService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/qcm")
@Secured(value = {"ROLE_ADMIN", "ROLE_TEACHER"})
public class QcmController {

	private static final String ADD_QCM_URL = "/qcm/create";
	private static final String ALL_QCM_URL = "/qcm/all";

	private static final String ADD_QCM_VIEW = "/qcm/create";
	private static final String VIEW_QCM_VIEW = "/qcm/view";
	private static final String ALL_QCM_VIEW = "/qcm/list";
	private static final String QCM_VIEW = "/qcm/view";
	private static final String QCM_QUESTIONS_VIEW = "/question/list";
	private static final String QCM_QUESTION_ANSWERS_VIEW = "/answer/list";
	private static final String ADD_QUESTION_VIEW = "question/create";
	private static final String EDIT_QUESTION_VIEW = "question/edit";
	private static final String ADD_ANSWER_VIEW = "answer/add";

	private final QcmService qcmService;
	private final QuestionService questionService;
	private final MessageSource messageSource;
	private final AnswerService answerService;

	public QcmController(QcmService qcmService, QuestionService questionService,
						 MessageSource messageSource, AnswerService answerService) {
		this.qcmService = qcmService;
		this.questionService = questionService;
		this.messageSource = messageSource;
		this.answerService = answerService;
	}

	@GetMapping("/all")
	public String qcmListTeacher(Model model, @CurrentUser Teacher teacher) {
		if(teacher != null) {
			model.addAttribute("qcmList", qcmService.getQcmByTeacher(teacher));
		} else {
			model.addAttribute("qcmList", qcmService.getAll());
		}

		return ALL_QCM_VIEW;
	}

	@GetMapping("/{id}")
	public String viewQcm(Model model, @PathVariable("id") String qcmId) {
		Qcm qcm = qcmService.get(qcmId);
		model.addAttribute("qcm", qcm);
		model.addAttribute("questions", questionService.getQuestionsByQcm(qcm));

		return QCM_VIEW;
	}

	@GetMapping("create")
	public String addQcmView(Model model) {
		model.addAttribute("qcmForm", new QcmForm());

		return ADD_QCM_VIEW;
	}

	@PostMapping("create")
	public String saveQcm(Model model, @Valid QcmForm qcmForm, BindingResult bindingResult,
                          @CurrentUser Teacher teacher, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// handle errors

			model.addAttribute("flash", MessageUtil.returnDanger("qcm.create.error"));

			return "redirect:" + ADD_QCM_URL;
		}

        Qcm qcm = qcmForm.getQcm();
        qcm.setTeacher(teacher);
		qcm = qcmService.add(qcm);

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("qcm.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + getQuestionsForQcmURL(qcm.getId());
	}

	@PostMapping("/{id}/questions")
	public String viewQcmQuestions(Model model, @PathVariable("id") String qcmId, RedirectAttributes redirectAttributes) {
		Qcm qcm = qcmService.get(qcmId);
		List<Question> questions = questionService.getQuestionsByQcm(qcm);

		if (qcm == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("qcm.not.found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + ALL_QCM_URL;
		}

		model.addAttribute("qcm", qcm);
		model.addAttribute("questions", questions);

		return QCM_QUESTIONS_VIEW;
	}

	@GetMapping("/{qcmId}/questions/add")
	public String addQuestion(Model model, @PathVariable String qcmId) {

		model.addAttribute("addQuestionForm", new QuestionForm(qcmId));

		return ADD_QUESTION_VIEW;
	}

	@PostMapping("/{qcmId}/questions/create")
	public String saveQuestion(Model model, @PathVariable String qcmId,
							   @Valid QuestionForm questionsForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// handle errors

			model.addAttribute("flash", MessageUtil.returnDanger("qcm.create.error"));

			return "redirect:" + getQuestionsForQcmURL(qcmId);
		}
		questionsForm.setQcmService(qcmService);
		questionService.add(questionsForm.getQuestion());

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("question.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + getQuestionsForQcmURL(qcmId);
	}

	@GetMapping("/{qcmId}/questions/edit/{questionId}")
	public String editQuestion(Model model, @PathVariable String qcmId, @PathVariable String questionId, RedirectAttributes redirectAttributes) {
		final Question question = questionService.get(questionId);
		if (question == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("qcm.not.found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + getQuestionsForQcmURL(qcmId);
		}
		// TODO link to editQuestion

		model.addAttribute("questionForm", QuestionForm.fromQuestion(question));
		
		return EDIT_QUESTION_VIEW;
	}

	@PostMapping("/{qcmId}/questions/edit")
	public String handleEditQuestion(Model model, @Valid QuestionForm questionsForm, BindingResult bindingResult,
									 @PathVariable String qcmId, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			// handle errors
			model.addAttribute("flash", MessageUtil.returnDanger("qcm.create.error"));

			return "redirect:" + getQuestionsForQcmURL(qcmId);
		}

		questionsForm.setQcmService(qcmService);
		questionService.update(questionsForm.getQuestion());

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("question.edit.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + getQuestionsForQcmURL(qcmId);
	}

	@GetMapping("/{id}/questions/{questionId}")
	public String viewQcmQuestionAnswers(Model model, @PathVariable("id") String qcmId,
										 @PathVariable("questionId") String questionId, RedirectAttributes redirectAttributes) {
		Question question = questionService.get(questionId);

		if (question == null || !qcmId.equals(question.getQcm().getId())) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("question.not.found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + ALL_QCM_VIEW;
		}

		model.addAttribute("question", question);

		return QCM_QUESTION_ANSWERS_VIEW;
	}

	@GetMapping("/{id}/questions/{questionId}/answers")
	public String questionAnswers(Model model, @PathVariable("id") String qcmId,
										 @PathVariable("questionId") String questionId, RedirectAttributes redirectAttributes) {
		Question question = questionService.get(questionId);

		if (question == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("question.not.found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + getAnswersForQuestionURL(qcmId, questionId);
		}

		if (qcmId.equals(question.getQcm().getId())) {

			model.addAttribute("answers", question.getAnswers());
			model.addAttribute("qcm", qcmService.get(qcmId));
			model.addAttribute("question", question);
		}

		return QCM_QUESTION_ANSWERS_VIEW;
	}

	@GetMapping("/{id}/questions/{questionId}/answers/add")
	public String addAnswerForQuestion(Model model, @PathVariable("id") String qcmId,
									   @PathVariable("questionId") String questionId, RedirectAttributes redirectAttributes) {
		Question question = questionService.get(questionId);

		if (question == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("question.not.found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + getAnswersForQuestionURL(qcmId, questionId);
		}

		model.addAttribute("qcmId", qcmId);
		model.addAttribute("questionId", questionId);
		model.addAttribute("addAnswerForm", new AddAnswersForm(questionId));

		return ADD_ANSWER_VIEW;
	}

	@PostMapping("/{id}/questions/{questionId}/answers/add")
	public String saveAnswerForQuestion(@PathVariable("id") String qcmId,
									   @PathVariable("questionId") String questionId,
									   @Valid
									   AddAnswersForm addAnswersForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			// handle errors
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnDanger("answer.create.error"));

			return "redirect:" + getAddAnswerForQuestionURL(qcmId, questionId);
		}

		Answer answer = new Answer(addAnswersForm.getContent(), questionService.get(questionId), addAnswersForm.getAnswerRate());
		answerService.add(answer);

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnDanger("answer.create.success"));

		return "redirect:" + getAnswersForQuestionURL(qcmId, questionId);
	}

	private String getQuestionsForQcmURL(String qcmId) {
		return "/qcm/" + qcmId;
	}

	private String getAnswersForQuestionURL(String qcmId, String questionId) {
		return MvcUriComponentsBuilder
				.fromMethodName(QcmController.class, "questionAnswers",null, qcmId, questionId, null)
				.build().toUriString();
	}

	private String getAddAnswerForQuestionURL(String qcmId, String questionId) {
		return MvcUriComponentsBuilder
				.fromMethodName(this.getClass(), "addAnswerForQuestion", null, qcmId, questionId, null)
				.toUriString();
	}
}
