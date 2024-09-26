package com.jsp.automation.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.automation.controller.WorkFlowController;
import com.jsp.automation.dto.WorkFlowDto;
import com.jsp.automation.entity.WorkFlowEntity;
import com.jsp.automation.repository.WorkFlowRepository;

/**
 * it is an implimention class for {@link WorkFlowService}
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowController.class);
	@Autowired
	private WorkFlowRepository workFlowRepository;

	/**
	 * it is used to save the {@link WorkFlowEntity}
	 * 
	 * @param workFlowDto
	 */
	@Override
	public void prepareAndUpdate(WorkFlowDto dto) {
		WorkFlowEntity workFlowEntity = workFlowRepository.findByWfIdAndStstusFlag(dto.getWfId(), "DRAFT");
		if (workFlowEntity == null) {
			// create new workflow entity

			WorkFlowEntity flowEntity = new WorkFlowEntity();
			flowEntity.setCreatedDate(new Date());
			flowEntity.setEntityCode(dto.getEntityCode());
			flowEntity.setSourceData(dto.getSourceData());
			flowEntity.setUniqueField(dto.getUniqueField());
			flowEntity.setWfCode(dto.getWfId() + "_0");
			flowEntity.setWfId(dto.getWfId());
			flowEntity.setWfName(dto.getWfName());

			workFlowRepository.save(flowEntity);
		} else {
			// update existing workflow entity

			workFlowEntity.setSourceData(dto.getSourceData());
			workFlowEntity.setModifiedDate(new Date());

			workFlowRepository.save(workFlowEntity);
		}
	}

}
