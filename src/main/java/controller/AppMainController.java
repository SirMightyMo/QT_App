package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.event.DocumentEvent;

import main.java.model.AppMainModel;
import main.java.model.IModel;
import main.java.model.StaticActions;
import main.java.model.User;
import main.java.view.AccountView;
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
	private AccountController accountController;
	private AccountView accountView;
	
	boolean hasclicked1=false;
	JLabel click1label=null;
	
	// Constructor
	public AppMainController(User user) {
		this.dashboardController = new DashboardController(user);
		this.accountController = new AccountController(user);
		this.setAppMainModel(new AppMainModel());
		this.appMainView = new AppMainView(this);
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
		String event = e.getActionCommand();
		System.out.println("ACTION: " + event.toString()); // For debugging

		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_DASHBOARD)) {
			System.out.println("MenuDashboardClick");
			//appMainController.getAppMainView().remove(dashboardView.getDashbPanel());
			this.appMainView.getContentPanel().add(this.appMainView.getDashboardView_1().getDashbPanel());
			this.appMainView.getContentPanel().remove(this.appMainView.getAccountView_1().getAccountPanel());
		}

		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_PROJECTS)) {
			System.out.println("MenuProjectsClick");
		}

		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_SESSIONS)) {
			System.out.println("MenuSessionsClick");
		}

		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_ACCOUNT)) {
			System.out.println("MenuAccountClick");
			this.appMainView.getContentPanel().add(this.appMainView.getAccountView_1().getAccountPanel());
			this.appMainView.getContentPanel().remove(this.appMainView.getDashboardView_1().getDashbPanel());
		
		}

		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_LOGOUT)) {
			System.out.println("MenuLogoutClick");
			this.appMainView.dispose();
			new LoginController();
		}
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

	public AccountController getAccountController() {
		return accountController;
	}

	public void setAccountController(AccountController accountController) {
		this.accountController = accountController;
	}

	public AccountView getAccountView() {
		return accountView;
	}

	public void setAccountView(AccountView accountView) {
		this.accountView = accountView;
	}

}

