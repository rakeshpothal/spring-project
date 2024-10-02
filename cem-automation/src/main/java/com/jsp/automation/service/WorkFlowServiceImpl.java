package com.jsp.automation.service;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.automation.controller.WorkFlowController;
import com.jsp.automation.dto.WorkFlowDto;
import com.jsp.automation.entity.WorkFlowEntity;
import com.jsp.automation.repository.WorkFlowNodeRepository;
import com.jsp.automation.repository.WorkFlowRepository;
import com.jsp.automation.util.XmlParser;

/**
 * it is an implimention class for {@link WorkFlowService}
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WorkFlowController.class);
	@Autowired
	private WorkFlowRepository workFlowRepository;
	
	@Autowired
	private WorkFlowNodeRepository workFlowNodeRepository;

	@Override
	public void prepareAndUpdateWorkFlow(WorkFlowDto dto) {
		WorkFlowEntity workFlowEntity = workFlowRepository.findByWfIdAndStatusFlag(dto.getWfId(), "DRAFT");
		if (workFlowEntity == null) {
			// create new workflow entity

			workFlowEntity = new WorkFlowEntity();
			workFlowEntity.setCreatedDate(new Date());
			workFlowEntity.setEntityCode(dto.getEntityCode());
			workFlowEntity.setSourceData(dto.getSourceData());
			workFlowEntity.setUniqueField(dto.getUniqueField());
			workFlowEntity.setWfCode(dto.getWfId() + "_0");
			workFlowEntity.setWfId(dto.getWfId());
			workFlowEntity.setWfName(dto.getWfName());

		} else {
			// update existing workflow entity

			workFlowEntity.setSourceData(dto.getSourceData());
			workFlowEntity.setModifiedDate(new Date());

		}
		try {
			workFlowRepository.save(workFlowEntity);
			LOGGER.info("Execution Successful");
		} catch (Exception e) {
			String message = e.getMessage();
			LOGGER.error("Execution error message:{}", message);
		}
	}

	@Override
	public void updateStatus(Map<String, String> wfStatusMap) {
		String wfCode = wfStatusMap.get("wf_code");

		try {
			WorkFlowEntity entity = workFlowRepository.findByWfCode(wfCode);
			String wfId = entity.getWfId();

			// upating status to inactive
			workFlowRepository.updateByWfIdAndWfCodeSetStatusFlag(wfId, wfCode, "INACTIVE");

			// if status is draft then update version and wfCode
			if (entity.getStatusFlag().equals("DRAFT")) {

				// get highest version and increment by 1
				int highestVersion = workFlowRepository.findByWfIdMaxVersionGroupByWfId(wfId) + 1;

				entity.setVersion(highestVersion);
				entity.setWfCode(wfId + "_" + highestVersion);
			}

			entity.setStatusFlag(wfStatusMap.get("ststus_flag"));

			workFlowRepository.save(entity);
			LOGGER.info("Execution Successfull for wfCode:{}", wfCode);
		} catch (Exception e) {
			String message = e.getMessage();
			LOGGER.error("execution error message:{}", message);
		}

	}

	@Override
	public void findByWfCode(String wfCode) {
		try {
//			workFlowNodeRepository.findByWfCode(wfCode);
			WorkFlowEntity byWfCode = workFlowRepository.findByWfCode("test1_0");
			String sourceData = byWfCode.getSourceData();
			
			XmlParser.saxParserData(sourceData);
			
		} catch (Exception e) {
			String message = e.getMessage();
			LOGGER.error("transaction error message:{}", message);
		}

	}

}
