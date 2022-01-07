package main.java.model;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Observable;

@SuppressWarnings("deprecation")
public class HourEntry {
	
	private String date;
	private long sessionTimeInSeconds;
	private long pauseTimeInSeconds;
	private long sessionTimeInMinutes;
	private long pauseTimeInMinutes;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalDateTime pauseStart;
	private LocalDateTime pauseEnd;
	private User user;
	private String projectName;
	private int projectID;
	private String service;
	private String comment;
	
	
	// Constructor
	public HourEntry(String date, LocalDateTime startTime) {
		super();
		this.date = date;
		this.startTime = startTime;
	}

	// Getter/Setter
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public long getSessionTimeInSeconds() {
		return sessionTimeInSeconds;
	}

	public void setSessionTimeInSeconds() {
		long duration = Duration.between(startTime, endTime).getSeconds() - pauseTimeInSeconds;
		this.sessionTimeInSeconds = duration;
		setSessionTimeInMinutes();
	}

	public long getPauseTimeInSeconds() {
		return pauseTimeInSeconds;
	}

	public void setPauseTimeInSeconds() {
		long duration = Duration.between(pauseStart, pauseEnd).getSeconds();
		this.pauseTimeInSeconds += duration;
		setPauseTimeInMinutes();
	}

	public long getSessionTimeInMinutes() {
		return sessionTimeInMinutes;
	}

	public void setSessionTimeInMinutes() {
		this.sessionTimeInMinutes = Math.round((float) this.sessionTimeInSeconds/60);
	}

	public long getPauseTimeInMinutes() {
		return pauseTimeInMinutes;
	}

	public void setPauseTimeInMinutes() {
		this.pauseTimeInMinutes = Math.round((float) this.pauseTimeInSeconds/60);
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		if (pauseStart != null && pauseEnd == null) {
			this.pauseEnd = endTime;
		}
		this.endTime = endTime;
		this.setSessionTimeInSeconds();
	}

	public LocalDateTime getPauseStart() {
		return pauseStart;
	}

	public void setPauseStart(LocalDateTime pauseStart) {
		this.pauseStart = pauseStart;
	}

	public LocalDateTime getPauseEnd() {
		return pauseEnd;
	}

	public void setPauseEnd(LocalDateTime pauseEnd) {
		this.pauseEnd = pauseEnd;
		if (pauseEnd != null) {
			this.setPauseTimeInSeconds();			
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int project) {
		this.projectID = project;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String pauseMinutesToFormattedString() {
		int hours = Math.round((float) pauseTimeInMinutes / 60);
		int minutes = Math.round((pauseTimeInMinutes / 60 - hours) * 60);
		String hourString = Integer.toString(hours);
		String minuteString = Integer.toString(hours);
		if (minutes < 10) {
			minuteString = "0" + minutes;
		}
		if (hours < 10) {
			hourString = "0" + hours;
		}
		return hourString + ":" + minuteString;
	}

}
