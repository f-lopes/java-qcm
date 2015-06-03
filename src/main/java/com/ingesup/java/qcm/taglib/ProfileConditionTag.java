package com.ingesup.java.qcm.taglib;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * Created by lopes_f on 6/3/2015.
 * <florian.lopes@outlook.com>
 * Custom tag evaluating current profile before rendering body
 */
public class ProfileConditionTag extends RequestContextAwareTag {

    private String profile;

    @Override
    protected int doStartTagInternal() throws Exception {
        final Environment environment = this.getRequestContext().getWebApplicationContext().getEnvironment();
        if (environment != null) {
            final String[] profiles = environment.getActiveProfiles();
            if (ArrayUtils.contains(profiles, this.profile)) {
                return EVAL_BODY_INCLUDE;
            }
        }

        return SKIP_BODY;
    }

    public String getValue() {
        return profile;
    }

    public void setValue(String profile) {
        this.profile = profile;
    }
}
