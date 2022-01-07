package main.java.model;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import main.java.controller.DatabaseController;

@SuppressWarnings("deprecation")
public class TimerModel extends Observable{

	private boolean timerRunning;
	private boolean timerPaused;
	private int timerSeconds;
	private int timerMinutes;
	private int timerHours;
	private boolean projectSet;
	private Timer taskTimer;
	private ArrayList<ArrayList<Object>> projectList;
	

	/**
	 *  Constructor
	 */
	public TimerModel() {
		super();
		this.timerHours = 0;
		this.timerMinutes = 0;
		this.timerHours = 0;
	}

	/**
	 *  Getter/Setter
	 */
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
	

	/**
	 *  Additional Methods
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
	
	public void pauseTimer() {
		if (timerRunning) {
			taskTimer.cancel();
			this.setTimerRunning(false);
			this.setTimerPaused(true);	
		}
	}
	
	public void stopTimer() {
		if (timerRunning || timerPaused) {
			taskTimer.cancel();
			this.setTimerRunning(false);
			this.setTimerRunning(false);	
		}
	}
	
	public void stopAndResetTimer() {
		stopTimer();
		this.setTimerHours(0);
		this.setTimerMinutes(0);
		this.setTimerSeconds(0);
		setChanged();
		notifyObservers(this);
	}
	
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
	
	public void retrieveProjects() {
		this.projectList = new ArrayList<>();
		DatabaseController db = new DatabaseController("sa", "");
		ArrayList<Object> result = db.query("SELECT p_id, name FROM project WHERE active = TRUE;");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.projectList.add(row);
			
//			row.forEach(value -> {
//				System.out.println(value);
//			});
		});
		setChanged();
		notifyObservers(this);
	}

}
