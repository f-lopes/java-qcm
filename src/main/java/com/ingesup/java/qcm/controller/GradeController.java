package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lopes_f on 3/12/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping(value = "/grades")
@Secured(value = "{ROLE_ADMIN, ROLE_TEACHER}")
public class GradeController {

	private static final String ALL_GRADES_VIEW = "grades/list";

	private final GradeService gradeService;

	@Autowired
	public GradeController(GradeService gradeService) {
		this.gradeService = gradeService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String viewGrades(Model model) {
		model.addAttribute("grades", gradeService.getAll());

		return ALL_GRADES_VIEW;
	}
}
