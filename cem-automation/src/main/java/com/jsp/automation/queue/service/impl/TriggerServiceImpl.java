package com.jsp.automation.queue.service.impl;

import java.util.Date;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jsp.automation.config.RabbitMQueueConfig;
import com.jsp.automation.dto.WorkflowTransactionContext;
import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.entity.WorkFlowEntity;
import com.jsp.automation.entity.WorkFlowTransactionModel;
import com.jsp.automation.queue.service.TriggerService;
import com.jsp.automation.repository.WorkFlowTransactionRepository;
import com.jsp.automation.util.RandomNumberGenerator;

@Service
public class TriggerServiceImpl implements TriggerService {
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RandomNumberGenerator randomNumberGenerator;

	@Autowired
	private WorkFlowTransactionRepository workFlowTransactionRepository;

	@Autowired
	private RabbitMQueueConfig rabbitMQueueConfig;

//	public TriggerServiceImpl() {
//		System.out.println(this.getClass().getSimpleName());
//	}

	@Override
	public void triggerWorkFlow(WorkFlowEntity workFlowEntity, EntityModel entityModel, String uniqueValue) {
		WorkFlowTransactionModel workflowTransactionModel = createWorkflowTransactionModel(workFlowEntity, entityModel,
				uniqueValue);
		WorkflowTransactionContext transactionContext = new WorkflowTransactionContext();
		transactionContext.setWorkFlowEntity(workFlowEntity);
		transactionContext.setEntityModel(entityModel);
		transactionContext.setWorkFlowTransactionModel(workflowTransactionModel);

		putIntoRabbitMQ(transactionContext);

	}

	private WorkFlowTransactionModel createWorkflowTransactionModel(WorkFlowEntity workFlowEntity,
			EntityModel entityModel, String uniqueValue) {
		WorkFlowTransactionModel transactionModel = new WorkFlowTransactionModel();
		transactionModel.setTransactionId(randomNumberGenerator.generateSixtenDigitRandomNumber());
		transactionModel.setTransactionUniqueValue(uniqueValue);
		transactionModel.setWfId(workFlowEntity.getWfId());
		transactionModel.setWfCode(workFlowEntity.getWfCode());
		transactionModel.setStstusFlag("QUEUE");
		transactionModel.setTransactionStartDate(new Date());
		transactionModel.setCreatedDate(new Date());

		workFlowTransactionRepository.save(transactionModel);
		return transactionModel;

	}

	private void putIntoRabbitMQ(WorkflowTransactionContext workflowTransactionContext) {
		rabbitTemplate.convertAndSend(rabbitMQueueConfig.EXCHANGE_NAME, rabbitMQueueConfig.ROUTING_KEY,
				workflowTransactionContext);
	}

	@Override
	public void sendMessageInrabbitMq() {
		String data = "data to be send to receiver method";
		rabbitTemplate.convertAndSend(rabbitMQueueConfig.EXCHANGE_NAME, rabbitMQueueConfig.ROUTING_KEY, data);
		System.out.println("done");
	}

}
