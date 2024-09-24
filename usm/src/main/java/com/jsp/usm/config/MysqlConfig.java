package com.jsp.usm.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
//@Profile(value = "Development")
public class MysqlConfig {

	public MysqlConfig() {
		System.out.println(this.getClass().getSimpleName() + "class loaded");
	}

	@Bean(name = "primary")
	@Primary
	public HikariDataSource getDatasource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(System.getenv("DB_URL"));
		hikariDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		hikariDataSource.setUsername(System.getenv("DB_USER"));
		hikariDataSource.setPassword(System.getenv("DB_PASSWORD"));
		return hikariDataSource;
	}

	@Bean(name = "resttemplate")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean(name = "jdbctemplate")
	public JdbcTemplate getJdbcTemplate(HikariDataSource hiakriDataSource) {
		return new JdbcTemplate(hiakriDataSource);
	}

}
