package test.java.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.LayoutManager;
import main.java.controller.TimerHourController;
import main.java.model.User;

class TimerHourControllerTest {

	TimerHourController thc;

	@Test
	void setUp() throws Exception {
		new LayoutManager();
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		thc = new TimerHourController();
		assertNotNull(thc);
	}
}
