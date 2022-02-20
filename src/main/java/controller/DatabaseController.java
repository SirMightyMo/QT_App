package main.java.controller;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;

import main.java.model.IModel;
import main.java.view.IView;

/**
 * DatabaseController controlls connections to the database.
 * It implements public methods for querying data or running
 * SQL statements.<br>
 * This Class implements the 'Singleton Pattern' and therefore
 * instanciates itself. No further instance of this class is 
 * possible. The information about this instance is also saved
 * in this class itself and can be accessed through a method.
 * <p>
 * This way, it is neither needed nor possible to instanciate more 
 * than one DatabaseController and this one instance can be used 
 * for all database actions.
 * <p>
 * At the moment, the database is always accessed with the 
 * same credentials. Database connection maybe could be 
 * established with unique credentials for each user?
 * @author Leander
 *
 */

public class DatabaseController implements IController {

	private final String JDBC_DRIVER = "org.h2.Driver";
	private final String DB_URL = "jdbc:h2:./src/main/resources/data/db"; // relative path
	private String user;
	private String pass;
	private Connection dbConnection; // H2 Database // Logout: Close DB Connection

	public static final DatabaseController DBC = new DatabaseController("sa", "");
	
	private DatabaseController(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	
	/**
	 * Returns the instance of this class (Singleton Pattern)
	 * @return One and only instance of this class.
	 */
	public static DatabaseController getInstance() {
		return DBC;
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

	/**
	 * Opens connection to database with given credentials set in class.
	 * 
	 * @author Leander
	 */
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

	/**
	 * Closes connection to database.
	 * 
	 * @author Leander
	 */
	public void close() {
		try {
			dbConnection.close();
			System.out.println("database connection closed.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Takes SQL commands as String and runs them on database.
	 * 
	 * Example:
	 * "INSERT INTO tablename(column1, column2, column3) VALUES('value1','value2','value3');"
	 * 
	 * @param sql		The SQL command to be runned.
	 * @author Leander
	 */
	public void run(String sql) {
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
		}
	}

	/**
	 * Takes SQL query as String and queries data from database.
	 * <p>
	 * Example: "SELECT column1, column2 FROM tablename;"
	 * <p>
	 * The method connects to the database, creates and executes the statement. The
	 * results retrieved are stored temporarily in a ResultSet. Since the only
	 * information given is the SQL statement as a String, the method does not
	 * automatically know how many columns are queried. Therefore it reads the meta
	 * data of the ResultSet and stores the column count in a separate variable. For
	 * every row contained in the ResultSet the method creates an ArrayList of
	 * Objects and adds the according values of the row. It then adds these
	 * ArrayLists containing the complete retrieved row's data to another final
	 * ArrayList of Objects 'resultArrayList'.
	 * <p>
	 * This resultArrayList is the returned by the method. For reading/computing the
	 * results of the inner ArrayLists, the values have to be casted to the
	 * according object type:
	 * <p>
	 * ArrayList result =
	 * db.query("SELECT column1, column2 FROM tablename;"); result.forEach(entry =>
	 * { ArrayList row = (ArrayList entry); // do something with
	 * row-ArrayList here }
	 * <p>
	 * "Visual Dummy-Example": 
	 * ResultList( RowList(valueOfColumn1, valueOfColumn2),
	 * RowList(valueOfColumn1, valueOfColumn2), RowList(valueOfColumn1,
	 * valueOfColumn2), ... ) => ResultList contains RowLists, that contain the
	 * values of queried columns.
	 * <p>
	 * @param sql The SQL command for querying data.
	 * @return An ArrayList containing ArrayLists (rows of db result).
	 * @author Leander
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
		return resultArrayList;
	}

	/**
	 * Creates tables from createTables.sql and
	 * inserts DummyData from insertDummyData.sql
	 * @return 0 if database successfully initialized, 1 if something bad happened
	 * 
	 * @author kevin
	 */
	public int initializeDB() {
		if (executeSQLScript("/main/resources/data/createTables.sql") == 0
				/*&& executeSQLScript("./src/main/resources/data/insertDummyData.sql") == 0*/) { // If dummy-data needed,
																								// remove inline comment
			System.out.println("Database successfully initialized");
			return 0;
		} else 
			return 1;
	}

	/**
	 * Takes SQL query as String and queries data from database.
	 * If only one row (or one row with just one value) is expected, set flag to true.
	 * The ArrayList consists just of these value(s) (type 'Object', need to be casted)
	 * and does NOT contain another ArrayList.
	 * <p>
	 * @see DatabaseController#query(String sql)
	 * @param sql The SQL command for querying data.
	 * @param onlyOneRowExpected Set 'true' if only one result row is expected.
	 * @return ArrayList containing the queried data from database.
	 * @author Leander
	 */
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
			return resultArrayList;
		} else {
			return query(sql);
		}
	}
	
	/**
	 * Executes an SQL Script
	 * @author kevin
	 * @param scriptFilePath
	 * @return 0 if successful, 1 if problem executing SQL Script
	 */
	
	public int executeSQLScript(String scriptFilePath) {
		Statement statement = null;

		try {
			InputStream is = getClass().getResourceAsStream(scriptFilePath);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			// Load Driver for H2
			Class.forName(JDBC_DRIVER);
			// Create Connection
			dbConnection = DriverManager.getConnection(DB_URL, user, pass);
			// Create Statement object
			statement = dbConnection.createStatement();
			// Read file
			String line = null;

			while ((line = br.readLine()) != null) {
				// execute query
				statement.executeUpdate(line);
			}

			statement.close();
			br.close();
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

	}

	@Override
	public void insertUpdate(DocumentEvent e) {

	}

	@Override
	public void removeUpdate(DocumentEvent e) {

	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public IModel getModel() {
		return null;
	}

	@Override
	public IView getView() {
		return null;
	}
}