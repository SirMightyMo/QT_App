package test.java.controller;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.RegistrationController;
import main.java.model.User;

class RegistrationControllerTest {

	RegistrationController rc;

	@Test
	void constructorTest() throws Exception {
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		rc = new RegistrationController();
		assertNotNull(rc);
	}
}
