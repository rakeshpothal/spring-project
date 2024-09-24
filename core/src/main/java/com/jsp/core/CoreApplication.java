package com.jsp.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.jsp.core.util.AppSourceUtil;

@SpringBootApplication (
		scanBasePackages = "com.jsp.core",
		exclude = {DataSourceAutoConfiguration.class}
		)
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		
		AppSourceUtil.storeRegisterService("modulename3333", "servicename3333","url4333");

	}

}
