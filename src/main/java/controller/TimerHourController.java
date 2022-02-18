package main.java.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

import javax.swing.event.DocumentEvent;

import main.java.model.HourEntry;
import main.java.model.IModel;
import main.java.model.Regex;
import main.java.model.StaticActions;
import main.java.model.TimerModel;
import main.java.model.User;
import main.java.view.IView;
import main.java.view.TimerView;

public class TimerHourController implements IController {

	private TimerModel timerModel;
	private TimerView timerView;
	private HourEntry hourEntry;
	private DatabaseController db = DatabaseController.getInstance();

	private LocalDateTime timeNow;
	
	boolean dateAutomaticallySet; // for handling date-actions

	// Constructor
	@SuppressWarnings("deprecation")
	public TimerHourController() {
		this.timerModel = new TimerModel();
		this.timerView = new TimerView(this);
		this.timerModel.addObserver(this.timerView);

		actionLoadProjects();
		actionLoadServices();
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
	 * Method creates a new hour entry with current time and date.
	 */	
	public void createHourEntry() {
		this.hourEntry = new HourEntry(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)); // date
		this.hourEntry.setStartTime(timeNow); // startTime
	}
	
	/*
	 * Method created a new hour entry with given date (String dd-mm-YYYY).
	 */
	public void createHourEntry(String date) {
		this.hourEntry = new HourEntry(date);
	}

	/*
	 * Method starts the timer.
	 * If the timer is running its first time, this method creates an hour entry, 
	 * fills and disables input fields automatically: 'from', 'to', 'date'
	 * 
	 * If the timer was paused, it sets end time of pause in the current hout entry
	 * and resumes the timer.
	 * 
	 * When trying to start a stopped timer, but the time recording has not been 
	 * saved yet, it highlights the 'reset' and 'save' button without further action.
	 * 
	 * The method sets the timer duration text to color green.
	 */
	public void actionStartTimer() {
		// If hour entry does not exist, create here. If it already exists and pause was
		// not ended, end it here.
		setTimeNow();
		// Autofill and deactivate date field & button
		dateAutomaticallySet = true;
		timerView.getTxtDateInput().setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
		this.timerView.getTxtDateInput().setEnabled(false);
		this.timerView.getBtnDatePicker().setEnabled(false);
		if (this.hourEntry == null || this.hourEntry.getStartTime() == null) {
			this.timerModel.stopAndResetTimer();
			this.timerView.getTxtStartTime().setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
			this.timerView.getTxtEndTime().setText("");
			this.timerView.getTextPauseDuration().setText("");
			createHourEntry();
		} else if (this.hourEntry.getPauseStart() != null && this.hourEntry.getPauseEnd() == null
				&& !this.timerModel.isTimerRunning()) {
			this.hourEntry.setPauseEnd(timeNow);
		}
		if (this.hourEntry.getEndTime() != null) { // if timer was stopped, but not saved/resetted
			
			// Show visual warning, to reset or save current recordings
			if (!timerView.isButtonsHighlighted()) {				
				Color defaultColor = timerView.getBtnSave().getBackground();
				
				timerView.getBtnSave().setBackground(new Color(50,205,50));
				timerView.getBtnReset().setBackground(Color.ORANGE);
				timerView.getBtnSave().setForeground(new Color(31,32,33));
				timerView.getBtnReset().setForeground(new Color(31,32,33));
				timerView.setButtonsHighlighted(true);
				
				ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
				executorService.schedule(new Runnable() {
					@Override
					public void run() {
						timerView.getBtnSave().setBackground(defaultColor);
						timerView.getBtnReset().setBackground(defaultColor);
						timerView.getBtnSave().setForeground(Color.WHITE);
						timerView.getBtnReset().setForeground(Color.WHITE);
						timerView.setButtonsHighlighted(false);
					}
				}, 1, TimeUnit.SECONDS);
			}
			
			
			
			return;
		}
		this.timerModel.startTimer();
		this.timerView.getDurationLabel().setForeground(new Color(50,205,50));
	}

