package main;


import main.Controller.DatabaseController;
import main.Controller.TestHourEntryController;
import main.Controller.TimerHourController;

public class MainMethod {

	public static void main(String[] args) throws Exception {
		DatabaseController dbc = new DatabaseController("sa", "");
		dbc.initializeDB();
//		new TimerHourController();
//		new TestHourEntryController();

	}
}
