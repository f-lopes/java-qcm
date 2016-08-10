package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.service.UserService;
import com.ingesup.java.qcm.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
public class HomeController {

	private static final String HOME_VIEW = "home/home";
	private static final String ACCESS_DENIED_VIEW = "accessDenied";

	private final UserService userService;

	public HomeController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String home() {
		return HOME_VIEW;
	}

	@PostMapping("/login-as-admin")
	public String loginAsAdmin() {
		SecurityUtil.loginUser(userService.getByEmail("admin@admin.fr"));

		return "redirect:/";
	}

	@PostMapping("/login-as-teacher")
	public String loginAsTeacher() {
		SecurityUtil.loginUser(userService.getByEmail("teacher@teacher.fr"));

		return "redirect:/";
	}

	@PostMapping("/login-as-student")
	public String loginAsStudent() {
		SecurityUtil.loginUser(userService.getByEmail("student@student.fr"));

		return "redirect:/";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {

		return ACCESS_DENIED_VIEW;
	}
}