	/*
	 * This method stops the timer, sets the endTime in the current hour entry
	 * and fills out the input fields with calculated information (duration, to, pause).
	 * The method sets the timer duration text to color red.
	 */
	public void actionStopTimer() {
		setTimeNow(); // set time now
		this.timerModel.stopTimer();
		if (this.hourEntry != null) {
			if (this.hourEntry.getEndTime() == null) {
				this.hourEntry.setEndTime(timeNow);
				this.timerView.getTxtEndTime()
						.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
			}
			this.timerView.getTextPauseDuration().setText(this.hourEntry.pauseMinutesToFormattedString());
		}
		this.timerView.getDurationLabel().setForeground(new Color(220,20,60));
	}

	/*
	 * If the timer is running and not paused, this method pauses the timer, 
	 * sets the pause start time and deletes the paus end time (sets null).
	 * The method sets the timer duration text to color orange.
	 */
	public void actionPauseTimer() {
		if (this.timerModel.isTimerRunning()) {
			if (this.hourEntry.getPauseStart() != null) {
				this.hourEntry.setPauseEnd(null);
			}
			setTimeNow();
			this.timerModel.pauseTimer();
			this.hourEntry.setPauseStart(timeNow);
			this.timerView.getDurationLabel().setForeground(Color.ORANGE);
		}
	}

