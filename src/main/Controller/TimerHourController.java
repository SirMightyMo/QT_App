package main.Controller;

import java.time.LocalDateTime;

import main.Model.HourEntry;
import main.Model.Project;
import main.Model.Service;
import main.Model.StaticActions;
import main.Model.TimerModel;
import main.Model.User;
import main.View.TimerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class TimerHourController implements ActionListener {

	private TimerModel timerModel;
	private TimerView timerView;
	private LocalDateTime timeNow;
	private HourEntry hourEntry;

	// Constructor
	@SuppressWarnings("deprecation")
	public TimerHourController() {
		this.timerModel = new TimerModel();
		this.timerView = new TimerView(this);

		this.timerModel.addObserver(this.timerView);
		this.timerView.setVisible(true);

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

	// Additional Methods
	public void createHourEntry() {
		hourEntry = new HourEntry(timeNow.getDayOfMonth() + "-" + timeNow.getMonthValue() + "-" + timeNow.getYear(), // date
				timeNow // startTime
		);
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
		System.out.println("ACTION: " + event.toString());
		// Start
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_START)) {
			// Timer
			setTimeNow(); // set time now

			/*
			 * Hour Entry
			 * 
			 * If not exists, create here. If already exists and pause not ended, end it
			 * here.
			 */
			if (this.hourEntry == null) {
				createHourEntry();
			} else if (this.hourEntry.getPauseEnd() == null && !this.timerModel.isTimerRunning()) {
				this.hourEntry.setPauseEnd(timeNow);
			}
			this.timerModel.startTimer();
		}
		// Pause
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_PAUSE)) {
			if (this.timerModel.isTimerRunning()) {
				if (this.hourEntry.getPauseStart() != null) {
					this.hourEntry.setPauseEnd(null);
				}
				setTimeNow(); // set time now
				this.timerModel.pauseTimer();
				this.hourEntry.setPauseStart(timeNow);
			}
		}
		// Stop
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_STOP)) {
			setTimeNow(); // set time now
			this.timerModel.stopAndResetTimer();
			if (this.hourEntry != null) {
				this.hourEntry.setEndTime(timeNow);
				if (this.hourEntry.getPauseStart() != null && this.hourEntry.getPauseEnd() == null) {
					this.hourEntry.setPauseEnd(timeNow);
				}
				DatabaseController db = new DatabaseController("sa", "");

				String date = this.hourEntry.getDate();
				long sessionTimeInSeconds = this.hourEntry.getSessionTimeInSeconds();
				long pauseTimeInSeconds = this.hourEntry.getPauseTimeInSeconds();
				String startTime = this.hourEntry.getStartTime().toString();
				String endTime = this.hourEntry.getEndTime().toString();
				// TODO: define all values

				db.insert(
						"INSERT INTO hourentries (date, username, project, service, starttime, endtime, comment, durationInSeconds, pauseInSeconds) VALUES ("
								+ date + "," + "'Testuser'" + "," + "'Testprojekt'" + "," + "'Leistung1'" + "," + "'"
								+ startTime.toString() + "'," + "'" + endTime.toString() + "'," + "'TestKommentar'"
								+ "," + sessionTimeInSeconds + "," + pauseTimeInSeconds + ")");

				this.hourEntry = null;
			}
		}
	}
}
