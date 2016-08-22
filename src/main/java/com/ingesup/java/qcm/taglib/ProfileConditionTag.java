package com.ingesup.java.qcm.taglib;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * Created by lopes_f on 6/3/2015.
 * <florian.lopes@outlook.com>
 * Custom tag evaluating current profiles before rendering body
 */
public class ProfileConditionTag extends RequestContextAwareTag {

    private String profiles;

    @Override
    protected int doStartTagInternal() throws Exception {
        final Environment environment = this.getRequestContext().getWebApplicationContext().getEnvironment();
        if (environment != null) {
            final String[] activeProfiles = environment.getActiveProfiles();
            if (areAllowed(activeProfiles)) {
                return EVAL_BODY_INCLUDE;
            }
        }

        return SKIP_BODY;
    }

    private boolean areAllowed(String[] profiles) {
        final String[] allowedProfiles = StringUtils.split(this.profiles, ",");
        boolean allowed = false;
        for (String profile : profiles) {
            if (ArrayUtils.contains(allowedProfiles, profile)) {
                allowed = true;
                break;
            }
        }
        return allowed;
    }

    public String getValue() {
        return profiles;
    }

    public void setValue(String profile) {
        this.profiles = profile;
    }
}
