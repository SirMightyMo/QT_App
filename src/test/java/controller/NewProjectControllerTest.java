package test.java.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.NewProjectController;
import main.java.model.User;

class NewProjectControllerTest {

	NewProjectController npm;

	@Test
	void constructorTest() throws Exception {
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		npm = new NewProjectController();
		assertNotNull(npm);
	}
}
