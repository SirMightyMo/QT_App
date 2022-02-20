package main.java;

import javax.swing.JOptionPane;

import main.java.controller.AppLocker;
import main.java.controller.DatabaseController;
import main.java.controller.LayoutManager;
import main.java.controller.LoginController;

public class MainMethod {

	public static void main(String[] args) throws Exception {
		new LayoutManager();
		startApp();
		
	}
	
	/**
	 * Starts the App if there is not running an instance of the application
	 * already. 
	 * @see main.java.controller.AppLocker#isAppActive()
	 */
	static void startApp() {
        AppLocker al = new AppLocker();

        if (al.isAppActive()) {
        	JOptionPane.showConfirmDialog(null, 
                    "Es läuft bereits eine Instanz dieser Anwendung.", "Fehler!", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
            System.exit(1);    
        }
        else {
            System.out.println("NOT already active.");
            // Initialize database
    		DatabaseController dbc = DatabaseController.getInstance();
    		dbc.initializeDB();
    		new LoginController();
        }
    }
}