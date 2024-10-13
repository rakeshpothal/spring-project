package com.jsp.automation.node.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.automation.dto.NodeExecutionContext;
import com.jsp.automation.dto.NodeExecutionResult;
import com.jsp.automation.dto.WorkflowTransactionContext;
import com.jsp.automation.entity.WorkFlowTransactionModel;
import com.jsp.automation.entity.WorkflowTransactionLogModel;
import com.jsp.automation.node.service.NodeExecutionService;
import com.jsp.automation.node.service.NodeRegisterService;
import com.jsp.automation.repository.WorkflowTransactionLogModelRepository;

@Service
public class NodeExecutionServiceImpl implements NodeExecutionService {
	
	@Autowired
	private WorkflowTransactionLogModelRepository workflowTransactionLogModelRepository;

	@Override
	public NodeExecutionResult executeNode(WorkflowTransactionContext workflowTransactionContext,
			NodeExecutionContext nodeExecutionContext) {

		try {
			NodeExecutionService nodeImlementation = new NodeRegisterService().getNodeImlementation("start");
			workflowTransactionContext.setExecutionStatus("INPROGRESS");
			nodeExecutionContext.setExecutionStatus("INPROGRESS");
			workflowTransactionContext
					.setRemarks(nodeExecutionContext.getNodeId() + "_" + nodeExecutionContext.getExecutionStart());

			insertWorkflowTransactionLog(workflowTransactionContext, nodeExecutionContext);

			return null;

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private void insertWorkflowTransactionLog(WorkflowTransactionContext workflowTransactionContext,
			NodeExecutionContext nodeExecutionContext) {
		WorkFlowTransactionModel transactionModel = workflowTransactionContext.getWorkFlowTransactionModel();
		WorkflowTransactionLogModel logModel = new WorkflowTransactionLogModel();
		
		logModel.setCreatedDate(new Date());
		logModel.setCurrentNodeId(nodeExecutionContext.getCurrentNodeConfig().getNodeId());
		logModel.setCurrentNodeType(nodeExecutionContext.getCurrentNodeConfig().getNodeType());
		logModel.setCurrentThread(null);
		logModel.setExecutionSignal(null);
		logModel.setPreviousNodeId(nodeExecutionContext.getPrevExecutedNodeConfig().getNodeId());
		logModel.setRemarks(null);
		logModel.setStatusFlag(null);
		logModel.setTransactionEndDtae(transactionModel.getTransactionEndDate());
		logModel.setTransactionId(transactionModel.getTransactionId());
		logModel.setTransactionStartDate(transactionModel.getTransactionStartDate());
		logModel.setTransactionUniqueValue(transactionModel.getTransactionUniqueValue());
		logModel.setTriggerDate(nodeExecutionContext.getExecutionStart());
		logModel.setWfid(transactionModel.getWfId());
		
		workflowTransactionLogModelRepository.save(logModel);

	}

}
