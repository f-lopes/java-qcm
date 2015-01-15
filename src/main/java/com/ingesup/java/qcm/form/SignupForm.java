package com.ingesup.java.qcm.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public class SignupForm {

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String password;
}
