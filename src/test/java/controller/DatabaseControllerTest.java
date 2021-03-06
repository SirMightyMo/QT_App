package test.java.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.DatabaseController;
import main.java.controller.LayoutManager;
import main.java.model.User;

/**
 * Unit Tests for DatabaseController
 * @author kevin
 *
 */
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
	void testInsert_ValidInputs() {
		String username = "Testuser from DatabaseControllerTest";
		String password = "Testpw";
		String email = "test@gmail.com";
		String security_question = "What is 42?";
		String answer = "42";

		assertNotNull(dc);
		dc.run("INSERT INTO users(username,password,email,security_question,answer)VALUES(" + "'" + username + "',"
				+ "'" + password + "'," + "'" + email + "'," + "'" + security_question + "'," + "'" + answer + "');");
	}

	@Test
	void testInitializeDB_Initialize_True() {
		assertEquals(dc.initializeDB(), 0);
	}

	@Test
	void testQuery_ValidData() {
		String sql = "SELECT u_id, username, email, password FROM users WHERE username='" + "Bob" + "';";
		ArrayList<Object> result = dc.query(sql, true);

		assertEquals(result.get(0), 1);
		assertEquals(result.get(1), "Bob");
		assertEquals(result.get(2), "bob@msn.com");
	}

	@Test
	void testQuery_InvalidData() {
		String sql = "SELECT u_id, username, email, password FROM users WHERE username='" + "Bob" + "';";
		ArrayList<Object> result = dc.query(sql, true);

		assertNotEquals(result.get(0), 2);
		assertNotEquals(result.get(1), "Alan");
		assertNotEquals(result.get(2), "turbo@gmx.de");
	}

	@Test
	void testQuery_MultipleLineOutput_True() {
		ArrayList<Object> result = dc.query(
				"SELECT * FROM project LEFT JOIN assign_project_user ON project.p_id = assign_project_user.p_id WHERE u_id = "
						+ User.getUser().getU_id() + ";");
		String excpectedRow1 = "[1, Projekt 1, 2021-01-15, 2022-01-15, true, 1, 1, 1, 1]";

		assertEquals(result.get(0).toString(), excpectedRow1);
	}

	@Test
	void testExecuteSQLScript() {
		assertEquals(dc.executeSQLScript("./src/main/resources/data/createTables.sql"), 0);
		assertEquals(dc.executeSQLScript("./src/main/resources/data/insertDummyData.sql"), 0);
	}
}
