package main.java.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.Observable;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.java.controller.AccountController;
import main.java.controller.AppMainController;
import main.java.controller.DashboardController;
import main.java.controller.LayoutManager;
import main.java.controller.ProjectController;
import main.java.controller.SessionController;
import main.java.model.StaticActions;
import java.awt.Component;
import java.awt.Point;

public class AppMainView extends WindowSuperclass implements IView {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel;
	private DashboardController dashboardController;
	private AccountController accountController;
	private ProjectController projectController;
	private SessionController sessionController;
	private DashboardView dashboardView;
	private AccountView accountView;
	private ProjectView projectView;
	private SessionView sessionView;

	boolean hasclicked1 = false;
	JLabel click1label = null;

	public void mouseClicked(MouseEvent me) {
		if (!hasclicked1) { // clicked first pic
			hasclicked1 = true;
			click1label = (JLabel) me.getSource();
			System.out.println(click1label);
		} else { // clicked second pic
			hasclicked1 = false;
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public AppMainView(AppMainController appMainController) {
		super();
		dashboardController = appMainController.getDashboardController();
		accountController = appMainController.getAccountController();
		projectController = appMainController.getProjectController();
		sessionController = appMainController.getSessionController();
		setBounds(100, 100, 1850, 1080); // x, y, width, height
		setLocationRelativeTo(null); // Center Frame
		setResizable(false);

		JPanel panel_1 = new JPanel();
		panel_1.setName("panel_1");
		panel_1.setBounds(0, 0, 1850, 1060);
		panel_1.setLayout(null);
		setContentPane(panel_1);

		JPanel menuPanel = new JPanel();
		menuPanel.setName("navMainPane");
		menuPanel.setBounds(0, 0, 346, 1060);
		menuPanel.setBackground(new Color(31, 32, 33));
		panel_1.add(menuPanel);
		menuPanel.setLayout(null);

		JPanel btnPaneMenuNavigation = new JPanel();
		btnPaneMenuNavigation.setName("btnPaneNavigation");
		btnPaneMenuNavigation.setBackground(new Color(31, 32, 33));
		btnPaneMenuNavigation.setBounds(10, 11, 335, 66);
		menuPanel.add(btnPaneMenuNavigation);
		btnPaneMenuNavigation.setLayout(null);

		JLabel lblMenuLabel = new JLabel("Navigation");
		lblMenuLabel.setBounds(108, 11, 217, 44);
		lblMenuLabel.setName("navLabelNavigation");
		lblMenuLabel.setFont(dinNeuzeitGrotesk_regular.deriveFont(20.0f));
		lblMenuLabel.setForeground(Color.WHITE);
		btnPaneMenuNavigation.add(lblMenuLabel);

		JPanel btnPaneMenuSettings = new JPanel();
		btnPaneMenuSettings.setName("btnPaneSettings");
		btnPaneMenuSettings.setBackground(new Color(31, 32, 33));
		btnPaneMenuSettings.setBounds(10, 319, 335, 66);
		menuPanel.add(btnPaneMenuSettings);
		btnPaneMenuSettings.setLayout(null);

		JPanel btnPaneMenuAccount = new JPanel();
		btnPaneMenuAccount.setName("btnPaneAccount");
		btnPaneMenuAccount.setBackground(new Color(31, 32, 33));
		btnPaneMenuAccount.setBounds(10, 396, 335, 66);
		menuPanel.add(btnPaneMenuAccount);

		JPanel btnPaneMenuLogout = new JPanel();
		btnPaneMenuLogout.setName("btnPaneLogout");
		btnPaneMenuLogout.setBackground(new Color(31, 32, 33));
		btnPaneMenuLogout.setBounds(10, 473, 335, 66);
		menuPanel.add(btnPaneMenuLogout);

		contentPanel = new JPanel();
		contentPanel.setName("dashboardMainPane");
		contentPanel.setBounds(345, 0, 1490, 1041);
		contentPanel.setBackground(new Color(47, 48, 52));
		panel_1.add(contentPanel);
		contentPanel.setLayout(null);

		dashboardView = dashboardController.getDashboardView();

		contentPanel.add(dashboardView.getDashbPanel());
		setSessionView(sessionController.getSessionView());
		setProjectView(projectController.getProjectView());
		setAccountView(accountController.getAccountView());

		JPanel btnPaneMenuDashboard = new JPanel();
		btnPaneMenuDashboard.setName("btnPaneMenuDashboard");
		btnPaneMenuDashboard.setBackground(new Color(31, 32, 33));
		btnPaneMenuDashboard.setBounds(10, 88, 335, 66);
		menuPanel.add(btnPaneMenuDashboard);
		btnPaneMenuDashboard.setLayout(null);

		JButton btnMenuDashboard = new JButton("Dashboard");
		btnMenuDashboard.setIcon(LayoutManager.getImageIcon(LayoutManager.ICON_DASHBOARD, 20, 20));
		btnMenuDashboard.setName("btnMenuDashboard");
		btnMenuDashboard.setHorizontalAlignment(SwingConstants.LEFT);
		btnMenuDashboard.setForeground(Color.WHITE);
		btnMenuDashboard.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		btnMenuDashboard.setBounds(130, 11, 195, 44);
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
		btnPaneMenuProjects.setBackground(new Color(31, 32, 33));
		btnPaneMenuProjects.setBounds(10, 165, 335, 66);
		menuPanel.add(btnPaneMenuProjects);
		btnPaneMenuProjects.setLayout(null);

		JButton btnMenuProjects = new JButton("Projekte");
		btnMenuProjects.setIcon(LayoutManager.getImageIcon(LayoutManager.ICON_PROJECTS, 20, 20));
		btnMenuProjects.setName("btnMenuProjects");
		btnMenuProjects.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		btnMenuProjects.setForeground(Color.WHITE);
		btnMenuProjects.setBackground(Color.BLACK);
		btnMenuProjects.setBounds(130, 11, 195, 44);

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
		btnPaneMenuSessions.setBackground(new Color(31, 32, 33));
		btnPaneMenuSessions.setBounds(10, 242, 335, 66);
		menuPanel.add(btnPaneMenuSessions);

		JButton btnMenuSessions = new JButton("Sitzungen");
		btnMenuSessions.setIcon(LayoutManager.getImageIcon(LayoutManager.ICON_SESSIONS, 20, 20));
		btnMenuSessions.setName("btnMenuSessions");
		btnMenuSessions.setBounds(130, 11, 195, 44);
		btnMenuSessions.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		btnMenuSessions.setHorizontalAlignment(SwingConstants.LEFT);
		btnPaneMenuSessions.setLayout(null);
		btnMenuSessions.setForeground(Color.WHITE);
		btnPaneMenuSessions.add(btnMenuSessions);
		btnMenuSessions.setOpaque(false);
		btnMenuSessions.setContentAreaFilled(false);
		btnMenuSessions.setBorderPainted(false);
		btnMenuSessions.addActionListener(appMainController);
		btnMenuSessions.setActionCommand(StaticActions.ACTION_MENU_SESSIONS);

		btnMenuSessions.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				btnMenuSessions.setForeground(Color.ORANGE);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				btnMenuSessions.setForeground(Color.WHITE);
			}
		});

