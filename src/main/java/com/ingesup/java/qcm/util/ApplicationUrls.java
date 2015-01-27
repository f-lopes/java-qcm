package com.ingesup.java.qcm.util;

/**
 * Created by lopes_f on 1/27/2015.
 * <florian.lopes@outlook.com>
 */
public enum ApplicationUrls {

	;

	public enum User {

		USERS_NAMESPACE("/users"),

		ALL(""),
		JSON_USERS("/json"),
		USER("/{id}"),
		ADD_USER("/add");

		private String value;

		User(String value) {
			this.value = value;
		}

		public String getUrl() {
			return USERS_NAMESPACE + this.value;
		}

		@Override
		public String toString() {
			return this.value;
		}
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
