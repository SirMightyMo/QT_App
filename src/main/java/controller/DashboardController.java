package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.model.DashboardModel;
import main.java.model.HourEntry;
import main.java.model.IModel;
import main.java.model.TimerModel;
import main.java.model.User;
import main.java.view.DashboardView;
import main.java.view.IView;
import main.java.view.TimerView;

public class DashboardController implements IController {

	private User user;
	private TimerHourController timerHourController;
	private DashboardProjectListController dashboardProjectListController;
	private DashboardHourListController dashboardHourListController;
	private DashboardView dashboardView;

	// Constructor
	@SuppressWarnings("deprecation")
	public DashboardController(User user) {
		// Intanciate Controller
		this.timerHourController = new TimerHourController(user);
		this.dashboardProjectListController = new DashboardProjectListController(user);
		this.dashboardHourListController = new DashboardHourListController(user);
		
		// Test
		new ProjectController(user);
		
		// Instanciate own view
		this.dashboardView = new DashboardView(this);
		this.dashboardView.setVisible(true);
		
		// Set logged in user
		this.user = user;		

		// Coordinate listeners:
		// Lists need to be updated, when new hour entry is being saved
		timerHourController.getTimerView().getBtnSave().addActionListener(dashboardHourListController);
		timerHourController.getTimerView().getBtnSave().addActionListener(dashboardProjectListController);
		
		
	}

	public TimerHourController getTimerHourController() {
		return timerHourController;
	}

	public void setTimerHourController(TimerHourController timerHourController) {
		this.timerHourController = timerHourController;
	}

	public DashboardProjectListController getDashboardProjectListController() {
		return dashboardProjectListController;
	}

	public void setDashboardProjectListController(DashboardProjectListController dashboardProjectListController) {
		this.dashboardProjectListController = dashboardProjectListController;
	}

	public DashboardHourListController getDashboardHourListController() {
		return dashboardHourListController;
	}

	public void setDashboardHourListController(DashboardHourListController dashboardHourListController) {
		this.dashboardHourListController = dashboardHourListController;
	}

	public DashboardView getDashboardView() {
		return dashboardView;
	}

	public void setDashboardView(DashboardView dashboardView) {
		this.dashboardView = dashboardView;
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

}
