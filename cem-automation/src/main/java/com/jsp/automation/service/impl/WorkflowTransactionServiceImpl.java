package com.jsp.automation.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.automation.dto.NodeConfig;
import com.jsp.automation.dto.NodeExecutionContext;
import com.jsp.automation.dto.WorkflowTransactionContext;
import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.entity.WorkFlowEntity;
import com.jsp.automation.queue.service.TriggerService;
import com.jsp.automation.repository.EntityRepository;
import com.jsp.automation.repository.WorkFlowRepository;
import com.jsp.automation.service.DataManagerService;
import com.jsp.automation.service.WorkflowTransactionService;
@Service
public class WorkflowTransactionServiceImpl implements WorkflowTransactionService {
	@Autowired
	private DataManagerService dataManagerService;

	@Autowired
	private WorkFlowRepository workFlowRepository;
	
	@Autowired
	private EntityRepository entityRepository;
	
	@Autowired
	private TriggerService triggerService;
	
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
		nodeExecutionContext.setTransactionDataMap(dataManagerService.getSingleTransactionData(
				context.getEntityModel().getEntityName(), context.getEntityModel().getUniqueFields(),
				context.getEntityModel().getStatusFieldValues(), nodeId));

		return nodeExecutionContext;
	}

	@Override
	public void processManualPush(Map<String, Object> data) {
		
		String wfCode = data.get("wfCode").toString();
//		String wfId = data.get("wfId").toString();
		String uniqueValue = data.get("uniqueValue").toString();
		
		WorkFlowEntity wfEntity = workFlowRepository.findByWfCode(wfCode);
		String entityCode = wfEntity.getEntityCode();
		EntityModel entityModel = entityRepository.findByEntityCode(entityCode);
		
		
		triggerService.triggerWorkFlow(wfEntity, entityModel, uniqueValue);
	}

}
