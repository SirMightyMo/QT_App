package test.java.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.LayoutManager;
import main.java.model.User;

class AccountControllerTest {

	@Test
	void constructorTest() {
		new LayoutManager();
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		AccountControllerTest ac = new AccountControllerTest();
		assertNotNull(ac);
	}
}
