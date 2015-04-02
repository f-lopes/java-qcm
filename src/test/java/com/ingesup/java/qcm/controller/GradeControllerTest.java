package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.entity.Grade;
import com.ingesup.java.qcm.repository.GradeRepository;
import com.ingesup.java.qcm.service.GradeService;
import com.ingesup.java.qcm.service.impl.GradeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class GradeControllerTest {

	private static final String GRADES_URL = "/grades";
	private static final String GRADES_VIEW = "grades/list";

	private MockMvc mockMvc;

	@Mock
	private GradeService gradeService;

	@Mock
	private GradeRepository gradeRepository;

	private static final List<Grade> EXPECTED_GRADES = new ArrayList<Grade>() {{
		add(new Grade("B1"));
		add(new Grade("B2"));
		add(new Grade("L3"));
		add(new Grade("EXP1"));
		add(new Grade("EXP2"));
	}};

	@Before
	public void setUp() {
		when(this.gradeRepository.findAll()).thenReturn(EXPECTED_GRADES);
		this.gradeService = new GradeServiceImpl(gradeRepository);

		this.mockMvc = MockMvcBuilders
				.standaloneSetup(new GradeController(this.gradeService))
				.build();
	}

	@Test
	public void testGradesList() throws Exception {
		this.mockMvc
				.perform(get(GRADES_URL))
				.andExpect(view().name(GRADES_VIEW))
				.andExpect(model().attribute("grades", EXPECTED_GRADES));
	}
}
