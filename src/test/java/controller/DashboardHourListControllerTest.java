package test.java.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.DashboardController;
import main.java.controller.DashboardHourListController;
import main.java.controller.LayoutManager;
import main.java.model.User;

class DashboardHourListControllerTest {
	DashboardHourListController dhlc;

	@BeforeEach
	void setUp() throws Exception {
		new LayoutManager();
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		dhlc = new DashboardHourListController();
	}
	
	@Test
	void testQueryData() {
		assertNotNull(dhlc);
		dhlc.queryData();
	}
}
