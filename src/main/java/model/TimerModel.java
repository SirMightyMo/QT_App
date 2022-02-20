package main.java.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import main.java.controller.DatabaseController;

@SuppressWarnings("deprecation")
public class TimerModel extends Observable implements IModel {

	private boolean timerRunning;
	private boolean timerPaused;
	private int timerSeconds;
	private int timerMinutes;
	private int timerHours;
	private Timer taskTimer;
	private ArrayList<ArrayList<Object>> projectList = new ArrayList<>();
	private boolean projectSet;
	private ArrayList<ArrayList<Object>> serviceList = new ArrayList<>();
	private boolean serviceSet;
	private DatabaseController db = DatabaseController.getInstance();

	public TimerModel() {
		super();
		this.timerHours = 0;
		this.timerMinutes = 0;
		this.timerHours = 0;
	}

	public boolean isTimerRunning() {
		return timerRunning;
	}

	public void setTimerRunning(boolean timerRunning) {
		this.timerRunning = timerRunning;
	}

	public boolean isTimerPaused() {
		return timerPaused;
	}

	public void setTimerPaused(boolean timerPaused) {
		this.timerPaused = timerPaused;
	}

	public int getTimerSeconds() {
		return timerSeconds;
	}

	public void setTimerSeconds(int timerSeconds) {
		this.timerSeconds = timerSeconds;
	}

	public int getTimerMinutes() {
		return timerMinutes;
	}

	public void setTimerMinutes(int timerMinutes) {
		this.timerMinutes = timerMinutes;
	}

	public int getTimerHours() {
		return timerHours;
	}

	public void setTimerHours(int timerHours) {
		this.timerHours = timerHours;
	}

	public boolean isProjectSet() {
		return projectSet;
	}

	public void setProjectSet(boolean projectSet) {
		this.projectSet = projectSet;
	}

	public ArrayList<ArrayList<Object>> getProjectList() {
		return projectList;
	}

	public void setProjectList(ArrayList<ArrayList<Object>> projectList) {
		this.projectList = projectList;
	}

	public ArrayList<ArrayList<Object>> getServiceList() {
		return serviceList;
	}

	public void setServiceList(ArrayList<ArrayList<Object>> serviceList) {
		this.serviceList = serviceList;
	}

	public boolean isServiceSet() {
		return serviceSet;
	}

	public void setServiceSet(boolean serviceSet) {
		this.serviceSet = serviceSet;
	}

	/**
	 * Starts a new TimerTask to update 
	 * the time information in this model every
	 * second.
	 * @author Leander
	 */
	public void startTimer() {
		if (!timerRunning) {
			this.setTimerRunning(true);
			this.setTimerPaused(false);
			taskTimer = new Timer();
			taskTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					updateTimer();
				}
			}, 1000, 1000); // delay one second, one second interval
		}
	}

	/**
	 * Cancels (Stops) the TimerTask (if it
	 * is running) so time information is no 
	 * longer updated.
	 * @author Leander
	 */
	public void pauseTimer() {
		if (timerRunning) {
			taskTimer.cancel();
			this.setTimerRunning(false);
			this.setTimerPaused(true);
		}
	}

	/**
	 * Stops the timer if it is running or 
	 * paused.
	 * @author Leander
	 */
	public void stopTimer() {
		if (timerRunning || timerPaused) {
			taskTimer.cancel();
			this.setTimerRunning(false);
			this.setTimerRunning(false);
		}
	}

	/**
	 * Resets time information to default 
	 * values (0) and notifies the models 
	 * observers.
	 */
	public void stopAndResetTimer() {
		stopTimer();
		this.setTimerHours(0);
		this.setTimerMinutes(0);
		this.setTimerSeconds(0);
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Adds one second every time this 
	 * method is being called. Increases  
	 * minutes and hours if nessecary.<br>
	 * Notifes the models observers.
	 */
	public void updateTimer() {
		if (timerRunning) {
			timerSeconds += 1;
			if (timerSeconds == 60) {
				timerSeconds = 0;
				timerMinutes += 1;
				if (timerMinutes == 60) {
					timerMinutes = 0;
					timerHours += 1;
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}

	/**
	 * Creates a String from the stored time information
	 * in format HH:mm or HH:mm:ss depending on the given 
	 * boolean argument.
	 * @param showSeconds Set true if seconds should be 
	 * returned in String.
	 * @return String in format HH:mm or HH:mm:ss
	 */
	public String timerToString(boolean showSeconds) {
		String seconds = Integer.toString(timerSeconds);
		String minutes = Integer.toString(timerMinutes);
		String hours = Integer.toString(timerHours);
		if (timerSeconds < 10) {
			seconds = "0" + timerSeconds;
		}
		if (timerMinutes < 10) {
			minutes = "0" + timerMinutes;
		}
		if (timerHours < 10) {
			hours = "0" + timerHours;
		}
		if (showSeconds) {
			return hours + ":" + minutes + ":" + seconds;
		} else {
			return hours + ":" + minutes;
		}
	}

	/**
	 * This method retrieves all projects the current user is assigned to from the
	 * database and adds them to an ArrayList for using them in a ComboBox
	 * (Dropdown).
	 * @author Leander
	 */
	public void retrieveProjects() {
		this.projectList = new ArrayList<>();
		ArrayList<Object> result = db.query(
				"SELECT project.p_id, name FROM project LEFT JOIN assign_project_user ON project.p_id = assign_project_user.p_id WHERE active = TRUE AND u_id = "
						+ User.getUser().getU_id() + ";");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.projectList.add(row);
		});
		setChanged();
		notifyObservers(this);
	}

	/**
	 * This method retrieves all services and adds them to an ArrayList for using
	 * them in a ComboBox (Dropdown).
	 * @author Leander
	 */
	public void retrieveServices() {
		this.serviceList = new ArrayList<>();
		ArrayList<Object> result = db.query("SELECT s_id, name FROM service;");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.serviceList.add(row);
		});
		setChanged();
		notifyObservers(this);
	}
}
