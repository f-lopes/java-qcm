package com.ingesup.java.qcm.security;

import com.ingesup.java.qcm.JavaQcmApplication;
import com.ingesup.java.qcm.entity.Evaluation;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.service.EvaluationService;
import com.ingesup.java.qcm.service.StudentService;
import com.ingesup.java.qcm.service.TeacherService;
import com.ingesup.java.qcm.service.UserService;
import com.ingesup.java.qcm.util.ApplicationUrls;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by lopes_f on 02/02/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JavaQcmApplication.class})
@Sql(scripts = "/sql/test-data.sql" )
@WebAppConfiguration
public class SecurityTests {

    private static final String LOGIN_URL = "http://localhost/secure/login";

    private static final String ALL_USERS_URL = ApplicationUrls.User.ALL.getUrl();
	private static final String AVAILABLE_EVALUATIONS_URL = ApplicationUrls.Evaluation.AVAILABLE.getUrl();
	private static final String PROPOSED_EVALUATIONS_URL = ApplicationUrls.Evaluation.PROPOSED.getUrl();

	@Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

	@Autowired
	private TeacherService teacherService;

    @Autowired
	private StudentService studentService;

    @Autowired
	private EvaluationService evaluationService;

    @Autowired
    private UserService userService;

    private Teacher teacher;
    private Student student;
    private User adminUser;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(springSecurityFilterChain)
                .build();

        this.teacher = getTeacher();
        this.student = getStudent();
        this.adminUser = getAdminUser();
    }

    @Test
    public void allUsersPageShouldRequireLogin() throws Exception {
        mvc.perform(get(ALL_USERS_URL))
                .andExpect(loginPage());
    }

    @Test
    public void allUsersPageShouldRequireAdminRole() throws Exception {
        RequestBuilder request = get(ALL_USERS_URL)
                .with(user(student));

        mvc.perform(request).andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void availableEvaluationsShouldRequireStudentRole() throws Exception {
		RequestBuilder request = get(AVAILABLE_EVALUATIONS_URL);

		mvc.perform(request).andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

	@Test
	public void teacherShouldSeeHisProposedEvaluations() throws Exception {
		RequestBuilder request = get(PROPOSED_EVALUATIONS_URL)
				.with(user(teacher));

        List<Evaluation> proposedEvaluationsByTeacher = evaluationService.getEvaluationsByTeacher(teacher);

		mvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("evaluations", proposedEvaluationsByTeacher))
				.andExpect(MockMvcResultMatchers.view().name("evaluation/list"));
	}

	@Test
    public void teacherShouldSeeHisFinishedProposedEvaluations() throws Exception {
		RequestBuilder request = get(PROPOSED_EVALUATIONS_URL + "?finished=true")
				.with(user(teacher));

        List<Evaluation> proposedEvaluationsByTeacher = evaluationService.getFinishedEvaluationsByTeacher(teacher);

		mvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.view().name("evaluation/evaluationsResults"))
				.andExpect(MockMvcResultMatchers.model().attribute("evaluations", proposedEvaluationsByTeacher));
    }

    private static ResultMatcher loginPage() {
        return new ResultMatcher() {
            @Override
            public void match(MvcResult mvcResult) throws Exception {
                MockMvcResultMatchers.status().is3xxRedirection().match(mvcResult);
                MockMvcResultMatchers.redirectedUrl(LOGIN_URL).match(mvcResult);
            }
        };
    }

    private Teacher getTeacher() {
        return teacherService.get("4028818b4b27e139014b27ea63310000");
    }

    private Student getStudent() {
        return studentService.get("4028818b4b27c5c8014b27d2580f0002");
    }

    private User getAdminUser() {
       return userService.get("4028818b4b22968e014b2297cb6b0009");
    }
}
