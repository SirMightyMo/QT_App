package main.java.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.model.HourEntry;
import main.java.model.IModel;
import main.java.model.Service;
import main.java.model.StaticActions;
import main.java.model.TimerModel;
import main.java.model.User;
import main.java.view.IView;
import main.java.view.TimerView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TimerHourController implements IController {

	private TimerModel timerModel;
	private TimerView timerView;
	private HourEntry hourEntry;

	private LocalDateTime timeNow;

	// Constructor
	@SuppressWarnings("deprecation")
	public TimerHourController() {
		this.timerModel = new TimerModel();
		this.timerView = new TimerView(this);
		this.timerModel.addObserver(this.timerView);

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
		this.hourEntry = new HourEntry(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE)); // date
		this.hourEntry.setStartTime(timeNow); // startTime
	}

	public void actionStartTimer() {
		// If hour entry does not exist, create here. If it already exists and pause was
		// not ended, end it here.
		setTimeNow();
		if (this.hourEntry == null) {
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
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						timerView.getBtnSave().setBackground(defaultColor);
						timerView.getBtnReset().setBackground(defaultColor);
						timerView.getBtnSave().setForeground(Color.WHITE);
						timerView.getBtnReset().setForeground(Color.WHITE);
						timerView.setButtonsHighlighted(false);
					}
				}, 1000);
			}
			return;
		}
		this.timerModel.startTimer();
		this.timerView.getDurationLabel().setForeground(new Color(50,205,50));
	}

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

	// Calculates duration from input field for setting view (not connected to hour
	// entry)
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
			this.hourEntry = new HourEntry(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE));
		}

		// read entry date
		entryDate = Date.valueOf(this.hourEntry.getDate());

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
		String project = this.timerView.getComboBox().getSelectedItem().toString();
		this.hourEntry.setProjectName(project);

		int comboBoxIndex = this.timerView.getComboBox().getSelectedIndex();
		projectID = (int) this.timerModel.getProjectList().get(comboBoxIndex).get(0);
		this.hourEntry.setProjectID(projectID);

		serviceID = 1; // TODO: implement, when TimerView holds service-dropdown and ServiceModel is
						// implemented

		userID = 1; // TODO: read and set, when login sets userID correctly

		// write hour entry to database only if starTime and endTime are not empty
		if (startTime != null && endTime != null && pauseMinutesValid == true) {
			DatabaseController db = new DatabaseController("sa", "");
			db.insert(
					"INSERT INTO hour_entry(entry_date,description,start_time,end_time,time_minutes,pause_minutes,p_id) VALUES("
							+ "'" + entryDate + "'," + "'" + comment + "'," + "'" + startTime + "'," + "'" + endTime
							+ "'," + "'" + timeMinutes + "'," // timeMinutes means productive time (pauseMinutes is
																// subtracted)
							+ "'" + pauseMinutes + "'," + "'" + projectID + "')");

			actionResetTimer();
			actionLoadProjects();
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

	public void actionResetTimer() {
		this.timerModel.stopAndResetTimer();
		this.timerView.getTextFieldComment().setText("");
		this.timerView.getTxtStartTime().setText("");
		this.timerView.getTxtEndTime().setText("");
		this.timerView.getTextPauseDuration().setText("");
		this.timerView.getLblErrorMessage().setText("");
		this.timerView.getDurationLabel().setForeground(Color.WHITE);
		this.hourEntry = null;
	}

	public void actionLoadProjects() {
		this.timerModel.setProjectSet(false);
		this.timerModel.retrieveProjects();

		// if project was not set yet, select last used project (highest h_id)
		if ((this.hourEntry == null || this.hourEntry.getProjectID() == 0) && !this.timerModel.isProjectSet()) {

			DatabaseController db = new DatabaseController("sa", "");
			ArrayList<Object> result = db
					.query("SELECT p_id FROM hour_entry WHERE h_id = (SELECT MAX(h_id) FROM hour_entry);");
					//  TODO:Check, if querying for specific user is necessary or if this table already contains
					//  hour entries of user only 
			if (!result.isEmpty()) {
				// find out projectListIndex by looking for p_id in ArrayList projectList of
				// timerModel
				int projectListIndex = 0; // initialize variable for list index in timerView
				int latestHourEntryProjectID = (int) ((ArrayList<Object>) result.get(0)).get(0); // get actual projectID
																									// of latest project
																									// used

				// iterator through project list of timerModel for every project
				for (ArrayList<Object> project : this.timerModel.getProjectList()) {
					// System.out.println(this.timerModel.getProjectList().indexOf(project));
					// System.out.println(project);
					// if one of the projectIDs equal the projectID of the latest project used,
					// condition is met
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

	// ActionListener method
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

	// DocumentListener methods; events fired when content is edited
	@Override
	public void insertUpdate(DocumentEvent e) {
		actionCalculateDurationView();
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IView getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
