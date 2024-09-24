package com.jsp.util;

import org.springframework.stereotype.Component;

@Component
public class TestUtil {
	public TestUtil() {
		System.out.println(getClass().getSimpleName());
	}
}
