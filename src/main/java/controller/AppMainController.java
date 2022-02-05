package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;

import main.java.model.AppMainModel;
import main.java.model.IModel;
import main.java.model.User;
import main.java.view.AppMainView;
import main.java.view.DashboardView;
import main.java.view.IView;

public class AppMainController implements IController{

	private User user;
	private DashboardView dashboardView;
	private DashboardController dashboardController;
	private AppMainController appMainController;
	private AppMainModel appMainModel;
	private AppMainView appMainView;
	
	boolean hasclicked1=false;
	JLabel click1label=null;
	
	// Constructor
	public AppMainController(User user) {
		this.dashboardController = new DashboardController(user);
		this.setAppMainModel(new AppMainModel());
		this.appMainView = new AppMainView(this);
		this.dashboardView = new DashboardView(this.dashboardController);
		System.out.println(this.dashboardView.getContentPanelDashb());
		/*
		System.out.println("Line 33:");
		System.out.println(this.appMainView);
		*/
		this.appMainView.setVisible(true);
		this.user = user;	
		//this.appMainModel.addObserver(this.appMainView);
	}

	public void mouseClicked(MouseEvent me)
	{
	  if(!hasclicked1){ //clicked first pic
	    hasclicked1 = true;
	    click1label = (JLabel) me.getSource();
	  } else 
	  	{ //clicked second pic
	    hasclicked1 = false;
	    
	  	}
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

	public DashboardController getDashboardController() {
		return dashboardController;
	}

	public void setDashboardController(DashboardController dashboardController) {
		this.dashboardController = dashboardController;
	}

	public DashboardView getDashboardView() {
		return dashboardView;
	}

	public void setDashboardView(DashboardView dashboardView) {
		this.dashboardView = dashboardView;
	}

	public AppMainController getAppMainController() {
		return appMainController;
	}

	public void setAppMainController(AppMainController appMainController) {
		this.appMainController = appMainController;
	}

	public AppMainView getAppMainView() {
		return appMainView;
	}

	public void setAppMainView(AppMainView appMainView) {
		this.appMainView = appMainView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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

	public AppMainModel getAppMainModel() {
		return appMainModel;
	}

	public void setAppMainModel(AppMainModel appMainModel) {
		this.appMainModel = appMainModel;
	}

}

