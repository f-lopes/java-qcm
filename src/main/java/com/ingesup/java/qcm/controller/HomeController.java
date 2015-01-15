package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.RoleEnum;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.service.UserService;
import org.apache.commons.lang.StringUtils;
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

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return HOME_VIEW;
	}

	@RequestMapping("/createAdminUser")
	public String createAdminUser() {

		User adminUser = new User("admin", "admin", "admin@admin.fr", "admin");
		adminUser.addRole(RoleEnum.ROLE_ADMIN);

		userService.add(adminUser);

		return StringUtils.EMPTY;
	}
}
