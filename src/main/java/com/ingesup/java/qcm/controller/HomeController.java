package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.service.UserService;
import com.ingesup.java.qcm.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
public class HomeController {

	private static final String HOME_VIEW = "home/home";

	private final UserService userService;

	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return HOME_VIEW;
	}

	@RequestMapping(value = "/login-as-admin", method = RequestMethod.POST)
	public String loginAsAdmin() {
		SecurityUtil.loginUser(userService.getByEmail("admin@admin.fr"));

		return "redirect:/";
	}

	@RequestMapping(value = "/login-as-teacher", method = RequestMethod.POST)
	public String loginAsTeacher() {
		SecurityUtil.loginUser(userService.getByEmail("teacher@teacher.fr"));

		return "redirect:/";
	}

	@RequestMapping(value = "/login-as-student", method = RequestMethod.POST)
	public String loginAsStudent() {
		SecurityUtil.loginUser(userService.getByEmail("student@student.fr"));

		return "redirect:/";
	}
}
