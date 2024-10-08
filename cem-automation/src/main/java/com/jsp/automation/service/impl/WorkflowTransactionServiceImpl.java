package com.jsp.automation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jsp.automation.dto.NodeConfig;
import com.jsp.automation.dto.NodeExecutionContext;
import com.jsp.automation.dto.WorkflowTransactionContext;
import com.jsp.automation.service.DataManagerService;
import com.jsp.automation.service.WorkflowTransactionService;

public class WorkflowTransactionServiceImpl implements WorkflowTransactionService {
	@Autowired
	private DataManagerService dataManagerService;

	@Override
	public void execute(WorkflowTransactionContext context) {
		List<NodeConfig> startNodeConfig = context.getWorkFlowEntity().getStartNodeConfig();
		for (NodeConfig nodeConfig : startNodeConfig) {

			NodeExecutionContext nodeExecutionContext = createNodeExecutionContext(context, nodeConfig,
					nodeConfig.getNodeId());
			context.setCurrentNodeExecutionContext(nodeExecutionContext);
		}

	}

	private NodeExecutionContext createNodeExecutionContext(WorkflowTransactionContext context, NodeConfig nodeConfig,
			String nodeId) {
		NodeExecutionContext nodeExecutionContext = new NodeExecutionContext();
		nodeExecutionContext.setExecutionStart(new Date());
		nodeExecutionContext.setExecutionStatus("inprogress");
		nodeExecutionContext.setNodeId(nodeId);
		nodeExecutionContext.setPrevExecutedNodeConfig(nodeConfig.getIncomingNode().get(0));
		nodeExecutionContext.setCurrentNodeConfig(nodeConfig);
		nodeExecutionContext.setNextExecutionNodeConfig(nodeConfig.getOutgoingNode());
		nodeExecutionContext.setWorkFlowEntity(context.getWorkFlowEntity());
		nodeExecutionContext.setWorkFlowTransactionModel(context.getWorkFlowTransactionModel());
		nodeExecutionContext.setEntityModel(context.getEntityModel());
		dataManagerService.getSingleTransactionData(context.getEntityModel().getEntityCode(),
				context.getEntityModel().getUniqueFields(), context.getEntityModel().getStatusFieldValues(), nodeId);

		return nodeExecutionContext;
	}

}
