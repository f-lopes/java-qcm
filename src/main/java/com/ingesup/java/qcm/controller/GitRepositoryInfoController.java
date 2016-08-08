package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.util.GitRepositoryState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by flopes on 02/08/2016.
 * <florian.lopes@outlook.com>
 */
@Controller
public class GitRepositoryInfoController {

    private final GitRepositoryState gitRepositoryState;

    @Autowired
    public GitRepositoryInfoController(GitRepositoryState gitRepositoryState) {
        this.gitRepositoryState = gitRepositoryState;
    }

    @RequestMapping(value = "/git/info", method = RequestMethod.GET)
    @ResponseBody
    public GitRepositoryState getGitRepositoryState() {
        return this.gitRepositoryState;
    }
}
