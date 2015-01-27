package com.ingesup.java.qcm.taglib;

import com.ingesup.java.qcm.util.ApplicationUrls;
import org.apache.commons.lang.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lopes_f on 1/27/2015.
 * <florian.lopes@outlook.com>
 */
public class QcmUrlTag extends SimpleTagSupport {

	private static final String UNDERSCORE = "_";
	private static final String DOT = ".";

	private Map<String, String> urlsValues;

	private String urlKey;

	public QcmUrlTag() {
		for (ApplicationUrls.User userUrls : ApplicationUrls.User.values()) {
			String urlKey = StringUtils.replaceChars(userUrls.name().toLowerCase(), UNDERSCORE, DOT);

			urlsValues.put(urlKey, userUrls.toString());
		}
	}

	@Override
	public void doTag() throws JspException, IOException {
		String resolvedUrl = ((PageContext) getJspContext()).getRequest().getRemoteHost() + urlsValues.get(urlKey);

		getJspContext().getOut().write(resolvedUrl);
	}
}
