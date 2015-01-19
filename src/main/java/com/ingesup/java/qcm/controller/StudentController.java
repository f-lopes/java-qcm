package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.security.CurrentUser;
import com.ingesup.java.qcm.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by flopes on 19/01/2015.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    private static final String STUDENT_INFO_VIEW = "";
    private static final String STUDENT_MARKS_VIEW = "";

    private EvaluationService evaluationService;

    @Autowired
    public StudentController(EvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @Secured(value = "ROLE_STUDENT")
    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String accountInfo(Model model, @CurrentUser User currentUser) {
        model.addAttribute("currentUser", currentUser);

        return STUDENT_INFO_VIEW;
    }

    @Secured(value = "ROLE_STUDENT")
    @RequestMapping(value = "/marks", method = RequestMethod.GET)
    public String studentMarks(Model model, @CurrentUser User currentUser) {
        model.addAttribute("evaluations", evaluationService.getTakenEvaluationsForStudent(currentUser.getId()));

        return STUDENT_MARKS_VIEW;
    }
}
