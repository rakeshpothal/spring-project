package com.jsp.usm.dto;

import lombok.Data;

@Data
public class SmsDto {
	private long contactNumber;
	private String subject;
	private String messageContect;
}
