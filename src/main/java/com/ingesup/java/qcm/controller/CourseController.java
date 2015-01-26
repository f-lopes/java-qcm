package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.form.AddCourseForm;
import com.ingesup.java.qcm.service.CourseService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by lopes_f on 1/24/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

	private static final String COURSES_URL = "/courses";

	private static final String COURSES_VIEW = "course/list";
	private static final String COURSE_VIEW = "course/view";
	private static final String ADD_COURSE_VIEW = "course/add";

	@Autowired
	private CourseService courseService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(method = RequestMethod.GET)
	public String allCourses(Model model) {
		model.addAttribute("courses", courseService.getAll());

		return COURSES_VIEW;
	}

	@RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
	public String viewCourse(Model model, @PathVariable String courseId) {
		model.addAttribute("course", courseService.get(courseId));

		return COURSE_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addCourseView(Model model) {
		model.addAttribute("addCourseForm", new AddCourseForm());

		return ADD_COURSE_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addCourse(@Valid AddCourseForm addCourseForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return ADD_COURSE_VIEW;
		}

		courseService.add(addCourseForm.getCourse());

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("course.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + COURSES_URL;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteCourse(@RequestBody String courseId) {
		courseService.remove(courseId);

		return COURSES_VIEW;
	}
}
