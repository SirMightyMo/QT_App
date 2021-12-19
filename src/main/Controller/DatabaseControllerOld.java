package main.Controller;

import java.sql.*;

public class DatabaseControllerOld {

	// H2 Database // Logout: Close DB Connection
	private Connection dbConnection;
	public Connection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(Connection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String[] getArrayOfTables() {
		return arrayOfTables;
	}

	public void setArrayOfTables(String[] arrayOfTables) {
		this.arrayOfTables = arrayOfTables;
	}

	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}

	public String getDB_URL() {
		return DB_URL;
	}

	private final String JDBC_DRIVER = "org.h2.Driver";
	private final String DB_URL = "jdbc:h2:./data/db"; // relative path
	private String user;
	private String pass;
	private String[] arrayOfTables = new String[] {
			// TODO: Define final tables with correct identifiers
			"CREATE TABLE IF NOT EXISTS hourentries (id BIGINT PRIMARY KEY AUTO_INCREMENT, date VARCHAR(20), username VARCHAR(200), project VARCHAR(200), service VARCHAR(200), starttime VARCHAR(200), endtime VARCHAR(200), comment VARCHAR(200), durationInSeconds BIGINT, pauseInSeconds BIGINT)", // TIMESTAMP
			"CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(200), password VARCHAR(200), email VARCHAR(200))",
			"CREATE TABLE IF NOT EXISTS projects (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(200))",
			"CREATE TABLE IF NOT EXISTS services (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(200))"
};
	
	public DatabaseControllerOld(String user, String pass) {
		this.user = user;
		this.pass = pass;
		for (int i = 0; i < arrayOfTables.length; i++) {
			insert(arrayOfTables[i]);
		}
	}
	
	public void connect() {
		try {
			Class.forName(JDBC_DRIVER);
			dbConnection = DriverManager.getConnection(DB_URL, user, pass); // username is not case sensitive
			System.out.println("database connected as " + user + ".");
		} catch (SQLException se) { // Handle JDBC errors
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace(); // Handle errors for Class.forName()
		}
	}
	
	public void close() {
		try {
			dbConnection.close();
			System.out.println("database connection closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String sql) {
		try {
			Class.forName(JDBC_DRIVER);
			dbConnection = DriverManager.getConnection(DB_URL, user, pass);
			Statement statement = dbConnection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ResultSet query(String sql) {
		ResultSet rs = null;
		try {
			Class.forName(JDBC_DRIVER);
			dbConnection = DriverManager.getConnection(DB_URL, user, pass);
			Statement statement = dbConnection.createStatement();
			rs = statement.executeQuery(sql);
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

	
}