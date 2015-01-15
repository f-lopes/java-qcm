package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.form.AddUserForm;
import com.ingesup.java.qcm.service.UserService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
public class UserController {

	private static final String ALL_USERS_VIEW = "user/list";
	private static final String ADD_USER_VIEW = "user/add";

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String allUsers(Model model) {
		model.addAttribute("users", userService.getAll());

		return ALL_USERS_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addUserView(Model model) {
		model.addAttribute(new AddUserForm());

		return ADD_USER_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@Valid AddUserForm addUserForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {

			return ADD_USER_VIEW;
		}

		userService.add(addUserForm.getUser());

		redirectAttributes.addFlashAttribute(MessageUtil.returnSuccess(
				messageSource.getMessage("user.add.success", null, LocaleContextHolder.getLocale())));
		return "redirect:" + ALL_USERS_VIEW;
	}
}