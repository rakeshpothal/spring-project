package com.jsp.core.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;


public class AppSourceUtil {

	private static final String DB_URL = System.getenv("DB_URL");
	private static final String DB_USER = System.getenv("DB_USER");
	private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

	public static void storeRegisterService(String modulename, String serviceName, String url) {
		try {
			Class.forName(DB_DRIVER);

			Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM register_service WHERE module_name='" + modulename + "' AND service_name='"
					+ serviceName + "' ";
			ResultSet data = statement.executeQuery(sql);

			if (data.next()) {
				long id = data.getLong("alt_key");
				Date modified_date = Date.valueOf(LocalDate.now());
				String status = "UPDATE";
				String sql_update = "UPDATE register_service SET URL='" + url + "', modified_date='" + modified_date
						+ "', `status`='" + status + "' WHERE alt_key=" + id;
				statement.executeUpdate(sql_update);
			} else {
				Date created_date = Date.valueOf(LocalDate.now());
				String status = "CREATED";
				String sql_insert = "INSERT INTO register_service (module_name,service_name,url,created_date, status) VALUES(?,?,?,?,?)";
				PreparedStatement preparedStatement = connection.prepareStatement(sql_insert);
				preparedStatement.setString(1, modulename);
				preparedStatement.setString(2, serviceName);
				preparedStatement.setString(3, url);
				preparedStatement.setDate(4, created_date);
				preparedStatement.setString(5, status);
				
				preparedStatement.executeUpdate();
			}

			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
