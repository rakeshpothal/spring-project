package com.jsp.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppResponseDto {
	private String statusCode;
	private String status;
	private Object data;
	private String error;
}
