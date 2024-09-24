package com.jsp.usm.dto;

import lombok.Data;

@Data
public class AppResponseDto {
	private String statusCode;
	private String status;
	private Object data;
	private String error;
}
