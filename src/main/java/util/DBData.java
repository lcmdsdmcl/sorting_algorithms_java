package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBData {

	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";		
	private static final String DB = "jdbc:mysql://localhost/random_numbers";
		
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB, USERNAME, PASSWORD);
	}
}
