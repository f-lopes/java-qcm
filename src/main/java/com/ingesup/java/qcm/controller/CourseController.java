package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.form.AddCourseForm;
import com.ingesup.java.qcm.service.CourseService;
import com.ingesup.java.qcm.service.EvaluationService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by lopes_f on 1/24/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@Secured(value = "ROLE_ADMIN")
@RequestMapping("/courses")
public class CourseController {

	private static final String COURSES_URL = "/courses";

	private static final String COURSES_VIEW = "course/list";
	private static final String COURSE_VIEW = "course/view";
	private static final String ADD_COURSE_VIEW = "course/add";

	private final CourseService courseService;
	private final MessageSource messageSource;
	private final EvaluationService evaluationService;

	public CourseController(CourseService courseService, MessageSource messageSource,
							EvaluationService evaluationService) {
		this.courseService = courseService;
		this.messageSource = messageSource;
		this.evaluationService = evaluationService;
	}

	@GetMapping
	public String allCourses(Model model) {
		model.addAttribute("courses", courseService.getAll());

		return COURSES_VIEW;
	}

	@GetMapping("/{courseId}")
	public String viewCourse(Model model, @PathVariable String courseId) {
		model.addAttribute("course", courseService.get(courseId));

		return COURSE_VIEW;
	}

	@GetMapping("/add")
	public String addCourseView(Model model) {
		model.addAttribute("addCourseForm", new AddCourseForm());

		return ADD_COURSE_VIEW;
	}

	@PostMapping("/add")
	public String addCourse(@Valid AddCourseForm addCourseForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return ADD_COURSE_VIEW;
		}

		courseService.add(addCourseForm.getCourse());

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("course.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + COURSES_URL;
	}

	@PostMapping("/delete")
	public String deleteCourse(@RequestParam String courseId, RedirectAttributes redirectAttributes) {

		List<Evaluation> existingEvaluationsForCourse = evaluationService.getEvaluationsByCourseId(courseId);
		if (existingEvaluationsForCourse != null && existingEvaluationsForCourse.size() > 0) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
					messageSource.getMessage("course.delete.error.evaluation", new String[] {courseId}, LocaleContextHolder.getLocale())));

			return "redirect:" + COURSES_URL;
		}

		courseService.remove(courseId);
		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("course.delete.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + COURSES_URL;
	}
}
