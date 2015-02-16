package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.security.CurrentUser;
import com.ingesup.java.qcm.service.EvaluationService;
import com.ingesup.java.qcm.service.StudentService;
import com.ingesup.java.qcm.service.UserService;
import com.ingesup.java.qcm.util.ControllerUtil;
import com.ingesup.java.qcm.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by lopes_f on 19/01/2015.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	private static final String ALL_USERS_URL = "/users";

    private static final String STUDENT_INFO_VIEW = "student/info";
    private static final String STUDENT_MARKS_VIEW = "student/marks";

	private StudentService studentService;

	private EvaluationService evaluationService;

	@Autowired
	private MessageSource messageSource;

    @Autowired
    public StudentController(EvaluationService evaluationService, StudentService studentService) {
        this.evaluationService = evaluationService;
		this.studentService = studentService;
    }

    @Secured(value = "ROLE_STUDENT")
    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String accountInfo(Model model, @CurrentUser User currentUser) {
        model.addAttribute("currentUser", currentUser);

        return STUDENT_INFO_VIEW;
    }

	@Secured(value = "ROLE_STUDENT")
	@RequestMapping(value = "/marks", method = RequestMethod.GET)
	public String studentMarks(Model model, @CurrentUser Student currentStudent) {
		List<EvaluationStudent> studentResults = evaluationService.getTakenEvaluationsForStudent(currentStudent);
		int average = 0;

		for (EvaluationStudent result : studentResults) {
			average += result.getMark();
		}
		average = average / studentResults.size();

		model.addAttribute("results", studentResults);
		model.addAttribute("average", average);

		return STUDENT_MARKS_VIEW;
	}

    @Secured(value = "ROLE_ADMIN")
    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
    public String studentDetail(Model model, @PathVariable String studentId, @CurrentUser Student currentStudent,
								RedirectAttributes redirectAttributes) {
		Student student = studentService.get(studentId);

		if (student == null) {
			redirectAttributes.addAttribute("flash", MessageUtil.returnSuccess(
					messageSource.getMessage("student.not-found", null, LocaleContextHolder.getLocale())));

			return ControllerUtil.redirect(ALL_USERS_URL);
		}

        model.addAttribute("student", studentService.get(studentId));
        model.addAttribute("studentMarks", evaluationService.getTakenEvaluationsForStudent(currentStudent));

        return STUDENT_MARKS_VIEW;
    }
}
