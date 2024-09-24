package com.jsp.allocationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(
		scanBasePackages = {"com.jsp.allocationservice"},
		exclude = {DataSourceAutoConfiguration.class}
		)
@EnableScheduling
public class AllocationserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(AllocationserviceApplication.class, args);
	}

}
