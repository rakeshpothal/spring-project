package com.jsp.automation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.jsp.automation.constant.MappingConstant;
import com.jsp.automation.dto.EntityDto;
import com.jsp.automation.service.AutomationService;

import jakarta.persistence.Converter;

@RestController
public class AutomationController {

	@Autowired
	AutomationService automationService;

	@PostMapping(value = MappingConstant.SAVE_ENTITY_MODEL)
	public void saveEntityModel(@RequestBody EntityDto entityDto) {
		automationService.saveEntityModel(entityDto);
//		System.out.println("here");
	}

	@GetMapping(value = MappingConstant.GET_ENTITY_MODEL)
	public List<EntityDto> getEntityModel() {
		return automationService.getEntityModel();
	}

	@GetMapping(value = MappingConstant.GET_ENTITY_MODEL_BY_MODEL_CODE)
	public EntityDto getEntityByentityCode(String entityCode) {
		return automationService.findByEntityCode(entityCode);
	}

}
