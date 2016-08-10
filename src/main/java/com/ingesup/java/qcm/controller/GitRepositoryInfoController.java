package com.ingesup.java.qcm.controller;

import com.ingesup.java.qcm.util.GitRepositoryState;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by flopes on 02/08/2016.
 * <florian.lopes@outlook.com>
 */
@Controller
public class GitRepositoryInfoController {

    private final GitRepositoryState gitRepositoryState;

    public GitRepositoryInfoController(GitRepositoryState gitRepositoryState) {
        this.gitRepositoryState = gitRepositoryState;
    }

    @GetMapping("/git/info")
    @ResponseBody
    public GitRepositoryState getGitRepositoryState() {
        return this.gitRepositoryState;
    }
}