		JLabel lblEinstellungen = new JLabel("Einstellungen");
		lblEinstellungen.setHorizontalAlignment(SwingConstants.LEFT);
		lblEinstellungen.setName("lblEinstellungen");
		lblEinstellungen.setForeground(Color.WHITE);
		lblEinstellungen.setFont(dinNeuzeitGrotesk_regular.deriveFont(20.0f));
		lblEinstellungen.setBounds(108, 11, 217, 44);
		btnPaneMenuSettings.add(lblEinstellungen);
		btnPaneMenuAccount.setLayout(null);

		JButton btnMenuAccount = new JButton("Account");
		btnMenuAccount.setIcon(LayoutManager.getImageIcon(LayoutManager.ICON_ACCOUNT, 20, 20));
		btnMenuAccount.setForeground(Color.WHITE);
		btnMenuAccount.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		btnMenuAccount.setBounds(130, 11, 195, 44);
		btnPaneMenuAccount.add(btnMenuAccount);
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
		btnPaneMenuLogout.setLayout(null);

		JButton btnMenuLogout = new JButton("Logout");
		btnMenuLogout.setBorderPainted(true);
		btnMenuLogout.setIcon(LayoutManager.getImageIcon(LayoutManager.ICON_LOGOUT, 20, 20));
		btnMenuLogout.setName("btnMenuLogout");
		btnMenuLogout.setFont(dinNeuzeitGrotesk_regular.deriveFont(18.0f));
		btnMenuLogout.setForeground(Color.WHITE);
		btnMenuLogout.setBounds(130, 11, 195, 44);
		btnPaneMenuLogout.add(btnMenuLogout);
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

	public DashboardController getDashboardController() {
		return dashboardController;
	}

	public void setDashboardController(DashboardController dashboardController) {
		this.dashboardController = dashboardController;
	}

//	public JFrame getFrame() {
//		return frame;
//	}

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

	public DashboardView getDashboardView() {
		return dashboardView;
	}

	public void setDashboardView(DashboardView dashboardView_1) {
		this.dashboardView = dashboardView_1;
	}

	public AccountView getAccountView() {
		return accountView;
	}

	public void setAccountView(AccountView accountView_1) {
		this.accountView = accountView_1;
	}

	public ProjectView getProjectView() {
		return projectView;
	}

	public void setProjectView(ProjectView projectView) {
		this.projectView = projectView;
	}

	public SessionView getSessionView() {
		return sessionView;
	}

	public void setSessionView(SessionView sessionView) {
		this.sessionView = sessionView;
	}
	
	public void setFrameToLeftUpperCorner(){
		setLocation(0, 0);
	}
}
