package com.jsp.automation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.automation.constant.MappingConstantForWorkFlow;
import com.jsp.automation.dto.WorkFlowDto;
import com.jsp.automation.service.WorkFlowService;

@RestController
public class WorkFlowController {
	@Autowired
	private WorkFlowService workFlowService;

	@PostMapping(value = MappingConstantForWorkFlow.SAVE_WORKFLOW)
	public void saveWorkflow(@RequestBody WorkFlowDto dto) {
		workFlowService.prepareAndUpdateWorkFlow(dto);
	}

	@PostMapping(value = MappingConstantForWorkFlow.UPDATE_WORKFLOW)
	public void updateStatus(@RequestBody Map<String, String> wfStatusMap) {
		/*
		 * { "wf_id":"", "ststus_flag":"ACTIVATED", "wf_code":"work_flow_is_0" }
		 */
//		workFlowService.updateStatus(wfStatusMap);
		workFlowService.findByWfCode(null);
	}
}
