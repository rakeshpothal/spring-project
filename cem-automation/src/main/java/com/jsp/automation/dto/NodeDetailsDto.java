package com.jsp.automation.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;
@Data
public class NodeDetailsDto {
	private List<String> incomingNodes;
	private List<String> outgoingNodes;
	private Map<String, Object> nodeProperties;
	private String nodeType;
}
