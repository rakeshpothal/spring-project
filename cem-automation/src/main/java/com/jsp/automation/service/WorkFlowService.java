package com.jsp.automation.service;

import java.util.Map;

import com.jsp.automation.dto.WorkFlowDto;

/**
 * it is an interface for workflow service
 */
public interface WorkFlowService {
	public void prepareAndUpdateWorkFlow(WorkFlowDto dto);

	public void updateStatus(Map<String, String> wfStatusMap);
}
