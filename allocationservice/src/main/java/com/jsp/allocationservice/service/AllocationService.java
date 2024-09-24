package com.jsp.allocationservice.service;

import java.util.List;
import java.util.Map;

public interface AllocationService {
	public void processAndPrepareAllocation(List<Map<String, Object>> listData);
	public void getGrantsForAllocation();
	 
	
	
}

