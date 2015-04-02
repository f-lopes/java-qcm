package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.repository.GradeRepository;
import com.ingesup.java.qcm.repository.StudentRepository;
import com.ingesup.java.qcm.service.EvaluationService;
import com.ingesup.java.qcm.service.StudentService;
import com.ingesup.java.qcm.service.impl.GradeServiceImpl;
import com.ingesup.java.qcm.service.impl.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by lopes_f on 4/2/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTests {

	private static final String STUDENTS_URL = "/student/all";
	private static final String STUDENTS_VIEW = "student/list";

	private static final String EXPECTED_STUDENT_ID = "1";
	private static final String STUDENT_URL = "/student/" + EXPECTED_STUDENT_ID;
	private static final String STUDENT_VIEW = "student/marks";

	private static Student EXPECTED_STUDENT;

	private static final List<Student> EXPECTED_STUDENTS = new ArrayList<Student>() {{
		add(EXPECTED_STUDENT);
		add(new Student("firstname", "lastname", "email"));
	}};

	private MockMvc mockMvc;

	private StudentService studentService;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private EvaluationService evaluationService;

	@Mock
	private GradeRepository gradeRepository;

	@Before
	public void setUp() {
		EXPECTED_STUDENT = new Student("expectedStudentFirstname", "expectedStudentLastname", "expectedStudentEmail");
		EXPECTED_STUDENT.setId(EXPECTED_STUDENT_ID);

		when(this.studentRepository.findAll()).thenReturn(EXPECTED_STUDENTS);
		when(this.studentRepository.findOne(EXPECTED_STUDENT_ID)).thenReturn(EXPECTED_STUDENT);

		this.studentService = new StudentServiceImpl(this.studentRepository);
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders
				.standaloneSetup(new StudentController(this.studentService, this.evaluationService, null, new GradeServiceImpl(this.gradeRepository)))
				.build();
	}

	@Test
	public void testStudents() throws Exception {
		this.mockMvc.perform(get(STUDENTS_URL))
				.andExpect(view().name(STUDENTS_VIEW))
				.andExpect(model().attribute("students", EXPECTED_STUDENTS));
	}

	@Test
	public void testViewStudent() throws Exception {
		this.mockMvc.perform(get(STUDENT_URL))
				.andExpect(view().name(STUDENT_VIEW))
				.andExpect(model().attribute("student", EXPECTED_STUDENT));
	}
}
