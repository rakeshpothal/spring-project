package com.jsp.automation.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.entity.WorkFlowEntity;
import com.jsp.automation.entity.WorkFlowTransactionModel;

import lombok.Data;

@Data
public class NodeExecutionContext {
	Date executionStart;
	Date executionEnd;
	String executionStatus;// inprogress initially
	String nodeId;
	NodeConfig prevExecutedNodeConfig; // for start its null
	NodeConfig currentNodeConfig;
	List<NodeConfig> nextExecutionNodeConfig;
	WorkFlowEntity workFlowEntity;
	WorkFlowTransactionModel workFlowTransactionModel;
	NodeExecutionResult nodeExecutionResult;
	Map<String, Object> transactionDataMap;
	EntityModel entityModel;
	

}
