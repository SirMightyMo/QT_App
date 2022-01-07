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
			this.timerView.getTxtStartTime().setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
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
				this.timerView.getTxtEndTime().setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
			}
			if (this.hourEntry.getPauseStart() != null && this.hourEntry.getPauseEnd() == null) {
				this.hourEntry.setPauseEnd(timeNow);
			}
			this.timerView.getTextPauseDuration().setText(this.hourEntry.pauseMinutesToFormattedString());
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
	
	// TODO: Use manual textfields for writing into database and then activate them for editing.
	public void actionSaveTimer() {
		if (this.hourEntry != null) {
			actionStopTimer();
			
			String comment = this.timerView.getTextFieldComment().getText();
			this.hourEntry.setComment(comment);
			
			String project = this.timerView.getComboBox().getSelectedItem().toString();
			int comboBoxIndex = this.timerView.getComboBox().getSelectedIndex();
			//System.out.println(this.timerModel.getProjectList().get(projectIndex).get(1));
			
			DatabaseController db = new DatabaseController("sa", "");
			Date entryDate = Date.valueOf(this.hourEntry.getDate());
			String description = this.hourEntry.getComment();
			Timestamp startTime = Timestamp.valueOf(this.hourEntry.getStartTime());
			Timestamp endTime = Timestamp.valueOf(this.hourEntry.getEndTime());
			
			int timeMinutes = (int) this.hourEntry.getSessionTimeInMinutes();
			int pauseMinutes = (int) this.hourEntry.getPauseTimeInMinutes();
			// check index of ComboBox (project list) and get item with same index from project list in timerModel to get projectID
			int projectID = (int) this.timerModel.getProjectList().get(comboBoxIndex).get(0);
			
			this.hourEntry.setProjectName(project);
			this.hourEntry.setProjectID(projectID);
			
			db.insert("INSERT INTO hour_entry(entry_date,description,start_time,end_time,time_minutes,pause_minutes,p_id) VALUES("
					+ "'" + entryDate + "',"
					+ "'" + description + "',"
					+ "'" + startTime + "',"
					+ "'" + endTime + "',"
					+ "'" + timeMinutes + "',"
					+ "'" + pauseMinutes + "',"
					+ "'" + projectID + "')");
			
			actionResetTimer();
			actionLoadProjects();
		}
	}
	
	public void actionResetTimer() {
		this.timerModel.stopAndResetTimer();
		this.timerView.getTextFieldComment().setText("");
		this.hourEntry = null;
	}
	
	public void actionLoadProjects() {
		this.timerModel.setProjectSet(false);
		this.timerModel.retrieveProjects();
		
		// if project was not set yet, select last used project (highest h_id)
		if ((this.hourEntry == null || this.hourEntry.getProjectID() == 0) && !this.timerModel.isProjectSet()) {
			
			DatabaseController db = new DatabaseController("sa", "");
			ArrayList<Object> result = db.query("SELECT p_id FROM hour_entry WHERE h_id = (SELECT MAX(h_id) FROM hour_entry);"); // TODO: Check, if querying for specific user is necessary or if this table already contains hour entries of user only
			if (!result.isEmpty()) {
				// find out projectListIndex by looking for p_id in ArrayList projectList of timerModel
				int projectListIndex = 0; // initialize variable for list index in timerView
				int latestHourEntryProjectID = (int) ((ArrayList<Object>) result.get(0)).get(0); // get actual projectID of latest project used
				
				// iterator through project list of timerModel for every project
				for (ArrayList<Object> project : this.timerModel.getProjectList()) {
									//System.out.println(this.timerModel.getProjectList().indexOf(project));
									//System.out.println(project);
					// if one of the projectIDs equal the projectID of the latest project used, condition is met
					if ((int) project.get(0) == latestHourEntryProjectID) {
						projectListIndex = this.timerModel.getProjectList().indexOf(project);
						break;
					}
				}
				// set selected item to latest project
				this.timerView.getComboBox().setSelectedIndex(projectListIndex);
			}
		}
		
	}

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
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_SET_PROJECT)) {
			this.timerModel.setProjectSet(true);
		}
	}
}
