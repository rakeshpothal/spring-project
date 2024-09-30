package com.jsp.automation.service;

import java.util.Map;

import com.jsp.automation.dto.WorkFlowDto;
import com.jsp.automation.entity.WorkFlowEntity;

/**
 * it is an interface for workflow service
 */
public interface WorkFlowService {

	/**
	 * it is used to save the {@link WorkFlowEntity}
	 * 
	 * @param workFlowDto
	 */
	public void prepareAndUpdateWorkFlow(WorkFlowDto dto);

	/**
	 * It is used to update the status of workFlow Entity at a time only one antity.
	 * State can be active all other entity except draft dhould be inactive when.
	 * Draft will be active version will be update to highest version +1.
	 * 
	 * Receive argument {@link Map}<{@link String}>
	 * 
	 */
	public void updateStatus(Map<String, String> wfStatusMap);

	public void findByWfCode(String wfCode);
}
