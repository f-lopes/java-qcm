package com.ingesup.java.qcm.util;

/**
 * Created by lopes_f on 2/12/2015.
 * <florian.lopes@outlook.com>
 */
public class ControllerUtil {

	private static final String REDIRECT = "redirect:";

	public static String redirect(String url) {
		return REDIRECT.concat(url);
	}
}
