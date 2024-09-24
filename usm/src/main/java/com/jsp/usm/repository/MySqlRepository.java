package com.jsp.usm.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MySqlRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Map<String, Object>> getUsers() {
		String userQuery = "select * from user_register_info";
		List<Map<String, Object>> userList = jdbcTemplate.queryForList(userQuery);
		return userList;
	}

	public List<Map<String, Object>> getUserByEmail(String email) {
		String getUSerByEmail = "select * from user_register_info where email = ?";
		List<Map<String, Object>> userList = jdbcTemplate.queryForList(getUSerByEmail, email);
		return userList;
	}

	public List<Map<String, Object>> getUserByEmailAndNme(String email, String name) {
		String getUSerByEmail = "select * from user_register_info where email = ? and name= ?";
		List<Map<String, Object>> userList = jdbcTemplate.queryForList(getUSerByEmail, email, name);
		return userList;
	}

	/**
	 * This Method will accepts List<String> for email and name to search users base on email and name
	 * @param List<String>
	 * @return List<Map<String, Object>>
	 */
	public List<Map<String, Object>> getUserByEmailAndNameList(List<String> dataList) {
		String getUSerByEmail = "select * from user_register_info where email = ? and name=?";
		List<Map<String, Object>> userList = jdbcTemplate.queryForList(getUSerByEmail, dataList.toArray());
		return userList;
	}
}
