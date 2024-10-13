package com.jsp.automation.node.service;

import com.jsp.automation.dto.NodeExecutionContext;
import com.jsp.automation.dto.NodeExecutionResult;
import com.jsp.automation.dto.WorkflowTransactionContext;

public interface NodeExecutionService {
	public NodeExecutionResult executeNode(WorkflowTransactionContext workflowTransactionContext, NodeExecutionContext nodeExecutionContext);
}
