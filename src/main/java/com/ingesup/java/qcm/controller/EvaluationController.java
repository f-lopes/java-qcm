package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.form.CreateEvaluationForm;
import com.ingesup.java.qcm.form.ValidateQcmForm;
import com.ingesup.java.qcm.security.CurrentUser;
import com.ingesup.java.qcm.service.CourseService;
import com.ingesup.java.qcm.service.EvaluationService;
import com.ingesup.java.qcm.service.GradeService;
import com.ingesup.java.qcm.service.QcmService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by lopes_f on 1/16/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/evaluations")
public class EvaluationController {

	private static final String ALL_EVALUATIONS_URL = "/evaluations/all";
	private static final String PROPOSED_EVALUATIONS_URL = "/evaluations/proposed-evaluations";
	private static final String AVAILABLE_EVALUATIONS_URL = "/evaluations";

	private static final String ALL_EVALUATIONS_VIEW = "evaluation/list";
	private static final String AVAILABLE_EVALUATIONS_VIEW = "evaluation/availableList";
	private static final String CREATE_EVALUATION_VIEW = "evaluation/create";
	private static final String TAKE_EVALUATION_VIEW = "evaluation/take";
	private static final String VIEW_EVALUATION_VIEW = "evaluation/view";
	private static final String ADMIN_EVALUATIONS_BY_GRADE_VIEW = "evaluation/admin/list";
	private static final String EVALUATION_DETAIL_VIEW = "evaluation/detail";
	private static final String EVALUATIONS_RESULTS = "evaluation/evaluationsResults";

	private final QcmService qcmService;
	private final EvaluationService evaluationService;
	private final GradeService gradeService;
	private final CourseService courseService;
	private final MessageSource messageSource;

    @Autowired
    public EvaluationController(QcmService qcmService, EvaluationService evaluationService,
                                GradeService gradeService, CourseService courseService, MessageSource messageSource) {
        this.qcmService = qcmService;
        this.evaluationService = evaluationService;
        this.gradeService = gradeService;
        this.courseService = courseService;
        this.messageSource = messageSource;
    }

    @Secured(value = "ROLE_STUDENT")
	@RequestMapping(method = RequestMethod.GET)
	public String availableEvaluationsForStudent(Model model, @CurrentUser Student student) {
		Map<Evaluation, EvaluationStudent> availablesEvaluationsForStudent = new HashMap<>();

		List<Evaluation> availableEvaluationsForGrade = evaluationService.getAvailableEvaluationsByGrade(student.getGrade());
		for (Evaluation evaluation : availableEvaluationsForGrade) {
			availablesEvaluationsForStudent.put(evaluation, evaluationService.getTakenEvaluation(evaluation.getId(), student.getId()));
		}

		model.addAttribute("availableEvaluations", availablesEvaluationsForStudent);

		return AVAILABLE_EVALUATIONS_VIEW;
	}

	@Secured(value = "ROLE_ADMIN")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String evaluations(Model model) {
		model.addAttribute("grades", gradeService.getAll());
		model.addAttribute("evaluations", evaluationService.getAll());

		return ALL_EVALUATIONS_VIEW;
	}

