package com.jsp.automation.dto;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class NodeConfig {
	Map<String, String> nodeProperties;
	String nodeId;
	String nodeType;
	List<NodeConfig> incomingNode;
	List<NodeConfig> outgoingNode;
	List<NodeConfig> associatedBoundaryNode;
	NodeConfig sourceNodeConfig;
	boolean isStartnode;
	boolean isEndNode;

	

}
