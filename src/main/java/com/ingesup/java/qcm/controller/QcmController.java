package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.form.QcmForm;
import com.ingesup.java.qcm.service.QcmService;
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
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/qcm")
public class QcmController {

	private static final String ADD_QCM_VIEW = "/qcm/create";
	private static final String ALL_QCM_VIEW = "/qcm/list";

	@Autowired
	private QcmService qcmService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	public QcmController(QcmService qcmService) {
		this.qcmService = qcmService;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String qcmList(Model model) {
		model.addAttribute("qcmList", qcmService.getAll());

		return ALL_QCM_VIEW;
	}

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String addQcmView(Model model) {
		model.addAttribute("qcmForm", new QcmForm());

		return ADD_QCM_VIEW;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String saveQcm(Model model, @Valid QcmForm qcmForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// handle errors

			model.addAttribute(MessageUtil.returnDanger("qcm.create.error"));

			return ADD_QCM_VIEW;
		}

		qcmService.add(qcmForm.getQcm());

		redirectAttributes.addFlashAttribute(MessageUtil.returnSuccess(
				messageSource.getMessage("qcm.create.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + ADD_QCM_VIEW;
	}
}
