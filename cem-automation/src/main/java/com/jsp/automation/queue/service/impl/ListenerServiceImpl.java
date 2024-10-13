package com.jsp.automation.queue.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.automation.config.RabbitMQueueConfig;
import com.jsp.automation.dto.WorkflowTransactionContext;
import com.jsp.automation.entity.WorkFlowTransactionModel;
import com.jsp.automation.queue.service.ListenerService;
import com.jsp.automation.repository.WorkFlowTransactionRepository;
import com.jsp.automation.service.WorkflowTransactionService;

@Service
public class ListenerServiceImpl implements ListenerService {

	@Autowired
	private WorkFlowTransactionRepository workFlowTransactionRepository;
	
	@Autowired
	private WorkflowTransactionService workflowTransactionService;
	
	@Override
//	@RabbitListener(queues = RabbitMQueueConfig.QUEUE_NAME)
	public void listenerEvent(WorkflowTransactionContext workflowTransactionContext) {
		WorkFlowTransactionModel workFlowTransactionModel = workflowTransactionContext.getWorkFlowTransactionModel();
		workFlowTransactionModel.setStstusFlag("INPROGRESS");
		workFlowTransactionRepository.save(workFlowTransactionModel);
		
		workflowTransactionService.execute(workflowTransactionContext);
		
		
		
	}
	
	@RabbitListener(queues = RabbitMQueueConfig.QUEUE_NAME)
	public void listenerEventMessage(String message) {
		System.out.println("message "+ message);
		
	}

}
