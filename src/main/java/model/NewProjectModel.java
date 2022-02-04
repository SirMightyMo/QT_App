package main.java.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import main.java.controller.DatabaseController;
import main.java.view.NewProjectView;
	
	@SuppressWarnings("deprecation")	
public class NewProjectModel extends Observable implements IModel {
	
		private boolean timerRunning;
		private boolean timerPaused;
		private int timerSeconds;
		private int timerMinutes;
		private int timerHours;
		private boolean projectSet;
		private Timer taskTimer;
		private ArrayList<ArrayList<Object>> projectList;

		/**
		 * Constructor
		 */
		public NewProjectModel() {
			super();
		}

		public void addObserver(NewProjectView newProjectView) {
			// TODO Auto-generated method stub
			
		}

}
