package com.jsp.automation.dto;

import java.util.Date;

import lombok.Data;

@Data
public class WorkFlowDto {
	
	private String wfId;
	private String wfName;
//	private String ststusFlag;
	private String entityCode;
	private String uniqueField;
	private String sourceData;
//	private Date createdDate;
//	private Date modifiedDate;
}
