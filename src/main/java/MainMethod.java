package main.java;

import main.java.controller.DatabaseController;
import main.java.controller.LayoutManager;
import main.java.controller.LoginController;

public class MainMethod {

	public static void main(String[] args) throws Exception {

		// Initialize database
		DatabaseController dbc = DatabaseController.getInstance();
//		dbc.initializeDB();
		
		// Load layout settings
		new LayoutManager();
		new LoginController();
	}
}