package com.jsp.automation.service;

import java.util.Map;

public interface DataManagerService {
	public Map<String, Object> getSingleTransactionData(String entityName, String selectedfield,String queryField, String queryFieldValue);
}
