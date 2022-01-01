package main.java;

import main.java.controller.DatabaseController;
import main.java.controller.TestHourEntryController;
import main.java.controller.TimerHourController;

public class MainMethod {

	public static void main(String[] args) throws Exception {
		DatabaseController dbc = new DatabaseController("sa", "");
		dbc.initializeDB();
		new TimerHourController();
//		new TestHourEntryController();

	}
}
