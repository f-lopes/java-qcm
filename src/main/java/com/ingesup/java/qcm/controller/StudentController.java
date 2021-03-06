package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.EvaluationStudent;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.security.CurrentUser;
import com.ingesup.java.qcm.service.EvaluationService;
import com.ingesup.java.qcm.service.GradeService;
import com.ingesup.java.qcm.service.StudentService;
import com.ingesup.java.qcm.util.ControllerUtil;
import com.ingesup.java.qcm.util.MessageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Created by lopes_f on 19/01/2015.
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	private static final String ALL_USERS_URL = "/users";

	private static final String ALL_STUDENTS_VIEW = "student/list";
    private static final String STUDENT_INFO_VIEW = "student/info";
    private static final String STUDENT_MARKS_VIEW = "student/marks";

	private final StudentService studentService;
	private final EvaluationService evaluationService;
	private final MessageSource messageSource;
	private final GradeService gradeService;

	public StudentController(StudentService studentService, EvaluationService evaluationService,
							 MessageSource messageSource, GradeService gradeService) {
		this.studentService = studentService;
		this.evaluationService = evaluationService;
		this.messageSource = messageSource;
		this.gradeService = gradeService;
	}

	@Secured(value = "ROLE_ADMIN")
	@GetMapping("/all")
	public String allStudents(Model model, @RequestParam(required = false) String grade) {
		List<Student> students;

		if (StringUtils.isEmpty(grade)) {
			students = studentService.getAll();
		} else {
			students = studentService.getStudentsByGrade(gradeService.getGradeByName(grade));
		}

		model.addAttribute("grades", gradeService.getAll());
        model.addAttribute("selected_grade", grade);
        model.addAttribute("students", students);

		return ALL_STUDENTS_VIEW;
	}

	@Secured(value = "ROLE_STUDENT")
    @GetMapping("/personal")
    public String accountInfo(Model model, @CurrentUser User currentUser) {
        model.addAttribute("currentUser", currentUser);

        return STUDENT_INFO_VIEW;
    }

	@Secured(value = "ROLE_STUDENT")
	@GetMapping("/marks")
	public String studentMarks(Model model, @CurrentUser Student currentStudent) {
		List<EvaluationStudent> studentResults = evaluationService.getTakenEvaluationsForStudent(currentStudent);
		int average = 0;

		for (EvaluationStudent result : studentResults) {
			average += result.getMark();
		}
		if(studentResults.size() > 0) {
			average = average / studentResults.size();
		}

		model.addAttribute("results", studentResults);
		model.addAttribute("average", average);

		return STUDENT_MARKS_VIEW;
	}

    @Secured(value = "ROLE_ADMIN")
    @GetMapping("/{studentId}")
    public String studentDetail(Model model, @PathVariable String studentId, @CurrentUser Student currentStudent,
								RedirectAttributes redirectAttributes) {
		Student student = studentService.get(studentId);

		if (student == null) {
			redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
					messageSource.getMessage("student.not-found", null, LocaleContextHolder.getLocale())));

			return ControllerUtil.redirect(ALL_USERS_URL);
		}

        model.addAttribute("student", studentService.get(studentId));
        model.addAttribute("studentMarks", evaluationService.getTakenEvaluationsForStudent(currentStudent));

        return STUDENT_MARKS_VIEW;
    }
}
