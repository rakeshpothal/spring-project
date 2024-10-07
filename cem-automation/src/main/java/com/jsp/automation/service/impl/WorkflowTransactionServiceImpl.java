package com.jsp.automation.service.impl;

import com.jsp.automation.dto.NodeConfig;
import com.jsp.automation.dto.NodeExecutionContext;
import com.jsp.automation.dto.WorkflowTransactionContext;
import com.jsp.automation.service.WorkflowTransactionService;

public class WorkflowTransactionServiceImpl implements WorkflowTransactionService {

	@Override
	public void execute(WorkflowTransactionContext context) {
		// TODO Auto-generated method stub
		
	}
	
	private NodeExecutionContext createNodeExecutionContext(WorkflowTransactionContext context, NodeConfig nodeConfig, String nodeId) {
		return null;
	}

}
