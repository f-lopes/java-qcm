package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Course;
import com.ingesup.java.qcm.repository.CourseRepository;
import com.ingesup.java.qcm.service.CourseService;
import com.ingesup.java.qcm.service.impl.CourseServiceImpl;
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
public class CourseControllerTests {

	private static final String COURSES_URL = "/courses";
	private static final String COURSES_VIEW = "course/list";

	private static final String EXPECTED_COURSE_ID = "1";
	private static final String COURSE_URL = "/courses/" + EXPECTED_COURSE_ID;
	private static final String COURSE_VIEW = "course/view";

	private static Course EXPECTED_COURSE;

	private static final List<Course> EXPECTED_COURSES = new ArrayList<Course>() {{
		add(EXPECTED_COURSE);
		add(new Course("Ruby"));
	}};

	private MockMvc mockMvc;

	private CourseService courseService;

	@Mock
	private CourseRepository courseRepository;

	@Before
	public void setUp() {
		EXPECTED_COURSE = new Course("Java");
		EXPECTED_COURSE.setId(EXPECTED_COURSE_ID);

		when(this.courseRepository.findAll()).thenReturn(EXPECTED_COURSES);
		when(this.courseRepository.findOne(EXPECTED_COURSE_ID)).thenReturn(EXPECTED_COURSE);

		this.courseService = new CourseServiceImpl(this.courseRepository);

		this.mockMvc = MockMvcBuilders
				.standaloneSetup(new CourseController(this.courseService, null, null))
				.build();
	}

	@Test
	public void testAllCourses() throws Exception {
		this.mockMvc.perform(get(COURSES_URL))
				.andExpect(view().name(COURSES_VIEW))
				.andExpect(model().attribute("courses", EXPECTED_COURSES));
	}

	@Test
	public void testCourse() throws Exception {
		this.mockMvc.perform(get(COURSE_URL))
				.andExpect(view().name(COURSE_VIEW))
				.andExpect(model().attribute("course", EXPECTED_COURSE));
	}
}
