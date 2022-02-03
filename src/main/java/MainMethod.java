package main.java;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import main.java.controller.DatabaseController;
import main.java.controller.LayoutManager;
import main.java.controller.DashboardController;
import main.java.controller.ProjectController;
import main.java.controller.TestHourEntryController;
import main.java.controller.TimerHourController;
import main.java.view.TimerView;
import main.java.controller.LoginController;

public class MainMethod {

	public static void main(String[] args) throws Exception {

		// Initialize database
		DatabaseController dbc = DatabaseController.getInstance();
		dbc.initializeDB();
		
		// Load layout settings
		new LayoutManager();

		new LoginController();
				
		
	}
}
