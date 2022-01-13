package main.java.model;

import java.time.LocalDateTime;

public class Project {
	
	private int p_id;
	private String projectName;
	private LocalDateTime start_date;
	private LocalDateTime end_date;
	private boolean active;
	private int c_id;
	
	
	
	public Project(int p_id, String projectName, LocalDateTime start_date, LocalDateTime end_date, boolean active,int c_id) {
		super();
		this.p_id = p_id;
		this.projectName = projectName;
		this.start_date = start_date;
		this.end_date = end_date;
		this.active = active;
		this.c_id = c_id;
	}



	public int getP_id() {
		return p_id;
	}



	public void setP_id(int p_id) {
		this.p_id = p_id;
	}



	public String getProjectName() {
		return projectName;
	}



	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}



	public LocalDateTime getStart_date() {
		return start_date;
	}



	public void setStart_date(LocalDateTime start_date) {
		this.start_date = start_date;
	}



	public LocalDateTime getEnd_date() {
		return end_date;
	}



	public void setEnd_date(LocalDateTime end_date) {
		this.end_date = end_date;
	}



	public boolean isActive() {
		return active;
	}



	public void setActive(boolean active) {
		this.active = active;
	}



	public int getC_id() {
		return c_id;
	}



	public void setC_id(int c_id) {
		this.c_id = c_id;
	}

	
	
	
}
