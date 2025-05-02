package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection con;

	// Generic method to create a database connection
	public void getDbConnection(String url, String username, String password) throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Database connection successful.");
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to create a database connection
	public void getDbConnection() throws SQLException {
		try {
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			con = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
			System.out.println("Database connection successful.");
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to close a connection
	public void closeDbConnection() throws SQLException {
		try {
			con.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
	}

	// Generic method to execute Select Query
	public ResultSet executeSelectQuery(String query) throws SQLException {
		ResultSet result = null;
		try {
			Statement stat = con.createStatement();
			result = stat.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	// Generic method to execute Non Select Query
	public int executeNonSelectQuery(String query) throws SQLException {
		int result = 0;
		try {
			Statement stat = con.createStatement();
			result = stat.executeUpdate(query);
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
