package com.ingesup.java.qcm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/")
public class HomeController {

	private static final String HOME_VIEW = "home";

	public String home() {
		return HOME_VIEW;
	}
}
