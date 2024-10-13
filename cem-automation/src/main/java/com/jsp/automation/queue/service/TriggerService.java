package com.jsp.automation.queue.service;

import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.entity.WorkFlowEntity;

public interface TriggerService {
	public void triggerWorkFlow(WorkFlowEntity workFlowEntity, EntityModel entityModel, String uniqueValue);
	public void sendMessageInrabbitMq();
}
