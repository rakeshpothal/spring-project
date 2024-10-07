package com.jsp.automation.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SqlRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> getSingleTransactiondata(String entityName, String selectedfield, String queryField,
			String queryFieldValue) {
		String query = "select " + selectedfield + "from " + entityName + "where " + queryField + "=" + queryFieldValue;
		Map<String, Object> queryForMap = jdbcTemplate.queryForMap(query);
		return queryForMap;
	}
}