	/*
	 * This method calculates the duration based on start-, end- and pause-times
	 * and sets the duration label text.
	 * This method is for showing information only and is not connected to the 
	 * running hour entry.
	 */
	public void actionCalculateDurationView() {
		if (!timerModel.isTimerRunning()) {

			String start = this.timerView.getTxtStartTime().getText();
			String end = this.timerView.getTxtEndTime().getText();
			String pause = this.timerView.getTextPauseDuration().getText();

			if ((pause.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$") || pause.matches("^\\d+[,||.]\\d{2}$")
					|| pause.matches("")) && start.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
					&& end.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {

				int pauseMinutes = 0;

				if (pause.replace(" ", "").equals("")) {
					pauseMinutes = 0;
				} else if (pause.matches("^\\d+[,|.]\\d{2}$")) {
					pauseMinutes = (int) (Float.parseFloat(pause.replace(",", ".")) * 60);
				} else {
					String[] split = pause.split(":");
					int hours = Integer.parseInt(split[0]);
					int minutes = Integer.parseInt(split[1]);
					pauseMinutes = hours * 60 + minutes;
				}

				int durationMinutes;
				int durationMinusPause;

				// if end time is before starttime, next day has started. Therefore the
				// productive minutes are calculated
				if (LocalTime.parse(end, DateTimeFormatter.ofPattern("H:mm"))
						.compareTo(LocalTime.parse(start, DateTimeFormatter.ofPattern("H:mm"))) < 0) {
					String[] startSplit = start.split(":");
					int startHours = Integer.parseInt(startSplit[0]);
					int startMinutes = Integer.parseInt(startSplit[1]);

					String[] endSplit = end.split(":");
					int endHours = Integer.parseInt(endSplit[0]);
					int endMinutes = Integer.parseInt(endSplit[1]);

					int minutesToMidnight = (24 - startHours) * 60 + startMinutes;
					int minutesAfterMidnight = endHours * 60 + endMinutes;

					durationMinutes = minutesToMidnight + minutesAfterMidnight;
					// else (start lies before end) entries are made for the same day
				} else {
					long durationSeconds = Duration.between(LocalTime.parse(start, DateTimeFormatter.ofPattern("H:mm")),
							LocalTime.parse(end, DateTimeFormatter.ofPattern("H:mm"))).getSeconds();
					durationMinutes = Math.round(durationSeconds / 60);
				}

				durationMinusPause = durationMinutes - pauseMinutes; // calculate productive minutes

				int minutesInt = durationMinusPause % 60;
				String minutes = Integer.toString(minutesInt);
				int hoursInt = durationMinusPause / 60;
				String hours = Integer.toString(hoursInt);
				if (minutesInt < 10) {
					minutes = "0" + minutes;
				}
				if (hoursInt < 10) {
					hours = "0" + hoursInt;
				}

				timerView.getDurationLabel().setText(hours + ":" + minutes + ":00");
			}
		}
	}

	/*
	 * actionSaveTimer()
	 * 
	 * Calls method 'actionStopTimer()' which stops the timer and writes all entries
	 * based on current time. The 'actionStopTimer()' method checks first, if the
	 * timer is running. When the timer is stopped, all editable time fields will be
	 * automatically filled with the needed information.
	 * 
	 * This means: If the timer was running, there was already created an hour entry
	 * and the timer will be stopped. If the timer was not running, fields will be
	 * empty if not edited manually.
	 * 
	 * This method 'actionSaveTimer()' will create an hour entry, if none was found
	 * (this.hourentry == null).
	 * 
	 * It then will not only read the editable form fields, but it will also
	 * overwrite the information in the hour entry before reading it again for
	 * inserting it into the database. This way the used instance of HourEntry will
	 * temporarily contain the same information written to the database, in case it
	 * is needed elsewhere later.
	 * 
	 */
	public void actionSaveTimer() {
		// stop timer if it is running and set timefields (start, stop, pause) with
		// information
		// when timer is stopped, editable time field are automatically filled with
		// needed information
		//
		if (this.timerModel.isTimerRunning()) {
			actionStopTimer();
		}

		Date entryDate;
		String comment;
		Timestamp startTime;
		Timestamp endTime;
		int timeMinutes;
		int pauseMinutes = 0;
		int projectID;
		int serviceID;
		int userID;

		boolean pauseMinutesValid = true;

		// if timer was not used (not started), hour entry was not created and needs to
		// be done here
		if (this.hourEntry == null) {
			String date = timerView.getTxtDateInput().getText();
			// if (date.matches("")) {
			//	this.hourEntry = new HourEntry(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));				
			//}
			if (validateDate(date)) {
				String[] dateParts;
				String dateString;
				if (date.contains(".")) {
					dateParts = timerView.getTxtDateInput().getText().split("\\.");			
				} else if (date.contains("-")) {
					dateParts = timerView.getTxtDateInput().getText().split("-");			
				} else {
					dateParts = timerView.getTxtDateInput().getText().split("\\/");			
				}
				dateString = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
				this.hourEntry = new HourEntry(dateString);
			} else {
				timerView.showErrorMessage("Invalid or empty date!", 3000);
				return;
			}
		}

		// read entry date
		entryDate = Date.valueOf(this.hourEntry.getDate());
		// Reorder entryDate YYYY-mm-dd to String dd-mm-YYYY for validating
		String entryDateReversed = entryDate.toString().split("-")[2] + "-" + entryDate.toString().split("-")[1] + "-" + entryDate.toString().split("-")[0];

		// read comment from textfield and set comment in hourEntry instance
		comment = this.timerView.getTextFieldComment().getText();
		this.hourEntry.setComment(comment);

		// read hourEntry date and parse
		LocalDate dateEntryLocalDate = LocalDate.parse(this.hourEntry.getDate());

		// read PauseDuration from textfield; needs to be done before setting startTime
		// & endTime, because sessionTime is calculated considering pauseduration
		String pauseMinutesString = this.timerView.getTextPauseDuration().getText();
		// check pause field for value and format and convert to minutes
		if (pauseMinutesString.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")
				|| pauseMinutesString.matches("^\\d+[,||.]\\d{2}$")) {

			if (pauseMinutesString.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
				String[] split = pauseMinutesString.split(":");
				int hours = Integer.parseInt(split[0]);
				int minutes = Integer.parseInt(split[1]);
				pauseMinutes = hours * 60 + minutes;

			} else if (pauseMinutesString.matches("^\\d+[,]\\d{2}$")) {
				String[] split = pauseMinutesString.split(",");
				int hours = Integer.parseInt(split[0]);
				int minutes = Integer.parseInt(split[1]);
				pauseMinutes = hours * 60 + Math.round(minutes * 60 / 100);

			} else if (pauseMinutesString.matches("^\\d+[.]\\d{2}$")) {
				String[] split = pauseMinutesString.split(".");
				int hours = Integer.parseInt(split[0]);
				int minutes = Integer.parseInt(split[1]);
				pauseMinutes = hours * 60 + Math.round(minutes * 60 / 100);
			}
		} else if (pauseMinutesString.replace(" ", "").equals("")) {
			pauseMinutes = 0;
		} else {
			pauseMinutesValid = false; // set flag, when format not valid
		}
		this.hourEntry.setPauseTimeInSeconds(pauseMinutes * 60);
		pauseMinutes = (int) this.hourEntry.getPauseTimeInMinutes();

		// read start time from textfield
		String startTimeField = this.timerView.getTxtStartTime().getText();
		// if startTimeField is empty or not a correct time format (HH:mm) set null
		if (startTimeField.equals("") || !startTimeField.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
			this.hourEntry.setStartTime(null);
			startTime = null;
		} else {
			// create LocalDateTime for startTime, write to hourEntry, read from hourEntry
			// for database
			LocalDateTime startDateTimeField = LocalDateTime.of(dateEntryLocalDate,
					LocalTime.parse(startTimeField, DateTimeFormatter.ofPattern("H:mm")));
			this.hourEntry.setStartTime(startDateTimeField);
			startTime = Timestamp.valueOf(this.hourEntry.getStartTime());
		}

		// read end time from textfield
		String endTimeField = this.timerView.getTxtEndTime().getText();
		// if endTimeField is empty or not a correct time format (HH:mm) set null;
		if (endTimeField.equals("") || !endTimeField.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
			this.hourEntry.setEndTime(null);
			endTime = null;
		} else {
			// create LocalDateTime for endTime, write to hourEntry, read from hourEntry for
			// database
			long plusDays = 0;
			if (endTimeField.compareTo(startTimeField) < 0) { // if endtime is smaller than starttime, it means that
																// another day has started and a day needs to be added
				plusDays = 1;
			}
			LocalDateTime endDateTimeField = LocalDateTime.of(dateEntryLocalDate.plusDays(plusDays),
					LocalTime.parse(endTimeField, DateTimeFormatter.ofPattern("H:mm")));
			this.hourEntry.setEndTime(endDateTimeField);
			endTime = Timestamp.valueOf(this.hourEntry.getEndTime());
		}

		timeMinutes = (int) this.hourEntry.getSessionTimeInMinutes();

		// check index of ComboBox (project list) and get item with same index from
		// project list in timerModel to get projectID
		String project = this.timerView.getProjectDropdown().getSelectedItem().toString();
		this.hourEntry.setProjectName(project);

		int comboBoxIndex = this.timerView.getProjectDropdown().getSelectedIndex();
		projectID = (int) this.timerModel.getProjectList().get(comboBoxIndex).get(0);
		this.hourEntry.setProjectID(projectID);

		int serviceIndex = this.timerView.getServiceDropdown().getSelectedIndex();
		serviceID = (int) this.timerModel.getServiceList().get(serviceIndex).get(0);
		System.out.println(serviceID);
		//serviceID = (int) db.query("SELECT s_id FROM service WHERE name= ", true).get(0);

		userID = User.getUser().getU_id();

		// write hour entry to database only if starTime and endTime are not empty
		if (startTime != null && endTime != null && pauseMinutesValid == true) {
			db.insert(
					"INSERT INTO hour_entry(entry_date,description,start_time,end_time,time_minutes,pause_minutes,p_id,s_id,u_id) VALUES("
							+ "'" + entryDate + "'," 
							+ "'" + comment + "'," 
							+ "'" + startTime + "'," 
							+ "'" + endTime+ "'," 
							+ "'" + timeMinutes + "'," // timeMinutes means productive time (pauseMinutes is subtracted)
							+ "'" + pauseMinutes + "',"
							+ "'" + projectID + "',"
							+ "'" + serviceID + "',"
							+ "'" + userID + "');");

			actionResetTimer();
			actionLoadProjects();
			actionLoadServices();
		} else {
			String errorStart = "";
			String errorEnd = "";
			String errorPause = "";
			if (startTime == null)
				errorStart = "'Von:' ";
			if (endTime == null)
				errorEnd = "'Bis:' ";
			if (pauseMinutesValid == false)
				errorPause = "'Pause:'";
			this.timerView.showErrorMessage("Fehler in Feld(ern): " + errorStart + errorEnd + errorPause, 3000);
		}
	}

	/*
	 * This method calls the stopAndResetTimer method of the timerModel.
	 * It then resets all texts and colors of the visible swing elements to
	 * its default values.
	 */
	public void actionResetTimer() {
		this.timerModel.stopAndResetTimer();
		this.timerView.getTextFieldComment().setText("");
		this.timerView.getTxtDateInput().setText("");
		this.timerView.getTxtDateInput().setEnabled(true);
		timerView.getTxtDateInput().setBackground(new Color(70, 73, 75));
		this.timerView.getBtnDatePicker().setEnabled(true);
		this.timerView.getTxtStartTime().setText("");
		this.timerView.getTxtEndTime().setText("");
		this.timerView.getTextPauseDuration().setText("");
		this.timerView.getLblErrorMessage().setText("");
		this.timerView.getDurationLabel().setForeground(Color.WHITE);
		this.hourEntry = null;
		dateAutomaticallySet = false;
	}

	/*
	 * This method calls methods of timerModel to refresh its project list for
	 * the dropdown.
	 * The method also queries information from db to check for the project that was last
	 * used to write an hour entry and automatically selects it.
	 * If the project was already set manually by the user (using dropdown) it just refreshes
	 * the project list.
	 * 
	 * This method also checks, if the user already has a project assigned. If not, the 
	 * time tracker will be disabled.
	 */
	public void actionLoadProjects() {
		this.timerModel.setProjectSet(false);
		this.timerModel.retrieveProjects();
		activateTimeTracker();
		
		// if service was not set yet, select last used service (highest h_id)
		if ((this.hourEntry == null || this.hourEntry.getProjectID() == 0) && !this.timerModel.isProjectSet()) {

			ArrayList<Object> result = db.query("SELECT p_id FROM hour_entry WHERE u_id = " + User.getUser().getU_id() +" ORDER BY h_id DESC LIMIT 1;");
			if (!result.isEmpty()) {
				// find out projectListIndex by looking for p_id in ArrayList projectList of
				// timerModel
				int projectListIndex = 0; // initialize variable for list index in timerView
				// get actual projectID of latest project used:
				int latestHourEntryProjectID = (int) ((ArrayList<Object>) result.get(0)).get(0);

				// iterator through project list of timerModel for every project
				for (ArrayList<Object> project : this.timerModel.getProjectList()) {
					// if one of the projectIDs equal the projectID of the latest project used,
					// condition is met
					if ((int) project.get(0) == latestHourEntryProjectID) {
						projectListIndex = this.timerModel.getProjectList().indexOf(project);
						break;
					}
				}
				// set selected item to latest project
				this.timerView.getProjectDropdown().setSelectedIndex(projectListIndex);
			}
			// check if user has projects; if not, deactivate time tracking
			ArrayList<Object> userProjects = db.query("SELECT project.p_id FROM project LEFT JOIN assign_project_user ON project.p_id = assign_project_user.p_id WHERE u_id = " + User.getUser().getU_id() + ";");
			if (userProjects.isEmpty()) {
				deactivateTimeTracker();
			}
		}
	}
	
	/*
	 * This method calls methods of timerModel to refresh its service list for
	 * the dropdown.
	 * The method also queries information from db to check for the service that was last
	 * used to write an hour entry and automatically selects it.
	 * If the service was already set manually by the user (using dropdown) it just refreshes
	 * the service list.
	 * 
	 * This method also checks, if there is a service to select from. If not, the 
	 * time tracker will be disabled.
	 */
	public void actionLoadServices() {
		this.timerModel.setServiceSet(false);
		this.timerModel.retrieveServices();
		activateTimeTracker();
		
		// if project was not set yet, select last used project (highest h_id)
		if ((this.hourEntry == null || this.hourEntry.getService() == null) && !this.timerModel.isServiceSet()) {

			ArrayList<Object> result = db.query("SELECT hour_entry.s_id, service.name FROM hour_entry LEFT JOIN service ON hour_entry.s_id = service.s_id WHERE u_id = " + User.getUser().getU_id() + " ORDER BY h_id DESC LIMIT 1;");
			if (!result.isEmpty()) {
				// find out projectListIndex by looking for p_id in ArrayList projectList of
				// timerModel
				int serviceListIndex = 0; // initialize variable for list index in timerView
				// get actual projectID of latest project used:
				int latestHourEntryServiceID = (int) ((ArrayList<Object>) result.get(0)).get(0);

				// iterator through project list of timerModel for every project
				for (ArrayList<Object> service : this.timerModel.getServiceList()) {
					// if one of the projectIDs equal the projectID of the latest project used,
					// condition is met
					if ((int) service.get(0) == latestHourEntryServiceID) {
						serviceListIndex = this.timerModel.getServiceList().indexOf(service);
						break;
					}
				}
				// set selected item to latest project
				this.timerView.getServiceDropdown().setSelectedIndex(serviceListIndex);
			}
			// check if there are services; if not, deactivate time tracking
			ArrayList<Object> services = db.query("SELECT s_id FROM service;");
			if (services.isEmpty()) {
				deactivateTimeTracker();
			}
		}
	}

	/*
	 * This method disables all elements and shows an error message
	 * to the user that no project is available for tracking time on.
	 */
	private void deactivateTimeTracker() {
		timerView.getProjectDropdown().setEnabled(false);
		timerView.getTxtDateInput().setEnabled(false);
		timerView.getBtnDatePicker().setEnabled(false);
		timerView.getTxtStartTime().setEnabled(false);
		timerView.getTextPauseDuration().setEnabled(false);
		timerView.getTxtEndTime().setEnabled(false);
		timerView.getTextFieldComment().setEnabled(false);
		timerView.getBtnReset().setEnabled(false);
		timerView.getBtnSave().setEnabled(false);
		timerView.getBtnStart().setEnabled(false);
		timerView.getBtnPause().setEnabled(false);
		timerView.getBtnStop().setEnabled(false);

		timerView.getLblErrorMessage().setVisible(true);
		timerView.getLblErrorMessage().setText("Please create a new project to track time for!");
		
	}
	
	/*
	 * This method activates all alements.
	 */
	private void activateTimeTracker() {
		timerView.getProjectDropdown().setEnabled(true);
		timerView.getTxtDateInput().setEnabled(true);
		timerView.getBtnDatePicker().setEnabled(true);
		timerView.getTxtStartTime().setEnabled(true);
		timerView.getTextPauseDuration().setEnabled(true);
		timerView.getTxtEndTime().setEnabled(true);
		timerView.getTextFieldComment().setEnabled(true);
		timerView.getBtnReset().setEnabled(true);
		timerView.getBtnSave().setEnabled(true);
		timerView.getBtnStart().setEnabled(true);
		timerView.getBtnPause().setEnabled(true);
		timerView.getBtnStop().setEnabled(true);

		timerView.getLblErrorMessage().setVisible(false);
		timerView.getLblErrorMessage().setText("");
		
	}

	/*
	 * This method validates the date format put into the 
	 * textfield. 
	 * The corresponding regular expression is defined in class "Regex"
	 * and allows dd-mm-YYYY, dd.mm.YYYY, dd/mm/YYYY.
	 */
	private boolean validateDate(String date) {
		Matcher matcher = Regex.VALID_DATE_FORMAT_DD_MM_YYYY.matcher(date);
		System.out.println(date + " is valid: " + matcher.find());
        return matcher.find();
	}
	
	// ActionListener method
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		System.out.println("ACTION: " + event.toString()); // For debugging

		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_START)) {
			actionStartTimer();
			dateAutomaticallySet = true;
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
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_LOAD_SERVICES)) {
			actionLoadServices();
		}

		if (event.equalsIgnoreCase(StaticActions.ACTION_SET_SERVICE)) {
			this.timerModel.setServiceSet(true);
		}
		
	}

	// DocumentListener methods; events fired when content is edited
	@Override
	public void insertUpdate(DocumentEvent e) {
		if (e.getDocument() == timerView.getTxtStartTime().getDocument() || e.getDocument() == timerView.getTxtEndTime().getDocument()) {
			actionCalculateDurationView();
		}
		if (e.getDocument() == timerView.getTxtDateInput().getDocument() && !dateAutomaticallySet) {
			if (validateDate(timerView.getTxtDateInput().getText())) {
				String[] dateParts = timerView.getTxtDateInput().getText().split("\\.");			
				String date = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];
				createHourEntry(date);
				timerView.getTxtDateInput().setBackground(new Color(70, 73, 75));
			} else {
				timerView.getTxtDateInput().setBackground(new Color(175,25,65));
				timerView.showErrorMessage("Invalid date format!", 3000);
			}
		}
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		actionCalculateDurationView();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		actionCalculateDurationView();
	}

	@Override
	public IModel getModel() {
		return null;
	}

	@Override
	public IView getView() {
		return null;
	}
}
