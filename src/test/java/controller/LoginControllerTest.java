package test.java.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.LayoutManager;
import main.java.controller.LoginController;
import main.java.model.User;

class LoginControllerTest {

	LoginController lc;
	
	@BeforeEach
	void setUp() throws Exception {
		new LayoutManager();
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		lc = new LoginController();
	}

	@Test
	void testConstructor() {
		assertNotNull(lc);
	}
}
