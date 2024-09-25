package com.jsp.automation.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

/**its a Dto class
 * 
 */
@Data
public class EntityDto {
	private String entityCode;
	private String entityName;
	private String entityDescription;
	private List<String> uniqueFields;
	private List<String> templateFields;
	private List<Map<String, String>> statusFieldValues;
	private String remarks;
}
