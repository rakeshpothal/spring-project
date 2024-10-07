package com.jsp.automation.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.entity.NodeDetailsModel;
import com.jsp.automation.entity.WorkFlowEntity;
import com.jsp.automation.entity.WorkFlowTransactionModel;


public class NodeExecutionContext {
	Date executionStart;
	Date executionEnd;
	String executionStatus;//inprogress initially
	String nodeId;
	NodeDetailsModel prevExecutedNodeConfig; //for start its null
	NodeDetailsModel currentNodeConfig;
	List<NodeDetailsDto> nextExecutionNodeConfig;
	WorkFlowEntity workFlowEntity;
	WorkFlowTransactionModel workFlowTransactionModel;

	Map<String, Object> transactionDataMap;
	EntityModel entityModel;

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}
