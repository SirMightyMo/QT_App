package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.model.DashboardModel;
import main.java.model.HourEntry;
import main.java.model.TimerModel;
import main.java.view.DashboardView;
import main.java.view.TimerView;

public class DashboardController implements ActionListener, DocumentListener {

	private TimerModel timerModel;
	private DashboardModel dashboardModel;
	private DashboardView dashboardView;
	private TimerView timerView;
	private HourEntry hourEntry;

	private LocalDateTime timeNow;
	private ArrayList<String> projectList;

	// Constructor
	@SuppressWarnings("deprecation")
	public DashboardController() {
		this.dashboardModel = new DashboardModel();
		this.dashboardView = new DashboardView(this);

		this.dashboardModel.addObserver(this.dashboardView);
		this.dashboardView.setVisible(true);
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

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

	public HourEntry getHourEntry() {
		return hourEntry;
	}

	public void setHourEntry(HourEntry hourEntry) {
		this.hourEntry = hourEntry;
	}

	public LocalDateTime getTimeNow() {
		return timeNow;
	}

	public void setTimeNow(LocalDateTime timeNow) {
		this.timeNow = timeNow;
	}

	public DashboardModel getDashboardModel() {
		return dashboardModel;
	}

	public void setDashboardModel(DashboardModel dashboardModel) {
		this.dashboardModel = dashboardModel;
	}

	public ArrayList<String> getProjectList() {
		return projectList;
	}

	public void setProjectList(ArrayList<String> projectList) {
		this.projectList = projectList;
	}

}
