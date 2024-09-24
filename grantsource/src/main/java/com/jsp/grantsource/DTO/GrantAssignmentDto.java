package com.jsp.grantsource.DTO;

import lombok.Data;

@Data
public class GrantAssignmentDto {
	private long planId;
	private String employeeNumber;
	private String bond;
	private long numberOfGrante;
	private double grantPrice;
	private long frequency;
}
