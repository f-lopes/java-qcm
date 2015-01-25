package com.ingesup.java.qcm.database;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lopes_f on 1/25/2015.
 * <florian.lopes@outlook.com>
 */
@ConfigurationProperties(prefix = QcmDatabaseProperties.PREFIX)
public class QcmDatabaseProperties {

	public static final String PREFIX = "qcm.database";
	public static final char SEPARATOR = ',';

	private boolean initialize = false;

	private String roles;

	public boolean isInitialize() {
		return initialize;
	}

	public void setInitialize(boolean initialize) {
		this.initialize = initialize;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
