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
import main.java.view.ProjectView;

public class AppMainController implements IController{
	
	//->unused
	//private User user;     
	private DashboardView dashboardView;
	private DashboardController dashboardController;
	private AppMainController appMainController;
	private AppMainModel appMainModel;
	private AppMainView appMainView;
	private AccountController accountController;
	private AccountView accountView;
	private ProjectController projectController;
	private ProjectView projectView;
	private SessionController sessionController;
	String visibleView= "Dashboard";
	
	boolean hasclicked1=false;
	JLabel click1label=null;
	
	// Constructor	
	public AppMainController() {					
		this.dashboardController = new DashboardController();
		this.sessionController= new SessionController(); 
		this.accountController = new AccountController();
		this.projectController = new ProjectController();
		this.setAppMainModel(new AppMainModel());
		this.appMainView = new AppMainView(this);
		this.appMainView.setVisible(true);
		
		// organize listeners
		dashboardController.getDashboardView().getBtnProjectShowMore().addActionListener(this);
		dashboardController.getDashboardView().getBtnSessionShowMore().addActionListener(this);
		sessionController.getSessionView().getBtnSaveEntry().addActionListener(dashboardController.getDashboardHourListController());
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
		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_DASHBOARD)) 
		{
			//appMainController.getAppMainView().remove(dashboardView.getDashbPanel());
			this.appMainView.getContentPanel().add(this.appMainView.getDashboardView().getDashbPanel());
			if(visibleView=="AccountSettings") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getAccountView().getAccountPanel());
			}
			if(visibleView=="Projects") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getProjectView().getProjectPanel());
			}
			if(visibleView=="Sessions") {
				this.appMainView.getContentPanel().remove(this.appMainView.getSessionView().getSessionPanel());
			}
			
			this.appMainView.getContentPanel().repaint();
			visibleView="Dashboard";
		}

		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_PROJECTS)) 
		{
			this.appMainView.getContentPanel().add(this.appMainView.getProjectView().getProjectPanel());
			System.out.println(visibleView);
			if(visibleView=="Dashboard") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getDashboardView().getDashbPanel());
			}
			if(visibleView=="Sessions") {
				this.appMainView.getContentPanel().remove(this.appMainView.getSessionView().getSessionPanel());
			}
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getDashboardView().getDashbPanel());
			}
			if(visibleView=="AccountSettings") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getAccountView().getAccountPanel());
			}
			this.appMainView.getContentPanel().repaint();
			visibleView="Projects";
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_SESSIONS)) {
			this.appMainView.getContentPanel().add(this.appMainView.getSessionView().getSessionPanel());
			if(visibleView=="Dashboard") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getDashboardView().getDashbPanel());
			}
			if(visibleView=="Projects") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getProjectView().getProjectPanel());
			}
			if(visibleView=="AccountSettings") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getAccountView().getAccountPanel());
			}
			this.appMainView.getContentPanel().repaint();
			visibleView="Sessions";
		}

		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_ACCOUNT)) 
		{
			System.out.println("MenuAccountClick");
			this.appMainView.getContentPanel().add(this.appMainView.getAccountView().getAccountPanel());
			if(visibleView=="Dashboard") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getDashboardView().getDashbPanel());
			}
			if(visibleView=="Projects") 
			{
				this.appMainView.getContentPanel().remove(this.appMainView.getProjectView().getProjectPanel());
			}
			if(visibleView=="Sessions") {
				this.appMainView.getContentPanel().remove(this.appMainView.getSessionView().getSessionPanel());
			}
			this.appMainView.getContentPanel().repaint();
			visibleView="AccountSettings";
			System.out.println(visibleView);
		}

		if (event.equalsIgnoreCase(StaticActions.ACTION_MENU_LOGOUT)) 
		{
			this.appMainView.dispose();
			User.setUser(null);
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

	public ProjectController getProjectController() {
		return projectController;
	}

	public void setProjectController(ProjectController projectController) {
		this.projectController = projectController;
	}

	public ProjectView getProjectView() {
		return projectView;
	}

	public void setProjectView(ProjectView projectView) {
		this.projectView = projectView;
	}

	public SessionController getSessionController() {
		return sessionController;
	}

	public void setSessionController(SessionController sessionController) {
		this.sessionController = sessionController;
	}

}

