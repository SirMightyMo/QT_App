package main.java.controller;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;

import main.java.model.IModel;
import main.java.view.IView;

public class DatabaseController implements IController {

	private final String JDBC_DRIVER = "org.h2.Driver";
	private final String DB_URL = "jdbc:h2:./src/main/resources/data/db"; // relative path
	private String user;
	private String pass;
	private Connection dbConnection; // H2 Database // Logout: Close DB Connection

	public DatabaseController(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}

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

	public String getJDBC_DRIVER() {
		return JDBC_DRIVER;
	}

	public String getDB_URL() {
		return DB_URL;
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

	/*
	 * insert(String sql)
	 * 
	 * This method takes a String as an argument. For the method to work this String
	 * needs to be a valid SQL statement.
	 * 
	 * Example:
	 * "INSERT INTO tablename(column1, column2, column3) VALUES('value1','value2','value3');"
	 * 
	 */
	public void insert(String sql) {
		Statement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			dbConnection = DriverManager.getConnection(DB_URL, user, pass);
			statement = dbConnection.createStatement();
			statement.executeUpdate(sql);
			System.out.println("Executed '" + sql + "'.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				dbConnection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * query(String sql):
	 * 
	 * This method takes a String as an argument. For the method to work this String
	 * needs to be a valid SQL statement.
	 * 
	 * Example: "SELECT column1, column2 FROM tablename;"
	 * 
	 * The method connects to the database, creates and executes the statement. The
	 * results retrieved are stored temporarily in a ResultSet. Since the only
	 * information given is the SQL statement as a String, the method does not
	 * automatically know how many columns are queried. Therefore it reads the meta
	 * data of the ResultSet and stores the column count in a separate variable. For
	 * every row contained in the ResultSet the method creates an ArrayList of
	 * Objects and adds the according values of the row. It then adds these
	 * ArrayLists containing the complete retrieved row's data to another final
	 * ArrayList of Objects 'resultArrayList'.
	 * 
	 * This resultArrayList is the returned by the method. For reading/computing the
	 * results of the inner ArrayLists, the values have to be casted to the
	 * according object type:
	 * 
	 * ArrayList<Object> result =
	 * db.query("SELECT column1, column2 FROM tablename;"); result.forEach(entry ->
	 * { ArrayList<Object> row = (ArrayList<Object> entry); // do something with
	 * row-ArrayList here }
	 * 
	 * "Visual Dummy-Example": ResultList( RowList(valueOfColumn1, valueOfColumn2),
	 * RowList(valueOfColumn1, valueOfColumn2), RowList(valueOfColumn1,
	 * valueOfColumn2), ... ) -> ResultList contains RowLists, that contain the
	 * values of queried columns.
	 * 
	 */
	public ArrayList<Object> query(String sql) {
		ArrayList<Object> resultArrayList = new ArrayList<>();
		ResultSet rs = null;
		Statement statement = null;
		try {
			Class.forName(JDBC_DRIVER);
			dbConnection = DriverManager.getConnection(DB_URL, user, pass);
			statement = dbConnection.createStatement();
			rs = statement.executeQuery(sql);

			ResultSetMetaData rsmd = rs.getMetaData(); // get info about ResultSet
			int columnCount = rsmd.getColumnCount(); // find out, how many columns per row where retrieved

			// while there are results, compute them here
			while (rs.next()) {
				// create an ArrayList for every retrieved row for storing column-values
				ArrayList<Object> rowData = new ArrayList<>();

				// for every column retrieved, add column-value to row-ArrayList;
				for (int column = 1; column <= columnCount; column++) {
					rowData.add(rs.getObject(column));
				}
				// add row to result-ArrayList
				resultArrayList.add(rowData);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			dbConnection.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return resultArrayList;
	}

	public void initializeDB() {
		if (executeSQLScript("./src/main/resources/data/createTables.sql") == 0
				/*&& executeSQLScript("./src/main/resources/data/insertDummyData.sql") == 0*/) { // If dummy-data needed,
																								// remove inline comment
			System.out.println("Database successfully initialized");
		}
	}

	// If only one row (or one row with just one value) is expected, set flag to true.
	// The ArrayList consists just of these value(s) (type 'Object', need to be casted)
	// and does NOT contain another ArrayList.
	public ArrayList<Object> query(String sql, boolean onlyOneRowExpected) {
		if (onlyOneRowExpected) {
			ArrayList<Object> resultArrayList = new ArrayList<>();
			ResultSet rs = null;
			Statement statement = null;
			try {
				Class.forName(JDBC_DRIVER);
				dbConnection = DriverManager.getConnection(DB_URL, user, pass);
				statement = dbConnection.createStatement();
				rs = statement.executeQuery(sql);

				ResultSetMetaData rsmd = rs.getMetaData(); // get info about ResultSet
				int columnCount = rsmd.getColumnCount(); // find out, how many columns per row where retrieved

				// while there are results, compute them here
				while (rs.next()) {
					// for every column retrieved, add column-value to row-ArrayList;
					for (int column = 1; column <= columnCount; column++) {
						resultArrayList.add(rs.getObject(column));
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (statement != null)
						statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			try {
				dbConnection.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			return resultArrayList;
		} else {
			return query(sql);
		}
	}
	
	public int executeSQLScript(String scriptFilePath) {
		BufferedReader bReader = null;
		Statement statement = null;

		try {
			// Load Driver for H2
			Class.forName(JDBC_DRIVER);
			// Create Connection
			dbConnection = DriverManager.getConnection(DB_URL, user, pass);
			// Create Statement object
			statement = dbConnection.createStatement();
			// Read file
			bReader = new BufferedReader(new FileReader(scriptFilePath));
			String line = null;

			while ((line = bReader.readLine()) != null) {
				// execute query
				statement.executeUpdate(line);
			}

			statement.close();
			bReader.close();
			dbConnection.close();

			System.out.println("SQL-Script " + scriptFilePath + " executed successfully");
		} catch (Exception e) {
			System.out.println("Problem executing SQL Script");
			e.printStackTrace();
			return 1;
		}
		return 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IView getView() {
		// TODO Auto-generated method stub
		return null;
	}
}