package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.form.QcmForm;
import com.ingesup.java.qcm.service.QcmService;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

	private QcmService qcmService;

	@Autowired
	public QcmController(QcmService qcmService) {
		this.qcmService = qcmService;
	}

	public String addQcmView(Model model) {
		model.addAttribute(new QcmForm());

		return ADD_QCM_VIEW;
	}

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String saveQcm(Model model, @Valid QcmForm qcmForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

		if (bindingResult.hasErrors()) {
			// handle errors

			model.addAttribute(MessageUtil.returnDanger("qcm.create.error"));

			return ADD_QCM_VIEW;
		}

		qcmService.add(qcmForm);

		redirectAttributes.addFlashAttribute(MessageUtil.returnSuccess("qcm.create.success"));

		return "redirect:" + ADD_QCM_VIEW;
	}
}
