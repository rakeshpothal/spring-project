package com.jsp.util;

public class Calculater {
	public int add(int a, int b) {
		if (a < 0 || b < 0) {
			return 0;
		}
		return a + b;
	}
}
