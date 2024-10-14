package com.jsp.automation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.automation.constant.MappingConstantForWorkFlow;
import com.jsp.automation.dto.WorkFlowDto;
import com.jsp.automation.service.WorkFlowService;
import com.jsp.automation.service.WorkflowTransactionService;
import com.jsp.automation.service.impl.WorkFlowServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class WorkFlowController {
	@Autowired
	private WorkFlowService workFlowService;
	
	@Autowired
	private WorkflowTransactionService workflowTransactionService;

	@PostMapping(value = MappingConstantForWorkFlow.SAVE_WORKFLOW)
	public void saveWorkflow(@RequestBody WorkFlowDto dto) {
		workFlowService.prepareAndUpdateWorkFlow(dto);
	}

	@PostMapping(value = MappingConstantForWorkFlow.UPDATE_WORKFLOW)
	public void updateStatus(@RequestBody Map<String, String> wfStatusMap) {
		/*
		 * { "wf_id":"", "ststus_flag":"ACTIVE", "wf_code":"work_flow_is_0" }
		 */
		workFlowService.updateStatus(wfStatusMap);
//		workFlowService.findByWfCode(null);
	}
	
	@GetMapping(value = "/test")
	public void transactionContext() {
		new WorkFlowServiceImpl().workflowTransactionContext();
	}
	
	@GetMapping(value = "/getnode/{wfCode}")
	public void getNodeConfig(@PathVariable("wfCode") String wfCode) {
		workFlowService.findByWfCode(wfCode);
	}
	
	@PostMapping("/manualpush")
	public void getMethodName(@RequestBody Map<String, Object> data) {
		workflowTransactionService.processManualPush(data);
		
//		wfCode : wfcode, wfId : wfid, uniqueValue : uniquevalue
	}
	
}
