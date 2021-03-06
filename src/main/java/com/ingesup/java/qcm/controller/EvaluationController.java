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
import com.ingesup.java.qcm.validation.CreateEvaluationFormValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
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
	private static final String CREATE_EVALUATION_URL = "/evaluations/create";

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

    public EvaluationController(QcmService qcmService, EvaluationService evaluationService,
                                GradeService gradeService, CourseService courseService, MessageSource messageSource) {
        this.qcmService = qcmService;
        this.evaluationService = evaluationService;
        this.gradeService = gradeService;
        this.courseService = courseService;
        this.messageSource = messageSource;
    }

	@InitBinder("createEvaluationForm")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(new CreateEvaluationFormValidator());
	}

    @Secured(value = "ROLE_STUDENT")
	@GetMapping
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
	@GetMapping("/all")
	public String evaluations(Model model) {
		model.addAttribute("grades", gradeService.getAll());
		model.addAttribute("evaluations", evaluationService.getAll());

		return ALL_EVALUATIONS_VIEW;
	}

	@Secured(value = "ROLE_ADMIN")
	@PostMapping("/delete")
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
	@GetMapping("/by-grade")
	public String evaluationsByGrade(Model model, @RequestParam(required = false) String grade, @RequestParam(required = false) boolean onlyAvailables) {
        model.addAttribute("grades", gradeService.getAll());
		if (onlyAvailables) {
			model.addAttribute("evaluations", evaluationService.getAvailableEvaluationsByGrade(gradeService.getGradeByName(grade)));
        } else {
            model.addAttribute("selected_grade", grade);
            model.addAttribute("evaluations", evaluationService.getEvaluationsByGrade(gradeService.getGradeByName(grade)));
        }

        model.addAttribute("grades", gradeService.getAll());

		return ALL_EVALUATIONS_VIEW;
	}

	@Secured(value = "ROLE_TEACHER")
	@GetMapping("/proposed-evaluations")
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

	@Secured(value = "ROLE_TEACHER")
	@GetMapping("/proposed-evaluations-by-grade")
	public String evaluationsByTeacherForGrade(Model model, @RequestParam("grade") String gradeName, @CurrentUser Teacher teacher) {
		model.addAttribute("evaluations", evaluationService.getEvaluationsByTeacherForGrade(teacher, gradeName));

		return ALL_EVALUATIONS_VIEW;
	}

	@Secured(value = "ROLE_ADMIN")
	@GetMapping("/evaluation-detail/{evaluationId}")
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
	@GetMapping("/{id}")
	public String viewEvaluationView(Model model, @PathVariable("id") String evalId, @CurrentUser Student student) {
		if (evaluationService.hasStudentTakenEvaluation(student.getId(), evalId)) {
			model.addAttribute("takenEvaluation", evaluationService.getTakenEvaluation(evalId, student.getId()));
		} else {
			model.addAttribute("evaluation", evaluationService.get(evalId));
		}

		return VIEW_EVALUATION_VIEW;
	}

	@Secured(value = "ROLE_TEACHER")
	@GetMapping("/create")
	public String createEvaluationView(Model model, @CurrentUser Teacher teacher) {
		model.addAttribute("createEvaluationForm", new CreateEvaluationForm());
		model.addAttribute("grades", gradeService.getAll());
		if(teacher != null)
			model.addAttribute("qcmList", qcmService.getQcmByTeacher(teacher));
		else
			model.addAttribute("qcmList", qcmService.getAll());
		model.addAttribute("courses", courseService.getAll());

		return CREATE_EVALUATION_VIEW;
	}

	@Secured(value = "ROLE_TEACHER")
	@PostMapping("/create")
	public String createEvaluation(@Valid @ModelAttribute CreateEvaluationForm createEvaluationForm, BindingResult bindingResult, @CurrentUser Teacher teacher,
								   RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("evaluation.create.error", null, LocaleContextHolder.getLocale())));
			return "redirect:" + CREATE_EVALUATION_URL;
		}

		Evaluation evaluation = createEvaluationForm.getEvaluation();
		evaluation.setTeacher(teacher);
		evaluationService.add(evaluation);

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("evaluation.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + PROPOSED_EVALUATIONS_URL;
	}

	@Secured(value = "ROLE_STUDENT")
	@GetMapping("/take")
	public String takeEvaluation(@RequestParam String evaluationId, Model model,
								 @CurrentUser Student student, RedirectAttributes redirectAttributes) {

		if (evaluationService.hasStudentTakenEvaluation(student.getId(), evaluationId)) {
			redirectAttributes.addAttribute("flash", MessageUtil.returnWarning(
					messageSource.getMessage("evaluation.already.taken", null, LocaleContextHolder.getLocale())));
			return "redirect:" + VIEW_EVALUATION_VIEW;
		}

		Evaluation evaluation = evaluationService.get(evaluationId);

		if(evaluation != null) {
			if (evaluation.getQcm() == null) {
				redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
						messageSource.getMessage("error", null, LocaleContextHolder.getLocale())));
				return "redirect:" + VIEW_EVALUATION_VIEW;
			}

			model.addAttribute("qcm", evaluation.getQcm());
			model.addAttribute("validateQcmForm", new ValidateQcmForm(evaluation.getId(), evaluation.getQcm().getId()));
			return TAKE_EVALUATION_VIEW;
		}

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnWarning(
				messageSource.getMessage("error", null, LocaleContextHolder.getLocale())));

		return "redirect:" + VIEW_EVALUATION_VIEW;
	}

	@Secured(value = "ROLE_STUDENT")
	@PostMapping("/validate")
	public String validateEvaluation(Model model, @Valid ValidateQcmForm validateQcmForm, BindingResult bindingResult,
									 RedirectAttributes redirectAttributes, @CurrentUser Student student) {
		if(bindingResult.hasErrors()){
			model.addAttribute("flash", MessageUtil.returnDanger("qcm.view.validate.error"));
			return TAKE_EVALUATION_VIEW;
		}

		Set<String> answersIds = new HashSet<>(Arrays.asList(validateQcmForm.getSelectedAnswers()));

		int evaluationMark = evaluationService.
				takeEvaluation(validateQcmForm.getEvalId(), validateQcmForm.getQcmId(), student, answersIds, new Date());

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("qcm.validate.success", new Object[]{evaluationMark}, LocaleContextHolder.getLocale())));

		return "redirect:" + AVAILABLE_EVALUATIONS_URL;
	}
}
