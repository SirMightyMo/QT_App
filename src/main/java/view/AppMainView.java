package main.java.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.java.controller.AccountController;
import main.java.controller.AppMainController;
import main.java.controller.DashboardController;
import main.java.controller.DashboardHourListController;
import main.java.controller.DashboardProjectListController;
import main.java.controller.NewProjectController;
import main.java.controller.ProjectController;
import main.java.controller.TimerHourController;
import main.java.model.StaticActions;

public class AppMainView extends WindowSuperclass implements IView {

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JPanel contentPanel;

	//private TimerHourController timerHourController;
	//private DashboardHourListController hourListController;
	//private DashboardProjectListController projectListController;
	//private DashboardView dashboardView;
	//private NewProjectController newProjectController;
	private DashboardController dashboardController;
	private AccountController accountController;
	private ProjectController projectController;
	//private AccountView accountView;
	private DashboardView dashboardView_1;
	private AccountView accountView_1;
	private ProjectView projectView_1;

	
	boolean hasclicked1=false;
	JLabel click1label=null;

	public void mouseClicked(MouseEvent me){
	  if(!hasclicked1){ //clicked first pic
	    hasclicked1 = true;
	    click1label = (JLabel) me.getSource();
	    System.out.println(click1label);
	  } else { //clicked second pic
	    hasclicked1 = false;}
	  }
	/**
	 * Initialize the contents of the frame.
	 */
	public AppMainView(AppMainController appMainController) {
		dashboardController= appMainController.getDashboardController();
		accountController= appMainController.getAccountController();
		projectController= appMainController.getProjectController();
		
		//dashboardView = dashboardController.getDashboardView();
		//accountView = accountController.getAccountView();
		//timerHourController = dashboardController.getTimerHourController();
		//newProjectController = dashboardController.getNewProjectController();
		//hourListController = dashboardController.getDashboardHourListController();
		//projectListController = dashboardController.getDashboardProjectListController();
		setBounds(100, 100, 1850, 1080); // x, y, width, height
		frame = new JFrame();
		frame.setBounds(100, 100, 1850, 1080);
		frame.getContentPane().setLayout(null);
		setResizable(false);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1850, 1060);
		panel_1.setLayout(null);
		setContentPane(panel_1);
		JPanel menuPanel = new JPanel();
		menuPanel.setName("navMainPane");
		menuPanel.setBounds(0, 0, 346, 1060);
		menuPanel.setBackground(new Color(31,32,33));
		panel_1.add(menuPanel);
		menuPanel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setName("btnPaneNavigation");
		panel_2.setBackground(new Color(31,32,33));
		panel_2.setBounds(10, 100, 335, 66);
		menuPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Navigation");
		lblNewLabel.setName("navLabelNavigation");
		lblNewLabel.setBounds(105, 11, 110, 22);
		lblNewLabel.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel.setForeground(Color.WHITE);
		panel_2.add(lblNewLabel);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setName("btnPaneDashboard");
		panel_2_1.setBackground(new Color(31,32,33));
		panel_2_1.setBounds(10, 177, 335, 66);
		menuPanel.add(panel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Dashboard");
		lblNewLabel_1.setName("navLabelDashboard");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(16.0f));
		panel_2_1.add(lblNewLabel_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setName("btnPaneProjects");
		panel_2_2.setBackground(new Color(31,32,33));
		panel_2_2.setBounds(10, 254, 335, 66);
		menuPanel.add(panel_2_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Projekte\r\n");
		lblNewLabel_1_1.setName("navLabelProjects");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(16.0f));
		panel_2_2.add(lblNewLabel_1_1);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setName("btnPaneSessions");
		panel_2_3.setForeground(Color.WHITE);
		panel_2_3.setBackground(new Color(31,32,33));
		panel_2_3.setBounds(10, 331, 335, 66);
		menuPanel.add(panel_2_3);
		

		JPanel panel_2_3_1 = new JPanel();
		panel_2_3_1.setName("btnPaneSettings");
		panel_2_3_1.setBackground(new Color(31,32,33));
		panel_2_3_1.setBounds(10, 408, 335, 66);
		menuPanel.add(panel_2_3_1);
		panel_2_3_1.setLayout(null);
		

		JPanel panel_2_3_2 = new JPanel();
		panel_2_3_2.setName("btnPaneAccount");
		panel_2_3_2.setBackground(new Color(31,32,33));
		panel_2_3_2.setBounds(10, 485, 335, 66);
		menuPanel.add(panel_2_3_2);
		
		JPanel panel_2_3_3 = new JPanel();
		panel_2_3_3.setName("btnPaneLogout");
		panel_2_3_3.setBackground(new Color(31,32,33));
		panel_2_3_3.setBounds(10, 562, 335, 66);
		menuPanel.add(panel_2_3_3);

		JLabel lblQualitytime = new JLabel("qualitytime\r\n");
		lblQualitytime.setForeground(Color.WHITE);
		lblQualitytime.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblQualitytime.setBounds(10, 11, 83, 31);
		menuPanel.add(lblQualitytime);
		
		contentPanel = new JPanel();
		contentPanel.setName("dashboardMainPane");
		contentPanel.setBounds(345, 0, 1490, 1041);
		contentPanel.setBackground(new Color(47,48,52));
		panel_1.add(contentPanel);
		contentPanel.setLayout(null);
		
		//projectView_1= 
		dashboardView_1 = dashboardController.getDashboardView();
		System.out.println(dashboardView_1);
		System.out.println(dashboardView_1.getDashbPanel());
		//System.out.println(dashboardView_1.getDashbPanel());
		//System.out.println(dashboardView.getDashbPanel());
		contentPanel.add(dashboardView_1.getDashbPanel());
		//contentPanel.add(dashboardView.getDashbPanel());
		
		setProjectView_1(projectController.getProjectView());
		setAccountView_1(accountController.getAccountView());
		
		
		JPanel btnPaneMenuDashboard = new JPanel();
		btnPaneMenuDashboard.setName("btnPaneDashboard");
		btnPaneMenuDashboard.setBackground(new Color(31,32,33));
		btnPaneMenuDashboard.setBounds(10, 177, 335, 66);
		menuPanel.add(btnPaneMenuDashboard);
		btnPaneMenuDashboard.setLayout(null);
		
		JButton btnMenuDashboard = new JButton("Dashboard\r\n");
		btnMenuDashboard.setForeground(Color.WHITE);
		btnMenuDashboard.setBounds(136, 0, 89, 23);
		btnPaneMenuDashboard.add(btnMenuDashboard);
		btnMenuDashboard.setOpaque(false);
		btnMenuDashboard.addActionListener(appMainController);
		btnMenuDashboard.setActionCommand(StaticActions.ACTION_MENU_DASHBOARD);
		btnMenuDashboard.setContentAreaFilled(false);
		btnMenuDashboard.setBorderPainted(false);
		btnMenuDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnMenuDashboard.setForeground(Color.ORANGE);
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnMenuDashboard.setForeground(Color.WHITE);
		    }
		});
		
		JPanel btnPaneMenuProjects = new JPanel();
		btnPaneMenuProjects.setName("btnPaneProjects");
		btnPaneMenuProjects.setBackground(new Color(31,32,33));
		btnPaneMenuProjects.setBounds(10, 254, 335, 66);
		menuPanel.add(btnPaneMenuProjects);
		btnPaneMenuProjects.setLayout(null);

		
		JButton btnMenuProjects = new JButton("Projekte");
		btnMenuProjects.setForeground(Color.WHITE);
		btnMenuProjects.setBackground(Color.BLACK);
		btnMenuProjects.setBounds(141, 0, 89, 23);
		btnPaneMenuProjects.add(btnMenuProjects);
		btnMenuProjects.setHorizontalAlignment(SwingConstants.LEFT); 
		btnMenuProjects.setOpaque(false);
		btnMenuProjects.setContentAreaFilled(false);
		btnMenuProjects.setBorderPainted(false);
		btnMenuProjects.addActionListener(appMainController);
		btnMenuProjects.setActionCommand(StaticActions.ACTION_MENU_PROJECTS);
		btnMenuProjects.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnMenuProjects.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnMenuProjects.setForeground(Color.WHITE);
		    }
		});
		
		
		
		JPanel btnPaneMenuSessions = new JPanel();
		btnPaneMenuSessions.setName("btnPaneSessions");
		btnPaneMenuSessions.setBackground(new Color(31,32,33));
		btnPaneMenuSessions.setBounds(10, 331, 335, 66);
		menuPanel.add(btnPaneMenuSessions);
		btnPaneMenuSessions.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Sitzungen");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBounds(141, 0, 89, 23);
		btnPaneMenuSessions.add(btnNewButton_2);
		btnNewButton_2.setOpaque(false);
		btnNewButton_2.setContentAreaFilled(false);
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setHorizontalAlignment(SwingConstants.LEFT); 
		btnNewButton_2.addActionListener(appMainController);
		btnNewButton_2.setActionCommand(StaticActions.ACTION_MENU_SESSIONS);
		btnNewButton_2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnNewButton_2.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnNewButton_2.setForeground(Color.WHITE);
		    }
		});
		
		
		JLabel lblEinstellungen = new JLabel("Einstellungen");
		lblEinstellungen.setName("navLabelNavigation");
		lblEinstellungen.setForeground(Color.WHITE);
		lblEinstellungen.setFont(null);
		lblEinstellungen.setBounds(111, 11, 110, 22);
		panel_2_3_1.add(lblEinstellungen);
		
		JButton btnMenuAccount = new JButton("Account");
		btnMenuAccount.setForeground(Color.WHITE);
		btnMenuAccount.setBounds(141, 1, 184, 23);
		panel_2_3_2.add(btnMenuAccount);
		btnMenuAccount.setOpaque(false);
		btnMenuAccount.setContentAreaFilled(false);
		btnMenuAccount.setBorderPainted(false);
		btnMenuAccount.setHorizontalAlignment(SwingConstants.LEFT); 
		btnMenuAccount.addActionListener(appMainController);
		btnMenuAccount.setActionCommand(StaticActions.ACTION_MENU_ACCOUNT);
		btnMenuAccount.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnMenuAccount.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnMenuAccount.setForeground(Color.WHITE);
		    }
		});

		JButton btnMenuLogout = new JButton("Logout\r\n");
		btnMenuLogout.setForeground(Color.WHITE);
		btnMenuLogout.setBounds(141, 1, 89, 23);
		panel_2_3_3.add(btnMenuLogout);
		btnMenuLogout.setOpaque(false);
		btnMenuLogout.setContentAreaFilled(false);
		btnMenuLogout.setBorderPainted(false);
		btnMenuLogout.setHorizontalAlignment(SwingConstants.LEFT); 
		btnMenuLogout.addActionListener(appMainController);
		btnMenuLogout.setActionCommand(StaticActions.ACTION_MENU_LOGOUT);
		btnMenuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnMenuLogout.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnMenuLogout.setForeground(Color.WHITE);
		    }
		});
	}
	@Override
	public void update(Observable o, Object arg) {
		
	}
