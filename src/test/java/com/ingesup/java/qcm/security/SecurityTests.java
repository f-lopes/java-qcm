package com.ingesup.java.qcm.security;

import com.ingesup.java.qcm.JavaQcmApplication;
import com.ingesup.java.qcm.entity.RoleEnum;
import com.ingesup.java.qcm.entity.Student;
import com.ingesup.java.qcm.entity.Teacher;
import com.ingesup.java.qcm.entity.User;
import com.ingesup.java.qcm.util.ApplicationUrls;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

/**
 * Created by lopes_f on 02/02/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JavaQcmApplication.class})
@WebAppConfiguration
public class SecurityTests {

    private static final String LOGIN_URL = "http://localhost/login";

    private static final String ALL_USERS_URL = ApplicationUrls.User.ALL.getUrl();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

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
        // TODO with anonymous user
        mvc.perform(get(ALL_USERS_URL))
                .andExpect(loginPage());
    }

    @Test
    public void allUsersPageShouldRequireAdminRole() throws Exception {
        RequestBuilder request = get(ALL_USERS_URL)
                .with(user(student));

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void availableEvaluationsShouldRequireStudentRole() {
        // TODO with admin user
    }

    @Test
    public void teacherShouldSeeHisProposedEvaluations() {

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
        Teacher teacher = new Teacher();
        teacher.addRole(RoleEnum.ROLE_TEACHER);
        return teacher;
    }

    private Student getStudent() {
        Student student = new Student();
        student.addRole(RoleEnum.ROLE_STUDENT);
        return student;
    }

    private User getAdminUser() {
        User user = new User();
        user.addRole(RoleEnum.ROLE_ADMIN);
        return user;
    }
}
