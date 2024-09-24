package com.jsp.allocationservice.dto;

import java.util.Date;

import lombok.Data;
@Data
public class AllocationDto {
	private long grantId;//planId
	private long frequency;//frequency
	private long grantNumber;//
	private long bond;//bond
	private Date grantDate;
	

}
