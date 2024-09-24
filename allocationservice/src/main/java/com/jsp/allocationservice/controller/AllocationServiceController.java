package com.jsp.allocationservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.allocationservice.constnt.MappingConstant;
import com.jsp.allocationservice.service.AllocationService;

@RestController
public class AllocationServiceController {

	@Autowired
	private AllocationService allocationService;

	@PostMapping(value = MappingConstant.SAVE_ALLOCATION_SERVICE)
	public void saveAllocationService(@RequestBody List<Map<String, Object>> listdata) {
		System.out.println(listdata);
		allocationService.processAndPrepareAllocation(listdata);
	}
}
