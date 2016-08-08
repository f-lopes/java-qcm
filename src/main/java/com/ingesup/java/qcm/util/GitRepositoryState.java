package com.ingesup.java.qcm.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by flopes on 02/08/2016.
 * <florian.lopes@outlook.com>
 */
@Component
@ConfigurationProperties(prefix = GitRepositoryState.PREFIX)
@Getter
@Setter
public class GitRepositoryState {

    public static final String PREFIX = "git";

    String tags;
    String branch;
    String dirty;
    String remoteOriginUrl;

    String commitId;
    String commitIdAbbrev;
    String describe;
    String describeShort;
    String commitUserName;
    String commitUserEmail;
    String commitMessageFull;
    String commitMessageShort;
    String commitTime;
    String closestTagName;
    String closestTagCommitCount;

    String buildUserName;
    String buildUserEmail;
    String buildTime;
    String buildHost;
    String buildVersion;
}
