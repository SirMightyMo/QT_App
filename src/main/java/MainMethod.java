package main.java;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import main.java.controller.DatabaseController;
import main.java.controller.TestHourEntryController;
import main.java.controller.TimerHourController;
import main.java.controller.LoginController;

import main.java.controller.DashboardController;
import main.java.controller.ProjectController;


public class MainMethod {

	public static void main(String[] args) throws Exception {

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
		
		new LoginController();
		
		
		
	}
}
