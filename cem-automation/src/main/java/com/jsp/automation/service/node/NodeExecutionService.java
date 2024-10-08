package com.jsp.automation.service.node;

import com.jsp.automation.dto.NodeExecutionContext;
import com.jsp.automation.dto.WorkflowTransactionContext;

public interface NodeExecutionService {
	public void execute(WorkflowTransactionContext workflowTransactionContext, NodeExecutionContext nodeExecutionContext);
}
