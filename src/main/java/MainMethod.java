package main.java;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import main.java.controller.DatabaseController;
<<<<<<< HEAD
import main.java.controller.DashboardController;
import main.java.controller.ProjectController;
=======
import main.java.controller.TestHourEntryController;
import main.java.controller.TimerHourController;
import main.java.controller.LoginController;
>>>>>>> 9f3ff4f6396d7917afd5a75a58f3646609395e86

public class MainMethod {

	public static void main(String[] args) throws Exception {
<<<<<<< HEAD
		// Initialize database
		DatabaseController dbc = new DatabaseController("sa", "");
		dbc.initializeDB();
		
		// Set look and feel (FlatLaf Theme)
		FlatDarkLaf.setup();
			// customize components with change of properties (see: https://www.formdev.com/flatlaf/customizing/)
		UIManager.put( "Button.arc", 999 );
		UIManager.put( "Component.arc", 999 );
		UIManager.put( "ProgressBar.arc", 999 );
		UIManager.put( "TextComponent.arc", 999 );
		
		// Generate TimerHourController; TODO: replace with dashboard later
		new DashboardController();
		new ProjectController();
=======
		new LoginController();
		DatabaseController dbc = new DatabaseController("sa", "");
		dbc.initializeDB();
		new TimerHourController();
		
>>>>>>> 9f3ff4f6396d7917afd5a75a58f3646609395e86
	}
}
