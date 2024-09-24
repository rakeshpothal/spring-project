package com.jsp.grantsource.service;

import java.util.List;
import java.util.Map;

import com.jsp.grantsource.DTO.GrantAssignmentDto;
import com.jsp.grantsource.Entity.GrantAssignmentEntity;

public interface GrantService {
	public void processGrante(List<GrantAssignmentDto> grantAssignmentDtos);
	public List<GrantAssignmentEntity> listAllGrant();
	public GrantAssignmentEntity fetchGrantbyId(long id);
	public void approveGrantById(List<Long> grantIdList);
	public List<GrantAssignmentEntity> getGrantsForAllocationBYId(long planId);
	public List<Map<String, Object>> getGrantListForAllocatioByPlanId(long planId);
		
	
	
	
	
}
