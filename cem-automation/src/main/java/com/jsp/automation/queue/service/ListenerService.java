package com.jsp.automation.queue.service;

import com.jsp.automation.dto.WorkflowTransactionContext;

public interface ListenerService {
	public void listenerEvent(WorkflowTransactionContext workflowTransactionContext);
	
	public void listenerEventMessage(String message);
	
}
