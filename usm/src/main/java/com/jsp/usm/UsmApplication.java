package com.jsp.usm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
	(
		scanBasePackages = { "com.jsp.usm", "com.jsp.util" },
		exclude = {DataSourceAutoConfiguration.class}
	)
//@SpringBootApplication(scanBasePackages = "com.jsp")

public class UsmApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsmApplication.class, args);
//		System.out.println("main is running");

	}

}
