package com.jsp.automation.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.automation.controller.AutomationController;
import com.jsp.automation.dto.EntityDto;
import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.repository.EntityRepository;
import com.jsp.automation.util.Converter;

/**
 * it is a service implimention class implimenting {@link AutomationService}
 * 
 */
@Service
public class AutomationServiceImpl implements AutomationService {

	@Autowired
	private EntityRepository entityRepository;

	@Autowired
	private Converter converter;

	private static final Logger LOGGER = LoggerFactory.getLogger(AutomationController.class);

	/**
	 * it is used to save {@link EntityModel} to database
	 * 
	 * @param entityDto
	 */
	@Override
	public void saveEntityModel(EntityDto entityDto) {
		EntityModel entity = new EntityModel();
		entity.setCreatedDate(new Date());
		entity.setEntityCode(entityDto.getEntityCode());
		entity.setEntityDescription(entityDto.getEntityDescription());
		entity.setEntityName(entityDto.getEntityName());
		entity.setRemarks(entityDto.getRemarks());
		entity.setTemplateFields(converter.convertToDatabaseColumn(entityDto.getTemplateFields()));
		entity.setUniqueFields(converter.convertToDatabaseColumn(entityDto.getUniqueFields()));

		try {
			entityRepository.save(entity);
			LOGGER.info("Data get inserted for entity:{}", entity);
		} catch (Exception e) {
			String message = e.getMessage();
			LOGGER.error("Data insertion error Entity:{},message:{}", entity, message);

		}
	}

	/**
	 * it is used to get all entitymodel
	 * 
	 * @return {@link List}< {@link EntityDto}>
	 */
	@Override
	public List<EntityDto> getEntityModel() {
		try {
			List<EntityModel> list = entityRepository.findAll();
			LOGGER.info("getting data from database as modelEntitylist  entitylist:{}");

			List<EntityDto> entityDtos = list.stream().map(entity -> {
				return createEntityDto(entity);
			}).collect(Collectors.toList());
			return entityDtos;
		} catch (Exception e) {
			String message = e.getMessage();
			LOGGER.error("Error in fectching data from database message:{}", message);
		}
		return null;
	}

	/**
	 * it is used to get entity by entityCode
	 * 
	 * @param {@link String} entityCode
	 * @return {@link EntityDto} entityDto
	 */
	@Override
	public EntityDto findByEntityCode(String entityCode) {
		try {
			EntityModel entity = entityRepository.findByEntityCode(entityCode);

			EntityDto entityDto = createEntityDto(entity);
			LOGGER.info("getting data from database as modelEntitylist");
			return entityDto;
		} catch (Exception e) {
			String message = e.getMessage();
			LOGGER.error("Error fetching data message:{}", message);

		}
		return null;
	}

	/**
	 * it is used to convert {@link EntityModel} to {@link EntityDto}
	 * 
	 * @param {@link EntityModel} entity
	 * @return {@link EntityDto} entityDto
	 */
	private EntityDto createEntityDto(EntityModel entity) {
		EntityDto entityDto = new EntityDto();
		entityDto.setEntityCode(entity.getEntityCode());
		entityDto.setEntityDescription(entity.getEntityDescription());
		entityDto.setEntityName(entity.getEntityName());
		entityDto.setRemarks(entity.getRemarks());
		entityDto.setTemplateFields(converter.convertToEntityAttribute(entity.getTemplateFields()));
		entityDto.setUniqueFields(converter.convertToEntityAttribute(entity.getUniqueFields()));
		return entityDto;
	}

}
