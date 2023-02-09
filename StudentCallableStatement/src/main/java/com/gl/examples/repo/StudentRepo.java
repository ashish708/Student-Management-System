package com.gl.examples.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StudentRepo {
	private static Connection conn;

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		if (conn == null || conn.isClosed()) {
			// Load the JDBC driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			// Establish a database connection
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ContactDb", "root", "Odisha@123#");
		}
		return conn;
	}

	public static void closeConnection() throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}
}
