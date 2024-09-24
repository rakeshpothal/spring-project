package com.jsp.automation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/**
 * Its a configuration class for database configuration
 * 
 * @getDataSource
 * 
 */
@Configuration
public class DbConfig {
	/**
	 * Its a no Argument Method.
	 * It will return dataSource Object.
	 * 
	 * @return HikariDataSource
	 */
	@Bean
	public HikariDataSource getDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(System.getenv("DB_URL"));
		hikariDataSource.setUsername(System.getenv("DB_USER"));
		hikariDataSource.setPassword(System.getenv("DB_PASSWORD"));
		hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

		return hikariDataSource;

	}

}
