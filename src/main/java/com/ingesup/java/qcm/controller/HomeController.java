package com.ingesup.java.qcm.controller;

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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return HOME_VIEW;
	}
}
