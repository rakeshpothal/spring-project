package com.jsp.automation.service;

import java.util.Map;

import com.jsp.automation.dto.WorkflowTransactionContext;

public interface WorkflowTransactionService  {
public void execute(WorkflowTransactionContext context);

public void processManualPush(Map<String, Object> data);
}
