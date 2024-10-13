package com.jsp.automation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.automation.constant.MappingConstantForEntityModel;
import com.jsp.automation.dto.EntityDto;
import com.jsp.automation.queue.service.ListenerService;
import com.jsp.automation.queue.service.TriggerService;
import com.jsp.automation.service.AutomationService;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class AutomationController {

	@Autowired
	AutomationService automationService;
	
	@Autowired
	TriggerService triggerService;
	
	@Autowired
	ListenerService listenerService;

	@PostMapping(value = MappingConstantForEntityModel.SAVE_ENTITY_MODEL)
	public void saveEntityModel(@RequestBody EntityDto entityDto) {
		automationService.saveEntityModel(entityDto);
//		System.out.println("here");
	}

	@GetMapping(value = MappingConstantForEntityModel.GET_ENTITY_MODEL)
	public List<EntityDto> getEntityModel() {
		return automationService.getEntityModel();
	}

	@GetMapping(value = MappingConstantForEntityModel.GET_ENTITY_MODEL_BY_MODEL_CODE)
	public EntityDto getEntityByentityCode(String entityCode) {
		return automationService.findByEntityCode(entityCode);
	}
	
	@GetMapping(value = "/check")
	public void getMethodName() {
		triggerService.sendMessageInrabbitMq();
	}
//	@GetMapping(value = "/message")
//	public void getMethodmessage() {
//		listenerService.listenerEventMessage(null);
//	}
	

}
