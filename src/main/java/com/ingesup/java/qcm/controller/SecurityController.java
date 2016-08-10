package com.ingesup.java.qcm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping(value = "/secure", method = RequestMethod.GET)
public class SecurityController {

	private static final String LOGIN_VIEW = "security/login";

	@GetMapping("/login")
	public String login() {
		return LOGIN_VIEW;
	}
}