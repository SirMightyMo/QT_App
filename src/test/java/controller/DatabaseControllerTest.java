package test.java.controller;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.h2.jdbc.JdbcSQLSyntaxErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.formdev.flatlaf.FlatDarkLaf;

import junit.framework.Assert;
import main.java.controller.DashboardHourListController;
import main.java.controller.DatabaseController;
import main.java.controller.LayoutManager;
import main.java.model.User;

class DatabaseControllerTest {

	DatabaseController dc;

	@BeforeEach
	void setUp() throws Exception {
		new LayoutManager();
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		dc = dc.DBC;
	}

	@Test
	void testConnect() {
		assertNotNull(dc);
		dc.connect();
	}

	@Test
	void testClose() {
		assertNotNull(dc);
		dc.connect();
		dc.close();
	}

	@Test
	void testInsert_Valid() {
		String username = "Testuser from DatabaseControllerTest";
		String password = "Testpw";
		String email = "test@gmail.com";
		String security_question = "What is 42?";
		String answer = "42";

		assertNotNull(dc);
		dc.insert("INSERT INTO users(username,password,email,security_question,answer)VALUES(" + "'" + username + "',"
				+ "'" + password + "'," + "'" + email + "'," + "'" + security_question + "'," + "'" + answer + "');");
	}

	@Test
	void testInitializeDB() {
		assertEquals(dc.initializeDB(), 0);
	}

	@Test
	void testQuery_Valid() {
		String sql = "SELECT u_id, username, email, password FROM users WHERE username='" + "Bob" + "';";
		ArrayList<Object> result = dc.query(sql, true);

		assertEquals(result.get(0), 1);
		assertEquals(result.get(1), "Bob");
		assertEquals(result.get(2), "bob@msn.com");
	}

	@Test
	void testQuery_Invalid() {
		String sql = "SELECT u_id, username, email, password FROM users WHERE username='" + "Bob" + "';";
		ArrayList<Object> result = dc.query(sql, true);

		assertNotEquals(result.get(0), 2);
		assertNotEquals(result.get(1), "Alan");
		assertNotEquals(result.get(2), "turbo@gmx.de");
	}
	
	@Test
	void testQuery_MultipleLineOutput(){
		ArrayList<Object> result = dc.query("SELECT * FROM project LEFT JOIN assign_project_user ON project.p_id = assign_project_user.p_id WHERE u_id = " + User.getUser().getU_id() + ";");
		String excpectedRow1 = "[1, Projekt 1, 2021-01-15, 2022-01-15, true, 1, null, 1, 1, 1]";
		String excpectedRow2 = "[1, Projekt 1, 2021-01-15, 2022-01-15, true, 1, null, 5, 1, 1]";
		
		assertEquals(result.get(0).toString(),excpectedRow1);
		assertEquals(result.get(0).toString(),excpectedRow2);
	}
	
	@Test
	void testExecuteSQLScript() {
		assertEquals(dc.executeSQLScript("./src/main/resources/data/createTables.sql"),0);
		assertEquals(dc.executeSQLScript("./src/main/resources/data/insertDummyData.sql"),0);
	}
	
	
//	@Test
//	public void testInsert_ExceptionThrown() {
//	    Exception exception = assertThrows(Exception.class, () -> {
//	    	String username = "TestuserfromDatabaseContTestTestuserfromDatabaseControllerTest";
//			String password = "Testpw";
//			String email = "test@gmail.com";
//			String security_question = "What is 42?";
//			String answer = "42";
//			
//			dc.insert("INSERT INTO users(username,password,email,security_question,answer)VALUES("
//					+ "'" + username + "',"
//					+ "'" + password + "',"
//					+ "'" + email + "',"
//					+ "'" + security_question + "',"
//					+ "'" + answer + "');"
//					);
//	    });
//	    System.out.println(exception.getMessage());
//	}
}
