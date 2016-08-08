package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.util.GitRepositoryState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Created by flopes on 02/08/2016.
 * <florian.lopes@outlook.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class GitRepositoryInfoControllerTest {

    private static final String GIT_REPOSITORY_INFO_URL = "/git/info";
    private static final String BUILD_VERSION = "0.0.1-SNAPSHOT";
    private static final String BUILD_USER_NAME = "Florian Lopes";
    private static final String BUILD_USER_EMAIL = "florian.lopes@outlook.com";
    private static final String BUILD_TIME = "00:00:30";
    private static final String BUILD_HOST = "localhost";
    private static final String BRANCH = "dev";
    private static final String COMMIT_ID = "dcff9c7";

    private GitRepositoryState gitRepositoryState;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.gitRepositoryState = getRepositoryState();

        this.mockMvc = MockMvcBuilders.standaloneSetup(new GitRepositoryInfoController(this.gitRepositoryState))
                .build();
    }

    @Test
    public void get_shouldReturnGitRepositoryStateBean() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(GIT_REPOSITORY_INFO_URL)
            .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.branch", is(BRANCH)))
                .andExpect(jsonPath("$.buildHost", is(BUILD_HOST)))
                .andExpect(jsonPath("$.buildTime", is(BUILD_TIME)))
                .andExpect(jsonPath("$.buildUserEmail", is(BUILD_USER_EMAIL)))
                .andExpect(jsonPath("$.buildUserName", is(BUILD_USER_NAME)))
                .andExpect(jsonPath("$.buildVersion", is(BUILD_VERSION)))
                .andExpect(jsonPath("$.commitId", is(COMMIT_ID)));
    }

    private GitRepositoryState getRepositoryState() {
        final GitRepositoryState gitRepositoryState = new GitRepositoryState();
        gitRepositoryState.setBranch(BRANCH);
        gitRepositoryState.setBuildHost(BUILD_HOST);
        gitRepositoryState.setBuildTime(BUILD_TIME);
        gitRepositoryState.setBuildUserEmail(BUILD_USER_EMAIL);
        gitRepositoryState.setBuildUserName(BUILD_USER_NAME);
        gitRepositoryState.setBuildVersion(BUILD_VERSION);
        gitRepositoryState.setCommitId(COMMIT_ID);
        return gitRepositoryState;
    }
}
