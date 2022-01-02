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
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private LocalDateTime pauseStart;
	private LocalDateTime pauseEnd;
	private User user;
	private Project project;
	private Service service;
	private String comment;
	
	
	// Constructor
	public HourEntry(String date, LocalDateTime startTime) {
		super();
		this.date = date;
		this.startTime = startTime;
		this.sessionTimeInSeconds = 0;
		this.pauseTimeInSeconds = 0;
		//TODO: Use all variables necessary
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
	}

	public long getPauseTimeInSeconds() {
		return pauseTimeInSeconds;
	}

	public void setPauseTimeInSeconds() {
		long duration = Duration.between(pauseStart, pauseEnd).getSeconds();
		this.pauseTimeInSeconds += duration;
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

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
		

}
