package main.java.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
		hourEntry = new HourEntry(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE), //date
				timeNow); // startTime
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
			if (this.hourEntry.getEndTime() == null) {
				this.hourEntry.setEndTime(timeNow);
			}
			if (this.hourEntry.getPauseStart() != null && this.hourEntry.getPauseEnd() == null) {
				this.hourEntry.setPauseEnd(timeNow);
			}
		}
	}
	
	public void actionPauseTimer() {
		if (this.timerModel.isTimerRunning()) {
			if (this.hourEntry.getPauseStart() != null) {
				this.hourEntry.setPauseEnd(null);
			}
			setTimeNow();
			this.timerModel.pauseTimer();
			this.hourEntry.setPauseStart(timeNow);
		}
	}
	
	public void actionSaveTimer() {
		if (this.hourEntry != null) {
			actionStopTimer();
			
			String comment = this.timerView.getTextFieldComment().getText();
			this.hourEntry.setComment(comment);
			
			// TODO: Get project-id instead of name
			String project = this.timerView.getComboBox().getSelectedItem().toString();
			
			this.hourEntry.setProject(project);
			
			//TODO: tbd: what about pause durations? columns in DB
			DatabaseController db = new DatabaseController("sa", "");
			Date entryDate = Date.valueOf(this.hourEntry.getDate());
			String description = this.hourEntry.getComment();
			Timestamp startTime = Timestamp.valueOf(this.hourEntry.getStartTime());
			Timestamp endTime = Timestamp.valueOf(this.hourEntry.getEndTime());
			int timeHours = (int) this.hourEntry.getSessionTimeInSeconds() / 3600;
			int timeMinutes = (int) (this.hourEntry.getSessionTimeInSeconds() - timeHours * 3600) / 60; // FRAGE: Dauer gesplittet oder nur umgerechnet?
			int projectID = 1; // TODO: get correct project-ID
			
			
			db.insert("INSERT INTO hour_entry(entry_date,description,start_time,end_time,time_hours,time_minutes,p_id) VALUES("
					+ "'" + entryDate + "',"
					+ "'" + description + "',"
					+ "'" + startTime + "',"
					+ "'" + endTime + "',"
					+ "'" + timeHours + "',"
					+ "'" + timeMinutes + "',"
					+ "'" + projectID + "')");
			
			actionResetTimer();
		}
	}
	
	public void actionResetTimer() {
		this.timerModel.stopAndResetTimer();
		this.timerView.getTextFieldComment().setText("");
		this.hourEntry = null;
	}
	
	public void actionLoadProjects() {
		this.timerModel.retrieveProjects();
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
