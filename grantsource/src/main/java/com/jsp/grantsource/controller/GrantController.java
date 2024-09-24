package com.jsp.grantsource.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.grantsource.DTO.GrantAssignmentDto;
import com.jsp.grantsource.Entity.GrantAssignmentEntity;
import com.jsp.grantsource.constant.MappingConstant;
import com.jsp.grantsource.service.GrantService;

@RestController
public class GrantController {

	@Autowired
	private GrantService grantService;

	@PostMapping(MappingConstant.SAVE_GRANT_APPLICTION)
	public void uploadGrant(@RequestBody List<GrantAssignmentDto> grantAssignmentDtos) {
		grantService.processGrante(grantAssignmentDtos);
	}

	@GetMapping(value = MappingConstant.GET_ALL_GRANTS)
	public @ResponseBody List<GrantAssignmentEntity> listGrant() {
		return grantService.listAllGrant();
	}

	@GetMapping(value = MappingConstant.GET_GRANT_BY_ID)
	public @ResponseBody GrantAssignmentEntity getGrantBYId(@RequestParam long id) {
		return grantService.fetchGrantbyId(id);
	}

	@GetMapping(value = MappingConstant.GET_GRANT_BY_ID_DUPLICATE)
	public @ResponseBody GrantAssignmentEntity getGrantBYIdDuplicate(@PathVariable("id") long id) {
		return grantService.fetchGrantbyId(id);
	}

	@PatchMapping(value = MappingConstant.UPDATE_GRANT_BY_ID)
	public void approveGrante(@PathVariable("grantids") List<Long> grantIdList) {
		grantService.approveGrantById(grantIdList);
	}

	@GetMapping(value = MappingConstant.GET_GRANTBY_PLANID)
	public @ResponseBody List<GrantAssignmentEntity> getGrantByPlanId(@PathVariable("planid") Long planId) {
		return grantService.getGrantsForAllocationBYId(planId);
	}

	@GetMapping(value = MappingConstant.GET_GRANT_LIST_BY_PLAN_ID)
	public List<Map<String, Object>> getGrantListByPlanId(@PathVariable("planid") long planId) {
		return grantService.getGrantListForAllocatioByPlanId(planId);
	}

}
