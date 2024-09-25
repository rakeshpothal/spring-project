package com.jsp.automation.service;

import java.util.List;

import com.jsp.automation.dto.EntityDto;

/**
 * it is a service interface
 * 
 */
public interface AutomationService {
	public void saveEntityModel(EntityDto entityDto);

	public List<EntityDto> getEntityModel();

	public EntityDto findByEntityCode(String entityCode);
}
