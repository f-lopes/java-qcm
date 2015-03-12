package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Answer;
import com.ingesup.java.qcm.entity.Qcm;
import com.ingesup.java.qcm.entity.Question;
import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.form.AddAnswersForm;
import com.ingesup.java.qcm.form.AddQuestionsForm;
import com.ingesup.java.qcm.form.QcmForm;
import com.ingesup.java.qcm.security.CurrentUser;
import com.ingesup.java.qcm.service.AnswerService;
import com.ingesup.java.qcm.service.QcmService;
import com.ingesup.java.qcm.service.QuestionService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
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
	private static final String ADD_ANSWER_VIEW = "answer/add";

	private final QcmService qcmService;
	private final QuestionService questionService;
	private final MessageSource messageSource;
	private final AnswerService answerService;

	@Autowired
	public QcmController(QcmService qcmService, QuestionService questionService,
						 MessageSource messageSource, AnswerService answerService) {
		this.qcmService = qcmService;
		this.questionService = questionService;
		this.messageSource = messageSource;
		this.answerService = answerService;
	}

	@ModelAttribute("questionsPoints")
	private List<String> populateModelPoints() {
		return Arrays.asList("1", "2", "3");
	}


	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String qcmListTeacher(Model model, @CurrentUser Teacher teacher) {
		if(teacher != null)
			model.addAttribute("qcmList", qcmService.getQcmByTeacher(teacher));
		else
			model.addAttribute("qcmList", qcmService.getAll());
		return ALL_QCM_VIEW;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewQcm(Model model, @PathVariable("id") String qcmId) {
		Qcm qcm = qcmService.get(qcmId);
		model.addAttribute("qcm", qcm);
		model.addAttribute("questions", questionService.getQuestionsByQcm(qcm));

		return QCM_VIEW;
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String addQcmView(Model model) {
		model.addAttribute("qcmForm", new QcmForm());

		return ADD_QCM_VIEW;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String saveQcm(Model model, @Valid QcmForm qcmForm, BindingResult bindingResult,
                          @CurrentUser Teacher teacher, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// handle errors

			model.addAttribute("flash", MessageUtil.returnDanger("qcm.create.error"));

			return "redirect:" + ADD_QCM_URL;
		}

        Qcm qcm = qcmForm.getQcm();
        qcm.setTeacher(teacher);
		qcmService.add(qcm);

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("qcm.create.success", null, LocaleContextHolder.getLocale())));

		Qcm addedQcm = qcmService.getAll().get(qcmService.getAll().size()-1);
		return "redirect:" + getQuestionsForQcmURL(addedQcm.getId());
	}

	@RequestMapping(value = "/{id}/questions", method = RequestMethod.GET)
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

	@RequestMapping(value = "/{qcmId}/questions/add", method = RequestMethod.GET)
	public String addQuestion(Model model, @PathVariable String qcmId) {

		model.addAttribute("addQuestionForm", new AddQuestionsForm(qcmId));

		return ADD_QUESTION_VIEW;
	}

	@RequestMapping(value = "/{qcmId}/questions/create", method = RequestMethod.POST)
	public String saveQuestion(Model model, @PathVariable String qcmId,
							   @Valid AddQuestionsForm questionsForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

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

	@RequestMapping(value = "/{id}/questions/{questionId}", method = RequestMethod.GET)
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

	@RequestMapping(value = "/{id}/questions/{questionId}/answers", method = RequestMethod.GET)
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

	@RequestMapping(value = "/{id}/questions/{questionId}/answers/add", method = RequestMethod.GET)
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

	@RequestMapping(value = "/{id}/questions/{questionId}/answers/add", method = RequestMethod.POST)
	public String saveAnswerForQuestion(Model model, @PathVariable("id") String qcmId,
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
		return new StringBuilder("/")
				.append("/qcm/")
				.append(qcmId)
				.append("/questions/")
				.append(questionId)
				.append("/answers").toString();
	}

	private String getAddAnswerForQuestionURL(String qcmId, String questionId) {
		return new StringBuilder("/")
				.append("/qcm/")
				.append(qcmId)
				.append("/questions/")
				.append(questionId)
				.append("/answers").toString();
	}
}
