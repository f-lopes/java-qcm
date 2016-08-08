package com.ingesup.java.qcm.database;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by lopes_f on 3/12/2015.
 * <florian.lopes@outlook.com>
 */
@Component
@ConfigurationProperties(prefix = DataSourceProperties.PREFIX)
public class DataSourceProperties {

	public static final String PREFIX = "qcm.datasource";

	private String url;

	private String username;

	private String password;

	private String dataSourceClassName;

	private boolean cachePrepStmts;

	private int prepStmtCacheSize;

	private int prepStmtCacheSqlLimit;

	private boolean useServerPrepStmts;

	private int maximumPoolSize;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDataSourceClassName() {
		return dataSourceClassName;
	}

	public void setDataSourceClassName(String dataSourceClassName) {
		this.dataSourceClassName = dataSourceClassName;
	}

	public boolean isCachePrepStmts() {
		return cachePrepStmts;
	}

	public void setCachePrepStmts(boolean cachePrepStmts) {
		this.cachePrepStmts = cachePrepStmts;
	}

	public int getPrepStmtCacheSize() {
		return prepStmtCacheSize;
	}

	public void setPrepStmtCacheSize(int prepStmtCacheSize) {
		this.prepStmtCacheSize = prepStmtCacheSize;
	}

	public int getPrepStmtCacheSqlLimit() {
		return prepStmtCacheSqlLimit;
	}

	public void setPrepStmtCacheSqlLimit(int prepStmtCacheSqlLimit) {
		this.prepStmtCacheSqlLimit = prepStmtCacheSqlLimit;
	}

	public boolean isUseServerPrepStmts() {
		return useServerPrepStmts;
	}

	public void setUseServerPrepStmts(boolean useServerPrepStmts) {
		this.useServerPrepStmts = useServerPrepStmts;
	}

	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}
}
