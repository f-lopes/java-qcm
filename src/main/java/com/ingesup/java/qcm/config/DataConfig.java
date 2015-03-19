package com.ingesup.java.qcm.config;

import com.ingesup.java.qcm.database.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by lopes_f on 3/12/2015.
 * <florian.lopes@outlook.com>
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DataConfig {

	@Autowired
	private DataSourceProperties properties;

	@Bean
	public DataSource dataSource() {
		final HikariDataSource dataSource = new HikariDataSource();
		dataSource.setMaximumPoolSize(properties.getMaximumPoolSize());
		dataSource.setDataSourceClassName(properties.getDataSourceClassName());
		dataSource.setDataSourceProperties(dataSourceProperties());

		return dataSource;
	}

	public Properties dataSourceProperties() {
		final Properties dataSourceProperties = new Properties();
		dataSourceProperties.put("url", properties.getUrl());
		dataSourceProperties.put("user", properties.getUsername());
		dataSourceProperties.put("password", properties.getPassword());
		dataSourceProperties.put("password", properties.getPassword());
		dataSourceProperties.put("cachePrepStmts", properties.isCachePrepStmts());
		dataSourceProperties.put("prepStmtCacheSize", properties.getPrepStmtCacheSize());
		dataSourceProperties.put("prepStmtCacheSqlLimit", properties.getPrepStmtCacheSqlLimit());
		dataSourceProperties.put("useServerPrepStmts", properties.isUseServerPrepStmts());

		return dataSourceProperties;
	}
}
