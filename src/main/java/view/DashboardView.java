package main.java.view;

import java.awt.Color;
import java.awt.Component;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.controller.DashboardController;
import main.java.controller.NewProjectController;
import main.java.controller.DashboardHourListController;
import main.java.controller.DashboardProjectListController;
import main.java.controller.TimerHourController;
import main.java.model.StaticActions;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class DashboardView implements IView{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel timerPanel;
	private JPanel dashbPanel;
	private TimerHourController timerHourController;
	private NewProjectController newProjectController;
	private DashboardHourListController hourListController;
	private DashboardProjectListController projectListController;
	

	
	public DashboardView(DashboardController dashboardController) {
		timerHourController = dashboardController.getTimerHourController();
		newProjectController = dashboardController.getNewProjectController();
		hourListController = dashboardController.getDashboardHourListController();
		projectListController = dashboardController.getDashboardProjectListController();
		/*
		//setBounds(100, 100, 1850, 1080); // x, y, width, height
		frame = new JFrame();
		frame.setBounds(100, 100, 1850, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//setResizable(false);
		*/
		
		dashbPanel = new JPanel();
		dashbPanel.setName("dashboardMainPane");
		dashbPanel.setBounds(0, 0, 1490, 1060);
		dashbPanel.setBackground(new Color(47,48,52));
		
		dashbPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Dashboard\r\n");
		lblNewLabel_2.setForeground(Color.WHITE);
		//lblNewLabel_2.setFont(dinNeuzeitGrotesk_regular.deriveFont(20.0f));
		lblNewLabel_2.setBounds(10, 60, 76, 24);
		dashbPanel.add(lblNewLabel_2);
		
		JPanel productPanel = new JPanel();
		productPanel.setName("dashboardProductivityPane");
		productPanel.setBackground(new Color(31,32,33));
		productPanel.setBounds(10, 699, 355, 324);
		dashbPanel.add(productPanel);
		productPanel.setLayout(null);
		
		JLabel lblNewLabel_2_1_9 = new JLabel("Produktivit\u00E4t");
		lblNewLabel_2_1_9.setForeground(Color.WHITE);
	//	lblNewLabel_2_1_9.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_9.setBounds(10, 11, 102, 24);
		productPanel.add(lblNewLabel_2_1_9);
		
		timerPanel = new JPanel();
		timerPanel.setName("dashboardTimerPane");
		timerPanel.setBackground(new Color(31,32,33));
		timerPanel.setBounds(10, 87, 354, 600);
		dashbPanel.add(timerPanel);
		TimerView timerView_1 = timerHourController.getTimerView();		
		timerPanel.add(timerView_1.getContentPanel());
		timerPanel.setLayout(new BoxLayout(timerPanel, BoxLayout.X_AXIS));


		JPanel projectPanel = new JPanel();
		projectPanel.setName("dashboardProjectsPane");
		projectPanel.setLayout(null);
		projectPanel.setBackground(new Color(31,32,33));
		projectPanel.setBounds(373, 87, 811, 424);
		
		dashbPanel.add(projectPanel);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Aktuelle Projekte\r\n");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		//lblNewLabel_2_1_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_1.setBounds(10, 11, 105, 24);
		projectPanel.add(lblNewLabel_2_1_1);
		
		JButton btnNewButton = new JButton("mehr anzeigen");
		btnNewButton.setForeground(Color.ORANGE);
		//btnNewButton.setFont(dinNeuzeitGrotesk_regular.deriveFont(14.0f));
		btnNewButton.setBounds(697, 390, 104, 23);
		projectPanel.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setLayout(new BoxLayout(panel_4_1_1, BoxLayout.X_AXIS));
		panel_4_1_1.setName("projectListPanel");
		panel_4_1_1.add(projectListController.getView().getScrollPane());
		panel_4_1_1.setBackground(new Color(35, 36, 38));
		panel_4_1_1.setBounds(10, 68, 791, 314);
		projectPanel.add(panel_4_1_1);
		
		
		JPanel sessionPanel = new JPanel();
		sessionPanel.setName("dashboardLastSessionsPane");
		sessionPanel.setLayout(null);
		sessionPanel.setBackground(new Color(31,32,33));
		sessionPanel.setBounds(375, 522, 721, 501);
		dashbPanel.add(sessionPanel);
		
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Letzte Sitzungen\r\n");
		lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
		//lblNewLabel_2_1_1_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_1_1.setBounds(10, 11, 102, 24);
		sessionPanel.add(lblNewLabel_2_1_1_1);
		
		JButton btnNewButton_1 = new JButton("mehr anzeigen");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setForeground(Color.ORANGE);
		//btnNewButton_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(14.0f));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(607, 467, 104, 23);
		sessionPanel.add(btnNewButton_1);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setName("hourListPanel");
		panel_4_1.setLayout(new BoxLayout(panel_4_1, BoxLayout.X_AXIS));
		panel_4_1.add(hourListController.getView().getScrollPane());
		panel_4_1.setBackground(new Color(35,36,38));
		panel_4_1.setBounds(10, 68, 701, 388);
		sessionPanel.add(panel_4_1);
		
		JPanel timelinePanel = new JPanel();
		timelinePanel.setName("dashboardTimelinePane");
		timelinePanel.setLayout(null);
		timelinePanel.setBackground(new Color(31,32,33));
		timelinePanel.setBounds(1106, 522, 365, 245);
		dashbPanel.add(timelinePanel);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Timeline");
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		//lblNewLabel_2_1_2.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_2.setBounds(10, 11, 70, 24);
		timelinePanel.add(lblNewLabel_2_1_2);
		
		JPanel upcomingPanel = new JPanel();
		upcomingPanel.setName("dashboardActivityPane");
		upcomingPanel.setLayout(null);
		upcomingPanel.setBackground(new Color(31,32,33));
		upcomingPanel.setBounds(1194, 87, 277, 424);
		dashbPanel.add(upcomingPanel);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("Anstehende\r\n");
		lblNewLabel_2_1_2_1.setForeground(Color.WHITE);
		//lblNewLabel_2_1_2_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_2_1.setBounds(10, 11, 75, 28);
		upcomingPanel.add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("Aktivit\u00E4ten");
		lblNewLabel_2_1_2_1_1.setForeground(Color.WHITE);
		//lblNewLabel_2_1_2_1_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_2_1_1.setBounds(10, 30, 75, 28);
		upcomingPanel.add(lblNewLabel_2_1_2_1_1);
		
		//HIERHIERHIERHIER
		
		
		JPanel newprojectPanel = new JPanel();
		newprojectPanel.setName("dashboardNewProjectPane");
		newprojectPanel.setLayout(null);
		newprojectPanel.setBackground(new Color(31,32,33));
		newprojectPanel.setBounds(1106, 778, 365, 245);
		dashbPanel.add(newprojectPanel);
		NewProjectView newProjectView = newProjectController.getNewProjectView();		
		newprojectPanel.add(newProjectView.getContentPanel());
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("Neues Projekt anlegen");
		lblNewLabel_2_1_2_2.setName("dashLabelNewProject");
		lblNewLabel_2_1_2_2.setForeground(Color.WHITE);
		//lblNewLabel_2_1_2_2.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_2_2.setBounds(10, 11, 135, 24);
		newprojectPanel.add(lblNewLabel_2_1_2_2);
		
		JButton btnNewButton_1_1_1 = new JButton("Speichern");
		btnNewButton_1_1_1.setOpaque(false);
		btnNewButton_1_1_1.setForeground(Color.ORANGE);
		//btnNewButton_1_1_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		btnNewButton_1_1_1.setContentAreaFilled(false);
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setBounds(132, 195, 104, 39);
		newprojectPanel.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_2_1_3_1 = new JLabel("Projektname\r\n");
		lblNewLabel_2_1_3_1.setForeground(Color.WHITE);
		//lblNewLabel_2_1_3_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_3_1.setBounds(10, 57, 108, 24);
		newprojectPanel.add(lblNewLabel_2_1_3_1);
		
		JLabel lblNewLabel_2_1_4_1 = new JLabel("Start");
		lblNewLabel_2_1_4_1.setForeground(Color.WHITE);
		//lblNewLabel_2_1_4_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_4_1.setBounds(10, 90, 108, 24);
		newprojectPanel.add(lblNewLabel_2_1_4_1);
		
		JLabel lblNewLabel_2_1_7_1 = new JLabel("Ende");
		lblNewLabel_2_1_7_1.setForeground(Color.WHITE);
		//lblNewLabel_2_1_7_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_7_1.setBounds(48, 90, 70, 24);
		newprojectPanel.add(lblNewLabel_2_1_7_1);
		
		JLabel lblNewLabel_2_1_8_1 = new JLabel("Kommentar");
		lblNewLabel_2_1_8_1.setForeground(Color.WHITE);
		//lblNewLabel_2_1_8_1.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel_2_1_8_1.setBounds(10, 125, 108, 24);
		newprojectPanel.add(lblNewLabel_2_1_8_1);
	}
		//setContentPane(panel_1);
		/*
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
		//lblNewLabel.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblNewLabel.setForeground(Color.WHITE);
		panel_2.add(lblNewLabel);
		
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
		btnNewButton_2.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnNewButton_2.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnNewButton_2.setForeground(Color.WHITE);
		    }
		});
		
		
		JPanel panel_2_3_1 = new JPanel();
		panel_2_3_1.setName("btnPaneSettings");
		panel_2_3_1.setBackground(new Color(31,32,33));
		panel_2_3_1.setBounds(10, 408, 335, 66);
		menuPanel.add(panel_2_3_1);
		panel_2_3_1.setLayout(null);
		
		JLabel lblEinstellungen = new JLabel("Einstellungen");
		lblEinstellungen.setName("navLabelNavigation");
		lblEinstellungen.setForeground(Color.WHITE);
		lblEinstellungen.setFont(null);
		lblEinstellungen.setBounds(111, 11, 110, 22);
		panel_2_3_1.add(lblEinstellungen);
		
		JPanel panel_2_3_2 = new JPanel();
		panel_2_3_2.setName("btnPaneAccount");
		panel_2_3_2.setBackground(new Color(31,32,33));
		panel_2_3_2.setBounds(10, 485, 335, 66);
		menuPanel.add(panel_2_3_2);
		panel_2_3_2.setLayout(null);
		
		JButton btnMenuAccount = new JButton("Accounteinstellungen\r\n");
		btnMenuAccount.setForeground(Color.WHITE);
		btnMenuAccount.setBounds(141, 1, 184, 23);
		panel_2_3_2.add(btnMenuAccount);
		btnMenuAccount.setOpaque(false);
		btnMenuAccount.setContentAreaFilled(false);
		btnMenuAccount.setBorderPainted(false);
		btnMenuAccount.setHorizontalAlignment(SwingConstants.LEFT); 
		btnMenuAccount.setActionCommand(StaticActions.ACTION_MENU_ACCOUNT);
		btnMenuAccount.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnMenuAccount.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnMenuAccount.setForeground(Color.WHITE);
		    }
		});

		
		JPanel panel_2_3_3 = new JPanel();
		panel_2_3_3.setName("btnPaneLogout");
		panel_2_3_3.setBackground(new Color(31,32,33));
		panel_2_3_3.setBounds(10, 562, 335, 66);
		menuPanel.add(panel_2_3_3);
		panel_2_3_3.setLayout(null);
		
		JButton btnMenuLogout = new JButton("Logout\r\n");
		btnMenuLogout.setForeground(Color.WHITE);
		btnMenuLogout.setBounds(141, 1, 89, 23);
		panel_2_3_3.add(btnMenuLogout);
		btnMenuLogout.setOpaque(false);
		btnMenuLogout.setContentAreaFilled(false);
		btnMenuLogout.setBorderPainted(false);
		btnMenuLogout.setHorizontalAlignment(SwingConstants.LEFT); 
		btnMenuLogout.setActionCommand(StaticActions.ACTION_MENU_LOGOUT);
		btnMenuLogout.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnMenuLogout.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnMenuLogout.setForeground(Color.WHITE);
		    }
		});
		
		JLabel lblQualitytime = new JLabel("qualitytime\r\n");
		lblQualitytime.setForeground(Color.WHITE);
		//lblQualitytime.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		lblQualitytime.setBounds(10, 11, 83, 31);
		menuPanel.add(lblQualitytime);
		*/
		
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public void update(Observable o, Object arg) {	
	}

	public JPanel getDashbPanel() {
		return dashbPanel;
	}

	public void setDashbPanel(JPanel dashbPanel) {
		this.dashbPanel = dashbPanel;
	}

	public JPanel getTimerPanel() {
		return timerPanel;
	}

	public void setTimerPanel(JPanel timerPanel) {
		this.timerPanel = timerPanel;
	}
}
