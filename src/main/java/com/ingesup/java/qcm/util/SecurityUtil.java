package com.ingesup.java.qcm.util;

import com.ingesup.java.qcm.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by lopes_f on 1/15/2015.
 * <florian.lopes@outlook.com>
 */
public class SecurityUtil {

	public static User getCurrentLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		return authentication == null ? null : (User) authentication.getPrincipal();
	}

	public static void loginUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}

		Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, user.getRoles());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
