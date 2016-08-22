package com.ingesup.java.qcm.handler;

import com.ingesup.java.qcm.util.GitRepositoryState;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by flopes on 12/08/2016.
 */
public class GitInfoInterceptor extends HandlerInterceptorAdapter {

    private static final String GIT_COMMIT_ID_MODEL_ATTRIBUTE = "gitCommitId";
    private static final String GIT_COMMIT_TIME_MODEL_ATTRIBUTE = "gitCommitTime";

    private final GitRepositoryState gitRepositoryState;

    public GitInfoInterceptor(GitRepositoryState gitRepositoryState) {
        this.gitRepositoryState = gitRepositoryState;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        modelAndView.getModel().put(GIT_COMMIT_ID_MODEL_ATTRIBUTE, this.gitRepositoryState.getCommitIdAbbrev());
        modelAndView.getModel().put(GIT_COMMIT_TIME_MODEL_ATTRIBUTE, this.gitRepositoryState.getCommitTime());
        super.postHandle(request, response, handler, modelAndView);
    }
}
