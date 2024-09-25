package com.jsp.automation.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.automation.dto.EntityDto;
import com.jsp.automation.entity.EntityModel;
import com.jsp.automation.repository.EntityRepository;
import com.jsp.automation.util.Converter;

@Service
public class AutomationServiceImpl implements AutomationService {

	@Autowired
	private EntityRepository autoRepository;

	@Autowired
	private Converter converter;

	@Override
	public void saveEntityModel(EntityDto entityDto) {
		EntityModel entity = new EntityModel();
		entity.setCreatedDate(new Date());
		entity.setEntityCode(entityDto.getEntityCode());
		entity.setEntityDescription(entityDto.getEntityDescription());
		entity.setEntityName(entityDto.getEntityName());
		entity.setRemarks(entityDto.getRemarks());
//		entity.set
	}

	@Override
	public List<EntityDto> getEntityModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityDto findByEntityCode(String entityCode) {
		// TODO Auto-generated method stub
		return null;
	}

//	

}
