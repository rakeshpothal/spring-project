package com.jsp.automation.util;

import org.springframework.stereotype.Component;

@Component
public class RandomNumberGenerator {
	public String generateSixtenDigitRandomNumber() {
		long lower = 1000000000000000l;
		long upper = 9999999999999999l;
		long randomNum = lower + (long) (Math.random() * (upper - lower));
		return String.valueOf(randomNum);
	}
}
