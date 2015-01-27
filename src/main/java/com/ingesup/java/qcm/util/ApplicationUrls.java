package com.ingesup.java.qcm.util;

/**
 * Created by lopes_f on 1/27/2015.
 * <florian.lopes@outlook.com>
 */
public enum ApplicationUrls {

	;

	public enum User {

		USERS_NAMESPACE("/users");

		private String value;

		User(String value) {
			this.value = value;
		}

		/*public static final String ALL = USERS_NAMESPACE;
		public static final String JSON_USERS = USERS_NAMESPACE + "/json";
		public static final String USER = USERS_NAMESPACE + "/{id}";
		public static final String ADD_USER = USERS_NAMESPACE + "/add";*/
	}

	public enum Student {

	}

	public enum Teacher {

	}

	public enum Evaluation {

	}

	public enum Qcm {

	}

	public enum Course {

	}
}
