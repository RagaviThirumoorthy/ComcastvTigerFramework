package com.vTiger.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {
	
	Connection conn;
	
	public void getDBConnection(String url, String username, String password) throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getDBConnection() throws SQLException {
		try {
			Driver driver  =new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "KavyaDB@2712");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void closeDBConnection() throws SQLException {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet executeSelectQuery(String query) throws SQLException {
		
		ResultSet result = null;
		try {
			Statement statement = conn.createStatement();
			result = statement.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int executeUpdate(String query) throws SQLException {
		
		int result = 0;
		try {
			Statement statement = conn.createStatement();
			result = statement.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
}