	@Secured(value = "ROLE_ADMIN")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteEvaluation(@RequestParam("evaluationId") String evaluationId,
								   RedirectAttributes redirectAttributes) {

        if (evaluationService.hasStudentsTakenEvaluation(evaluationId)) {
            redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
                    messageSource.getMessage("evaluation.delete.error.already-taken", null, LocaleContextHolder.getLocale())));
            return "redirect:" + ALL_EVALUATIONS_URL;
        }

        Evaluation evaluation = evaluationService.get(evaluationId);
        if (evaluation == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("evaluation.create.success", null, LocaleContextHolder.getLocale())));
			return "redirect:" + ALL_EVALUATIONS_URL;
		}

		evaluationService.removeEntity(evaluation);

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
                messageSource.getMessage("evaluation.delete.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + ALL_EVALUATIONS_URL;
	}

	@Secured(value = "ROLE_ADMIN")
	@RequestMapping(value = "/by-grade", method = RequestMethod.GET)
	public String evaluationsByGrade(Model model, @RequestParam(required = false) String grade) {

		if (grade == null) {
			model.addAttribute("evaluations", evaluationService.getAll());
		} else {
			model.addAttribute("evaluations", evaluationService.getAvailableEvaluationsByGrade(gradeService.getGradeByName(grade)));
		}

		return ALL_EVALUATIONS_VIEW;
	}

	@Secured(value = "ROLE_TEACHER")
	@RequestMapping(value = "/proposed-evaluations", method = RequestMethod.GET)
	public String evaluationsByTeacher(Model model, @RequestParam(required = false) boolean finished, @CurrentUser Teacher teacher) {

		if (finished) {
			Map<Evaluation, Float> averageMarkByEvaluation = new HashMap<>();
			List<Evaluation> finishedEvaluations = evaluationService.getFinishedEvaluationsByTeacher(teacher);

			if (finishedEvaluations != null) {
				for (Evaluation evaluation: finishedEvaluations) {
					averageMarkByEvaluation.put(evaluation, evaluationService.getAverageMarkForEvaluation(evaluation));
				}
			}
			model.addAttribute("averageMark", averageMarkByEvaluation);
			model.addAttribute("evaluations", finishedEvaluations);
			return EVALUATIONS_RESULTS;
		} else {
			model.addAttribute("evaluations", evaluationService.getEvaluationsByTeacher(teacher));
		}

		return ALL_EVALUATIONS_VIEW;
	}

	@Secured(value = "ROLE_ADMIN")
	@RequestMapping(value = "/evaluation-detail/{evaluationId}", method = RequestMethod.GET)
	public String evaluationDetail(Model model, @PathVariable String evaluationId, RedirectAttributes redirectAttributes) {
		Evaluation evaluation = evaluationService.get(evaluationId);

		if (evaluation == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("evaluation.not-found", null, LocaleContextHolder.getLocale())));
		}

		List<EvaluationStudent> studentsResults = evaluationService.getStudentsResultsByEvaluation(evaluation);

		model.addAttribute("evaluation", evaluation);
		model.addAttribute("studentsResults", studentsResults);

		return EVALUATION_DETAIL_VIEW;
	}

	@Secured(value = "ROLE_STUDENT")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String viewEvaluationView(Model model, @PathVariable("id") String evalId, @CurrentUser Student student) {
		if (evaluationService.hasStudentTakenEvaluation(student.getId(), evalId)) {
			model.addAttribute("takenEvaluation", evaluationService.getTakenEvaluation(evalId, student.getId()));
		} else {
			model.addAttribute("evaluation", evaluationService.get(evalId));
		}

		return VIEW_EVALUATION_VIEW;
	}

	@Secured(value = "ROLE_TEACHER")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createEvaluationView(Model model) {
		model.addAttribute("createEvaluationForm", new CreateEvaluationForm());
		model.addAttribute("grades", gradeService.getAll());
		model.addAttribute("qcmList", qcmService.getAll());
		model.addAttribute("courses", courseService.getAll());

		return CREATE_EVALUATION_VIEW;
	}

	@Secured("ROLE_TEACHER")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String createEvaluation(@Valid CreateEvaluationForm createEvaluationForm, @CurrentUser Teacher teacher,
								   BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {

			return CREATE_EVALUATION_VIEW;
		}

		Evaluation evaluation = createEvaluationForm.getEvaluation();
		evaluation.setTeacher(teacher);
		evaluationService.add(evaluation);

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("evaluation.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + PROPOSED_EVALUATIONS_URL;
	}

	@Secured(value = "ROLE_STUDENT")
	@RequestMapping(value = "/take", method = RequestMethod.GET)
	public String takeEvaluation(@RequestParam String evaluationId, Model model,
								 @CurrentUser Student student, RedirectAttributes redirectAttributes) {

		if (evaluationService.hasStudentTakenEvaluation(student.getId(), evaluationId)) {
			redirectAttributes.addAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("evaluation.already.taken", null, LocaleContextHolder.getLocale())));
			return "redirect:" + VIEW_EVALUATION_VIEW;
		}

		Evaluation evaluation = evaluationService.get(evaluationId);

		if(evaluation != null) {
			model.addAttribute("qcm", evaluation.getQcm());
			model.addAttribute("validateQcmForm", new ValidateQcmForm(evaluation.getId(), evaluation.getQcm().getId()));
			return TAKE_EVALUATION_VIEW;
		}

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
				messageSource.getMessage("error", null, LocaleContextHolder.getLocale())));

		return "redirect:" + VIEW_EVALUATION_VIEW;
	}

	@Secured(value = "ROLE_STUDENT")
	@RequestMapping(value = "/validate", method = RequestMethod.POST)
	public String validateEvaluation(Model model, @Valid ValidateQcmForm validateQcmForm, BindingResult bindingResult,
									 RedirectAttributes redirectAttributes, @CurrentUser Student student) {
		if(bindingResult.hasErrors()){
			model.addAttribute("flash", MessageUtil.returnDanger("qcm.view.validate.error"));
			return TAKE_EVALUATION_VIEW;
		}

		//evaluationService.takeEvaluation(validateQcmForm.getEvalId(), validateQcmForm.getQcmId(), null, validateQcmForm.getSelectedAnswers(), null);
		Set<String> answersIds = new HashSet<>(Arrays.asList(validateQcmForm.getSelectedAnswers()));

		int evaluationMark = evaluationService.
				takeEvaluation(validateQcmForm.getEvalId(), validateQcmForm.getQcmId(), student, answersIds, new Date());

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("qcm.validate.success", new Object[]{evaluationMark}, LocaleContextHolder.getLocale())));

		return "redirect:" + AVAILABLE_EVALUATIONS_URL;
	}
}
