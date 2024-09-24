package com.jsp.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculaterTest {

	@BeforeAll
	public static void beforeclass() {
		System.out.println("@beforeall");
	}

	@BeforeEach
	public void beforeEach() {
		System.out.println("@beforeEach");
	}

	@AfterAll
	public static void afterAll() {
		System.out.println("@afterall");
	}

	@AfterEach
	public void afterEach() {
		System.out.println("@afterEach");
	}

	@Test
	public void testSaveWithNegetiveValues() {
		Calculater calculater = new Calculater();
		int result = calculater.add(-5, 0);
		assertEquals(0, result);
	}

	@Test
	public void testSaveWithPositivrValues() {
		Calculater calculater = new Calculater();
		int result = calculater.add(5, 3);
		assertEquals(8, result);
	}
}
