package test.java.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.AppMainController;
import main.java.controller.LayoutManager;
import main.java.model.User;

class AppMainControllerTest {
	AppMainController amc;

	@Test
	void constructorTest() {
		new LayoutManager();
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		amc = new AppMainController();
		assertNotNull(amc);
	}
}
