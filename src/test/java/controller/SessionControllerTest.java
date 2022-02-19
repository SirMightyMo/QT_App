package test.java.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.LayoutManager;
import main.java.controller.SessionController;
import main.java.model.User;

class SessionControllerTest {

	SessionController sc;

	@Test
	void setUp() throws Exception {
		new LayoutManager();
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		sc = new SessionController();
		assertNotNull(sc);
	}
}
