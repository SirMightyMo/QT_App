package main.java.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import main.java.model.HourEntry;
import main.java.model.Project;
import main.java.model.Service;
import main.java.model.StaticActions;
import main.java.model.TimerModel;
import main.java.model.User;
import main.java.view.TimerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimerHourController implements ActionListener {

	private TimerModel timerModel;
	private TimerView timerView;
	private HourEntry hourEntry;
	
	
	private LocalDateTime timeNow;
	private ArrayList<String> projectList;

	// Constructor
	@SuppressWarnings("deprecation")
	public TimerHourController() {
		this.timerModel = new TimerModel();
		this.timerView = new TimerView(this);
		
		this.timerModel.addObserver(this.timerView);
		this.timerView.setVisible(true);
		
		actionLoadProjects();

	}

	// Getter/Setter
	public TimerModel getTimerModel() {
		return timerModel;
	}

	public void setTimerModel(TimerModel timerModel) {
		this.timerModel = timerModel;
	}

	public TimerView getTimerView() {
		return timerView;
	}

	public void setTimerView(TimerView timerView) {
		this.timerView = timerView;
	}

	public LocalDateTime getTimeNow() {
		return timeNow;
	}

	public void setTimeNow() {
		this.timeNow = LocalDateTime.now();
	}

	public HourEntry getHourEntry() {
		return hourEntry;
	}

	public void setHourEntry(HourEntry hourEntry) {
		this.hourEntry = hourEntry;
	}

	/**
	 * additional methods
	 */
	
	public void createHourEntry() {
		hourEntry = new HourEntry(timeNow.getDayOfMonth() + "-" + timeNow.getMonthValue() + "-" + timeNow.getYear(), // date
				timeNow // startTime
		);
	}
	
	public void actionStartTimer() {
		// If hour entry does not exist, create here. If it already exists and pause was not ended, end it here.
		setTimeNow();
		if (this.hourEntry == null || this.hourEntry.getEndTime() != null) {
			this.timerModel.stopAndResetTimer();
			createHourEntry();
		} else if (this.hourEntry.getPauseEnd() == null && !this.timerModel.isTimerRunning()) {
			this.hourEntry.setPauseEnd(timeNow);
		}
		this.timerModel.startTimer();
	}
	
	public void actionStopTimer() {
		setTimeNow(); // set time now
		this.timerModel.stopTimer();
		if (this.hourEntry != null) {
			this.hourEntry.setEndTime(timeNow);
			if (this.hourEntry.getPauseStart() != null && this.hourEntry.getPauseEnd() == null) {
				this.hourEntry.setPauseEnd(timeNow);
			}
//			DatabaseController db = new DatabaseController("sa", "");
//
//			String date = this.hourEntry.getDate();
//			long sessionTimeInSeconds = this.hourEntry.getSessionTimeInSeconds();
//			long pauseTimeInSeconds = this.hourEntry.getPauseTimeInSeconds();
//			String startTime = this.hourEntry.getStartTime().toString();
//			String endTime = this.hourEntry.getEndTime().toString();
			
			// TODO: write into db

//			db.insert(
//					"INSERT INTO hour_entry (date, username, project, service, starttime, endtime, comment, durationInSeconds, pauseInSeconds) VALUES ("
//							+ date + "," + "'Testuser'" + "," + "'Testprojekt'" + "," + "'Leistung1'" + "," + "'"
//							+ startTime.toString() + "'," + "'" + endTime.toString() + "'," + "'TestKommentar'"
//							+ "," + sessionTimeInSeconds + "," + pauseTimeInSeconds + ")");

			this.hourEntry = null;
		}
	}
	
	public void actionPauseTimer() {
		if (this.timerModel.isTimerRunning()) {
			if (this.hourEntry.getPauseStart() != null) {
				this.hourEntry.setPauseEnd(null);
			}
			setTimeNow(); // set time now
			this.timerModel.pauseTimer();
			this.hourEntry.setPauseStart(timeNow);
		}
	}
	
	public void actionSaveTimer() {
		//TODO: write hour entry into db
		this.hourEntry = null;
	}
	
	public void actionResetTimer() {
		this.timerModel.stopAndResetTimer();
		this.hourEntry = null;
	}
	
	public void actionLoadProjects() {
		this.timerModel.retreiveProjects();
	}
	
//	public void updateCurrentHourEntry(String action) {
//		if (hourEntry != null) {
//			switch(action) {
//			case "stop":
//				hourEntry.setEndTime(timeNow);
//			case "pauseStart":
//				hourEntry.setPauseStart(timeNow);
//			case "pauseStop":
//				hourEntry.setPauseEnd(timeNow);
//			}
//		}
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		System.out.println("ACTION: " + event.toString()); // For debugging
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_START)) {
			actionStartTimer();
		}
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_PAUSE)) {
			actionPauseTimer();
		}
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_STOP)) {
			actionStopTimer();
		}
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_SAVE)) {
			actionSaveTimer();
		}
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_RESET)) {
			actionResetTimer();
		}
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_LOAD_PROJECTS)) {
			actionLoadProjects();
		}
	}
}
