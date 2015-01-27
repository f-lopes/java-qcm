package com.ingesup.java.qcm.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by lopes_f on 1/27/2015.
 * <florian.lopes@outlook.com>
 */
public class QcmUrlTag extends SimpleTagSupport {

	private String qcmUrl;

	@Override
	public void doTag() throws JspException, IOException {
//		getJspContext().getOut().write(UrlUtils.getProp(propUrl));
	}
}
