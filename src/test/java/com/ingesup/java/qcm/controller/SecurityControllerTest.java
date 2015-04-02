package com.ingesup.java.qcm.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Created by lopes_f on 4/2/2015.
 * <florian.lopes@outlook.com>
 */
public class SecurityControllerTest {

	private static final String LOGIN_URL = "/secure/login";
	private static final String LOGIN_VIEW = "security/login";

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(new SecurityController())
				.build();
	}

	@Test
	public void testLoginView() throws Exception {
		this.mockMvc.perform(get(LOGIN_URL))
				.andExpect(view().name(LOGIN_VIEW));
	}
}
