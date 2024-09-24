package com.jsp.grantsource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(
		scanBasePackages = {"com.jsp.grantsource"},
		exclude = {DataSourceAutoConfiguration.class}
		)
public class GrantsourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrantsourceApplication.class, args);
//		System.out.println("main is running");
	}

}
