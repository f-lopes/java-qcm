package com.ingesup.java.qcm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
		*/
@Controller
@RequestMapping("/secure")
public class SecurityController {

	private static final String LOGIN_VIEW = "";
	private static final String SIGNUP_VIEW = "";

	public String login() {
		return LOGIN_VIEW;
	}
}