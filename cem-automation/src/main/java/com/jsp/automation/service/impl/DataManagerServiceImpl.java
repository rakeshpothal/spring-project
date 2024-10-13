package com.jsp.automation.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jsp.automation.repository.SqlRepository;
import com.jsp.automation.service.DataManagerService;
@Service
public class DataManagerServiceImpl implements DataManagerService {
	@Autowired
	private SqlRepository sqlRepository;

	@Override
	public Map<String, Object> getSingleTransactionData(String entityName, String selectedfield, String queryField,
			String queryFieldValue) {
		return sqlRepository.getSingleTransactiondata(entityName, selectedfield, queryField, queryFieldValue);
	}

}
