package com.ingesup.java.qcm.taglib;

import com.ingesup.java.qcm.util.ApplicationUrls;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lopes_f on 1/27/2015.
 * <florian.lopes@outlook.com>
 */
public class QcmUrlTag extends SimpleTagSupport {

	private static final String UNDERSCORE = "_";
	private static final String DOT = ".";

	private final Map<String, String> urlsValues = new HashMap<>();

	private boolean relative = true;
	private String key;

	public QcmUrlTag() {
		for (ApplicationUrls.User userUrls : ApplicationUrls.User.values()) {
			String urlKey = StringUtils.replaceChars("user." + userUrls.name().toLowerCase(), UNDERSCORE, DOT);

			urlsValues.put(urlKey, userUrls.getUrl());
		}
	}

	@Override
	public void doTag() throws JspException, IOException {
		StringBuilder resolvedUrlStringBuilder = new StringBuilder();
		ServletRequest servletRequest = ((PageContext) getJspContext()).getRequest();

		if (relative) {
			resolvedUrlStringBuilder.append(servletRequest.getRemoteHost()).append(urlsValues.get(key));
		} else {
			resolvedUrlStringBuilder.append(servletRequest.getServerName()).append(":").append(servletRequest.getLocalPort());
		}

		getJspContext().getOut().write(resolvedUrlStringBuilder.toString());
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public boolean isRelative() {
		return relative;
	}

	public void setRelative(boolean relative) {
		this.relative = relative;
	}
}
