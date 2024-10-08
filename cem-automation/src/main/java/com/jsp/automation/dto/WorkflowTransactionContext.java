package com.jsp.automation.dto;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.entity.NodeDetailsModel;
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
	NodeDetailsModel nextNodeConfig;
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

	public WorkFlowEntity getWorkFlowEntity() {
		return workFlowEntity;
	}

	public void setWorkFlowEntity(WorkFlowEntity workFlowEntity) {
		this.workFlowEntity = workFlowEntity;
	}

	public WorkFlowTransactionModel getWorkFlowTransactionModel() {
		return workFlowTransactionModel;
	}

	public EntityModel getEntityModel() {
		return entityModel;
	}

	public void setEntityModel(EntityModel entityModel) {
		this.entityModel = entityModel;
	}
	
}
