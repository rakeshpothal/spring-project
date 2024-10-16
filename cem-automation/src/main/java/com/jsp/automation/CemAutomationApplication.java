package com.jsp.automation;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jsp.automation.util.RandomNumberGenerator;

@EnableRabbit
@SpringBootApplication
public class CemAutomationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CemAutomationApplication.class, args);
		
	}

}
