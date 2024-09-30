package com.jsp.automation.service;

import java.util.List;

import com.jsp.automation.dto.EntityDto;
import com.jsp.automation.entity.EntityModel;

/**
 * it is a service interface
 * 
 */
public interface AutomationService {
	/**
	 * it is used to save {@link EntityModel} to database
	 * 
	 * @param entityDto
	 */
	public void saveEntityModel(EntityDto entityDto);

	/**
	 * it is used to get all entitymodel
	 * 
	 * @return {@link List}< {@link EntityDto}>
	 */
	public List<EntityDto> getEntityModel();

	/**
	 * it is used to get entity by entityCode
	 * 
	 * @param {@link String} entityCode
	 * @return {@link EntityDto} entityDto
	 */
	public EntityDto findByEntityCode(String entityCode);
}
