package com.jsp.automation.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.entity.WorkFlowEntity;
import com.jsp.automation.entity.WorkFlowTransactionModel;

public class WorkflowTransactionContext implements Cloneable {

	WorkFlowEntity workFlowEntity;
	EntityModel entityModel;
	Date executionStart = new Date();
	Date executionEnd;
	String executionStatus;
	String remarks;
	BigInteger workflowTransactionAltKey;
	WorkFlowTransactionModel workFlowTransactionModel;
	NodeDetailsDto nextNodeConfig;
	NodeExecutionContext currentNodeExecutionContext;
	Map<String, NodeExecutionContext> currentNodeExucutionContextMap = new ConcurrentHashMap<String, NodeExecutionContext>();

	public void setWorkflowTransactionAltKey(BigInteger workflowTransactionAltKey) {
		this.workflowTransactionAltKey = workflowTransactionAltKey;
	}

	public void setWorkFlowTransactionModel(WorkFlowTransactionModel workFlowTransaction) {
		this.workFlowTransactionModel = workFlowTransaction;
		if (workFlowTransaction != null) {
			setWorkflowTransactionAltKey(workFlowTransaction.getAltKey());
		}
	}

	public NodeExecutionContext getCurrentNodeExecutionContext(String nodeId) {
		return this.currentNodeExucutionContextMap.get(nodeId);
	}

	public void setCurrentNodeExecutionContext(NodeExecutionContext currentNodeExecutionContext) {
		this.currentNodeExucutionContextMap.put(currentNodeExecutionContext.getNodeId(), currentNodeExecutionContext);
	}

	@Override
	public WorkflowTransactionContext clone() {
		try {
			WorkflowTransactionContext cloned = (WorkflowTransactionContext) super.clone();
			cloned.executionStart = this.executionStart != null ? (Date) this.executionStart.clone() : null;
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public String toString() {
		return "WorkflowTransactionContext [workFlowEntity=" + workFlowEntity + ", entityModel=" + entityModel
				+ ", executionStrat=" + executionStart + ", executionEnd=" + executionEnd + ", executionStatus="
				+ executionStatus + ", remarks=" + remarks + ", workflowTransactionAltKey=" + workflowTransactionAltKey
				+ ", workFlowTransactionModel=" + workFlowTransactionModel + ", nextNodeConfig=" + nextNodeConfig
				+ ", currentNodeExecutionContext=" + currentNodeExecutionContext + ", currentNodeExucutionContextMap="
				+ currentNodeExucutionContextMap + "]";
	}

}