/*
	public DashboardHourListController getHourListController() {
		return hourListController;
	}

	public void setHourListController(DashboardHourListController hourListController) {
		this.hourListController = hourListController;
	}

	public TimerHourController getTimerHourController() {
		return timerHourController;
	}

	public void setTimerHourController(TimerHourController timerHourController) {
		this.timerHourController = timerHourController;
	}

	public DashboardProjectListController getProjectListController() {
		return projectListController;
	}

	public void setProjectListController(DashboardProjectListController projectListController) {
		this.projectListController = projectListController;
	}	
	public DashboardView getDashboardView() {
		return dashboardView;
	}
	
	public void setDashboardView(DashboardView dashboardView) {
		this.dashboardView = dashboardView;
	}
	public NewProjectController getNewProjectController() {
		return newProjectController;
	}
	public void setNewProjectController(NewProjectController newProjectController) {
		this.newProjectController = newProjectController;
	}	
	public AccountView getAccountView() {
		return accountView;
	}
	public void setAccountView(AccountView accountView) {
		this.accountView = accountView;
	}
*/
	public DashboardController getDashboardController() {
		return dashboardController;
	}

	public void setDashboardController(DashboardController dashboardController) {
		this.dashboardController = dashboardController;
	}


	
	public JFrame getFrame() {
		return frame;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public AccountController getAccountController() {
		return accountController;
	}
	public void setAccountController(AccountController accountController) {
		this.accountController = accountController;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}
	public DashboardView getDashboardView_1() {
		return dashboardView_1;
	}
	public void setDashboardView_1(DashboardView dashboardView_1) {
		this.dashboardView_1 = dashboardView_1;
	}
	public AccountView getAccountView_1() {
		return accountView_1;
	}
	public void setAccountView_1(AccountView accountView_1) {
		this.accountView_1 = accountView_1;
	}
	public ProjectView getProjectView_1() {
		return projectView_1;
	}
	public void setProjectView_1(ProjectView projectView_1) {
		this.projectView_1 = projectView_1;
	}
}
