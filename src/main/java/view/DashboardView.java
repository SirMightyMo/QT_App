package main.java.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.java.controller.DashboardController;
import main.java.controller.TimerHourController;
import main.java.model.StaticActions;
import main.java.model.TimerModel;
import main.java.view.TimerView;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;

public class DashboardView extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JComboBox comboBox = new JComboBox(); // ComboBox for project-list
	
	
	
	private JLabel lblErrorMessage;
	private JLabel durationLabel = new JLabel("00:00:00");
	boolean errorVisible;
	private TimerHourController timerHourController;
	private TimerView timerView;
	

	
	public DashboardView(DashboardController dashboardController) {
		setBounds(100, 100, 1850, 1080); // x, y, width, height
		timerHourController = new TimerHourController();
		frame = new JFrame();
		frame.setBounds(100, 100, 1850, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		setResizable(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1850, 1060);
		//frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		setContentPane(panel_1);
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 346, 1060);
		menuPanel.setBackground(new Color(31,32,33));
		panel_1.add(menuPanel);
		menuPanel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(31,32,33));
		panel_2.setBounds(10, 100, 335, 66);
		menuPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Navigation");
		lblNewLabel.setBounds(105, 11, 110, 22);
		lblNewLabel.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.WHITE);
		panel_2.add(lblNewLabel);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setBackground(new Color(31,32,33));
		panel_2_1.setBounds(10, 177, 335, 66);
		menuPanel.add(panel_2_1);
		
		JLabel lblNewLabel_1 = new JLabel("Dashboard");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 16));
		panel_2_1.add(lblNewLabel_1);
		
		JPanel panel_2_2 = new JPanel();
		panel_2_2.setBackground(new Color(31,32,33));
		panel_2_2.setBounds(10, 254, 335, 66);
		menuPanel.add(panel_2_2);
		
		JLabel lblNewLabel_1_1 = new JLabel("Projekte\r\n");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 16));
		panel_2_2.add(lblNewLabel_1_1);
		
		JPanel panel_2_3 = new JPanel();
		panel_2_3.setBackground(new Color(31,32,33));
		panel_2_3.setBounds(10, 331, 335, 66);
		menuPanel.add(panel_2_3);
		
		JLabel lblNewLabel_1_2 = new JLabel("Sitzungen\r\n");
		lblNewLabel_1_2.setForeground(Color.WHITE);
		lblNewLabel_1_2.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 16));
		panel_2_3.add(lblNewLabel_1_2);
		
		JPanel panel_2_3_1 = new JPanel();
		panel_2_3_1.setBackground(new Color(31,32,33));
		panel_2_3_1.setBounds(10, 408, 335, 66);
		menuPanel.add(panel_2_3_1);
		panel_2_3_1.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Einstellungen\r\n");
		lblNewLabel_1_3.setBounds(105, 11, 138, 20);
		lblNewLabel_1_3.setForeground(Color.WHITE);
		lblNewLabel_1_3.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel_2_3_1.add(lblNewLabel_1_3);
		
		JPanel panel_2_3_2 = new JPanel();
		panel_2_3_2.setBackground(new Color(31,32,33));
		panel_2_3_2.setBounds(10, 485, 335, 66);
		menuPanel.add(panel_2_3_2);
		
		JLabel lblNewLabel_1_4 = new JLabel("Account");
		lblNewLabel_1_4.setForeground(Color.WHITE);
		lblNewLabel_1_4.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 16));
		panel_2_3_2.add(lblNewLabel_1_4);
		
		JPanel panel_2_3_3 = new JPanel();
		panel_2_3_3.setBackground(new Color(31,32,33));
		panel_2_3_3.setBounds(10, 562, 335, 66);
		menuPanel.add(panel_2_3_3);
		
		JLabel lblNewLabel_1_5 = new JLabel("Logout");
		lblNewLabel_1_5.setForeground(Color.WHITE);
		lblNewLabel_1_5.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 16));
		panel_2_3_3.add(lblNewLabel_1_5);
		
		JLabel lblQualitytime = new JLabel("qualitytime\r\n");
		lblQualitytime.setForeground(Color.WHITE);
		lblQualitytime.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblQualitytime.setBounds(10, 11, 83, 31);
		menuPanel.add(lblQualitytime);
		
		JPanel dashbPanel = new JPanel();
		dashbPanel.setBounds(345, 0, 1490, 1060);
		dashbPanel.setBackground(new Color(47,48,52));
		panel_1.add(dashbPanel);
		dashbPanel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Dashboard\r\n");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(10, 60, 76, 24);
		dashbPanel.add(lblNewLabel_2);
		
		JPanel productPanel = new JPanel();
		productPanel.setBackground(new Color(31,32,33));
		productPanel.setBounds(10, 699, 355, 324);
		dashbPanel.add(productPanel);
		productPanel.setLayout(null);
		
		JLabel lblNewLabel_2_1_9 = new JLabel("Produktivit\u00E4t");
		lblNewLabel_2_1_9.setForeground(Color.WHITE);
		lblNewLabel_2_1_9.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_9.setBounds(10, 11, 102, 24);
		productPanel.add(lblNewLabel_2_1_9);
		
		JPanel timerPanel = new JPanel();
		timerPanel.setBackground(new Color(31,32,33));
		timerPanel.setBounds(10, 87, 354, 600);
		dashbPanel.add(timerPanel);
		timerPanel.setLayout(null);
		
		TimerView timerView_1 = timerHourController.getTimerView();
		//timerView_1.setBounds(0, 0, 354, 600);
		//timerPanel.add(timerView_1);
		timerView_1.setLayout(null);
		//durationLabel.setBounds(55, 5, 258, 83);
		//durationLabel.setFont(new Font("Adam", Font.PLAIN, 78));
		//durationLabel.setForeground(Color.WHITE);
		//timerPanel.add(durationLabel);
		
		timerPanel.add(timerView_1.getContentPanel());
		//System.out.println(timerPanel.add(timerView_1.getContentPanel()));
		//timerView_1.getContentPanel().setBounds(0, 0, 1850, 1080);
		//System.out.println(timerPanel.add(timerView_1.getContentPanel()));
		
		//System.out.println("got Content Panel from TimerView");
		//timerView_1.getContentPanel().setVisible(true);
		/*
		
		// Pane for buttons
		JPanel buttonPanel = new JPanel();
		timerPanel.add(buttonPanel);
		buttonPanel.setLayout(null);
		
		JLabel lblNewLabel_2_1 = new JLabel("Timer\r\n");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		timerPanel.add(lblNewLabel_2_1);
		
		JButton btnNewButton_1_1 = new JButton("Speichern");
		btnNewButton_1_1.setOpaque(false);
		btnNewButton_1_1.setForeground(Color.ORANGE);
		btnNewButton_1_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		btnNewButton_1_1.setContentAreaFilled(false);
		btnNewButton_1_1.setBorderPainted(false);
		timerPanel.add(btnNewButton_1_1);
		
		JLabel lblNewLabel_2_1_3 = new JLabel("Projekt\r\n");
		lblNewLabel_2_1_3.setForeground(Color.WHITE);
		lblNewLabel_2_1_3.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		timerPanel.add(lblNewLabel_2_1_3);
		
		JLabel lblNewLabel_2_1_4 = new JLabel("Start");
		lblNewLabel_2_1_4.setForeground(Color.WHITE);
		lblNewLabel_2_1_4.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		timerPanel.add(lblNewLabel_2_1_4);
		
		JLabel lblNewLabel_2_1_5 = new JLabel("Dauer");
		lblNewLabel_2_1_5.setForeground(Color.WHITE);
		lblNewLabel_2_1_5.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		timerPanel.add(lblNewLabel_2_1_5);
		
		JLabel lblNewLabel_2_1_6 = new JLabel("Pause");
		lblNewLabel_2_1_6.setForeground(Color.WHITE);
		lblNewLabel_2_1_6.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		timerPanel.add(lblNewLabel_2_1_6);
		
		JLabel lblNewLabel_2_1_7 = new JLabel("Ende");
		lblNewLabel_2_1_7.setForeground(Color.WHITE);
		lblNewLabel_2_1_7.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		timerPanel.add(lblNewLabel_2_1_7);
		
		JLabel lblNewLabel_2_1_8 = new JLabel("Kommentar");
		lblNewLabel_2_1_8.setForeground(Color.WHITE);
		lblNewLabel_2_1_8.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		timerPanel.add(lblNewLabel_2_1_8);
		
		// Button pause
		JButton btnPause = new JButton("");
		timerPanel.add(btnPause);
		btnPause.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/pause.gif")));
		//btnPause.addActionListener(timerHourController);
		btnPause.setActionCommand(StaticActions.ACTION_TIMER_PAUSE);
		btnPause.setOpaque(false);
		btnPause.setContentAreaFilled(false);
		btnPause.setBorderPainted(false);
		
		// Button stop
		JButton btnStop = new JButton("");
		timerPanel.add(btnStop);
		btnStop.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/stop.gif")));
		//btnStop.addActionListener(timerHourController);
		btnStop.setActionCommand(StaticActions.ACTION_TIMER_STOP);
		btnStop.setOpaque(false);
		btnStop.setContentAreaFilled(false);
		btnStop.setBorderPainted(false);
		
		// Button start
		JButton btnStart = new JButton("");
		timerPanel.add(btnStart);
		btnStart.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/play.gif")));
		//btnStart.addActionListener(timerHourController);
		btnStart.setActionCommand(StaticActions.ACTION_TIMER_START);
		btnStart.setOpaque(false);
		btnStart.setContentAreaFilled(false);
		btnStart.setBorderPainted(false);
		
		JLabel lblNewLabel_3 = new JLabel("stunden");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setFont(new Font("Adam", Font.PLAIN, 14));
		timerPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("minuten");
		lblNewLabel_3_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3_1.setFont(new Font("Adam", Font.PLAIN, 14));
		timerPanel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_3_2 = new JLabel("sekunden");
		lblNewLabel_3_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3_2.setFont(new Font("Adam", Font.PLAIN, 14));
		timerPanel.add(lblNewLabel_3_2);
		

		*/
		
		
		JPanel projectPanel = new JPanel();
		projectPanel.setLayout(null);
		projectPanel.setBackground(new Color(31,32,33));
		projectPanel.setBounds(373, 87, 811, 424);
		dashbPanel.add(projectPanel);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Aktuelle Projekte\r\n");
		lblNewLabel_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_1.setBounds(10, 11, 105, 24);
		projectPanel.add(lblNewLabel_2_1_1);
		
		JButton btnNewButton = new JButton("mehr anzeigen");
		btnNewButton.setForeground(Color.ORANGE);
		btnNewButton.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 14));
		btnNewButton.setBounds(697, 390, 104, 23);
		projectPanel.add(btnNewButton);
		btnNewButton.setOpaque(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(27,27,27));
		panel.setBounds(10, 38, 791, 32);
		projectPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2_1_1_1_1_3_2 = new JLabel("Name");
		lblNewLabel_2_1_1_1_1_3_2.setBounds(10, 5, 188, 22);
		lblNewLabel_2_1_1_1_1_3_2.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_3_2.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1_1_1_3_2);
		
		JLabel lblNewLabel_2_1_1_1_1_2_1 = new JLabel("Start");
		lblNewLabel_2_1_1_1_1_2_1.setBounds(208, 5, 98, 22);
		lblNewLabel_2_1_1_1_1_2_1.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_2_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1_1_1_2_1);
		
		JLabel lblNewLabel_2_1_1_1_1_3_1 = new JLabel("Ende");
		lblNewLabel_2_1_1_1_1_3_1.setBounds(316, 5, 169, 22);
		lblNewLabel_2_1_1_1_1_3_1.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_3_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1_1_1_3_1);
		
		JLabel lblNewLabel_2_1_1_1_1_3_3 = new JLabel("Status");
		lblNewLabel_2_1_1_1_1_3_3.setBounds(495, 5, 176, 22);
		lblNewLabel_2_1_1_1_1_3_3.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_3_3.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1_1_1_3_3);
		
		JLabel lblNewLabel_2_1_1_1_1_3_4 = new JLabel("Arbeitszeit");
		lblNewLabel_2_1_1_1_1_3_4.setBounds(681, 5, 100, 22);
		lblNewLabel_2_1_1_1_1_3_4.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_3_4.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel.add(lblNewLabel_2_1_1_1_1_3_4);
		
		JPanel panel_4_1_1 = new JPanel();
		panel_4_1_1.setBackground(new Color(35, 36, 38));
		panel_4_1_1.setBounds(10, 68, 791, 314);
		projectPanel.add(panel_4_1_1);
		
		JPanel sessionPanel = new JPanel();
		sessionPanel.setLayout(null);
		sessionPanel.setBackground(new Color(31,32,33));
		sessionPanel.setBounds(375, 522, 721, 501);
		dashbPanel.add(sessionPanel);
		
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Letzte Sitzungen\r\n");
		lblNewLabel_2_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_1_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_1_1.setBounds(10, 11, 102, 24);
		sessionPanel.add(lblNewLabel_2_1_1_1);
		
		JButton btnNewButton_1 = new JButton("mehr anzeigen");
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setForeground(Color.ORANGE);
		btnNewButton_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 14));
		btnNewButton_1.setContentAreaFilled(false);
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setBounds(607, 467, 104, 23);
		sessionPanel.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(27,27,27));
		panel_4.setBounds(10, 37, 701, 32);
		sessionPanel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Datum");
		lblNewLabel_2_1_1_1_1.setBounds(10, 5, 136, 22);
		lblNewLabel_2_1_1_1_1.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel_4.add(lblNewLabel_2_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_1 = new JLabel("Projekt");
		lblNewLabel_2_1_1_1_1_1.setBounds(156, 5, 186, 22);
		lblNewLabel_2_1_1_1_1_1.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel_4.add(lblNewLabel_2_1_1_1_1_1);
		
		JLabel lblNewLabel_2_1_1_1_1_2 = new JLabel("Start");
		lblNewLabel_2_1_1_1_1_2.setBounds(352, 5, 101, 22);
		lblNewLabel_2_1_1_1_1_2.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_2.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel_4.add(lblNewLabel_2_1_1_1_1_2);
		
		JLabel lblNewLabel_2_1_1_1_1_3 = new JLabel("Ende");
		lblNewLabel_2_1_1_1_1_3.setBounds(463, 5, 126, 22);
		lblNewLabel_2_1_1_1_1_3.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_3.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel_4.add(lblNewLabel_2_1_1_1_1_3);
		
		JLabel lblNewLabel_2_1_1_1_1_4 = new JLabel("Arbeitszeit");
		lblNewLabel_2_1_1_1_1_4.setBounds(599, 5, 92, 22);
		lblNewLabel_2_1_1_1_1_4.setForeground(Color.ORANGE);
		lblNewLabel_2_1_1_1_1_4.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		panel_4.add(lblNewLabel_2_1_1_1_1_4);
		
		JPanel panel_4_1 = new JPanel();
		panel_4_1.setBackground(new Color(35,36,38));
		panel_4_1.setBounds(10, 68, 701, 388);
		sessionPanel.add(panel_4_1);
		
		JPanel timelinePanel = new JPanel();
		timelinePanel.setLayout(null);
		timelinePanel.setBackground(new Color(31,32,33));
		timelinePanel.setBounds(1106, 522, 365, 245);
		dashbPanel.add(timelinePanel);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Timeline");
		lblNewLabel_2_1_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_2.setBounds(10, 11, 70, 24);
		timelinePanel.add(lblNewLabel_2_1_2);
		
		JPanel upcomingPanel = new JPanel();
		upcomingPanel.setLayout(null);
		upcomingPanel.setBackground(new Color(31,32,33));
		upcomingPanel.setBounds(1194, 87, 277, 424);
		dashbPanel.add(upcomingPanel);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("Anstehende\r\n");
		lblNewLabel_2_1_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_2_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_2_1.setBounds(10, 11, 75, 28);
		upcomingPanel.add(lblNewLabel_2_1_2_1);
		
		JLabel lblNewLabel_2_1_2_1_1 = new JLabel("Aktivit\u00E4ten");
		lblNewLabel_2_1_2_1_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_2_1_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_2_1_1.setBounds(10, 30, 75, 28);
		upcomingPanel.add(lblNewLabel_2_1_2_1_1);
		
		JPanel newprojectPanel = new JPanel();
		newprojectPanel.setLayout(null);
		newprojectPanel.setBackground(new Color(31,32,33));
		newprojectPanel.setBounds(1106, 778, 365, 245);
		dashbPanel.add(newprojectPanel);
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("Neues Projekt anlegen");
		lblNewLabel_2_1_2_2.setForeground(Color.WHITE);
		lblNewLabel_2_1_2_2.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_2_2.setBounds(10, 11, 135, 24);
		newprojectPanel.add(lblNewLabel_2_1_2_2);
		
		JButton btnNewButton_1_1_1 = new JButton("Speichern");
		btnNewButton_1_1_1.setOpaque(false);
		btnNewButton_1_1_1.setForeground(Color.ORANGE);
		btnNewButton_1_1_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		btnNewButton_1_1_1.setContentAreaFilled(false);
		btnNewButton_1_1_1.setBorderPainted(false);
		btnNewButton_1_1_1.setBounds(132, 195, 104, 39);
		newprojectPanel.add(btnNewButton_1_1_1);
		
		JLabel lblNewLabel_2_1_3_1 = new JLabel("Projektname\r\n");
		lblNewLabel_2_1_3_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_3_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_3_1.setBounds(10, 57, 108, 24);
		newprojectPanel.add(lblNewLabel_2_1_3_1);
		
		JLabel lblNewLabel_2_1_4_1 = new JLabel("Start");
		lblNewLabel_2_1_4_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_4_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_4_1.setBounds(10, 90, 108, 24);
		newprojectPanel.add(lblNewLabel_2_1_4_1);
		
		JLabel lblNewLabel_2_1_7_1 = new JLabel("Ende");
		lblNewLabel_2_1_7_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_7_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_7_1.setBounds(48, 90, 70, 24);
		newprojectPanel.add(lblNewLabel_2_1_7_1);
		
		JLabel lblNewLabel_2_1_8_1 = new JLabel("Kommentar");
		lblNewLabel_2_1_8_1.setForeground(Color.WHITE);
		lblNewLabel_2_1_8_1.setFont(new Font("DINNeuzeitGrotesk-BoldCond", Font.PLAIN, 18));
		lblNewLabel_2_1_8_1.setBounds(10, 125, 108, 24);
		newprojectPanel.add(lblNewLabel_2_1_8_1);
	}
	public JLabel getDurationLabel() {
		return durationLabel;
	}
	public JLabel getLblErrorMessage() {
		return lblErrorMessage;
	}

	public void setLblErrorMessage(JLabel lblErrorMessage) {
		this.lblErrorMessage = lblErrorMessage;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public void update(Observable o, Object arg) 
	{
		if(arg instanceof TimerModel) 
		{
			// update timer-Stop watch
			this.durationLabel.setText(((TimerModel) arg).timerToString(true));
			
			/*
			 * Update project list:
			 * ProjectList from TimerModel is an ArrayList of Objects containing ArrayLists of Objects (project data).
			 * The inner ArrayList contains the project id (index 0) and the project name (index 1).
			 * To only display the projects names, the program is iterating over the project data and adds the names to a new ArrayList 'projectNames'.
			 * This ArrayList is then converted to an Array and provides the needed information for the ComboBox (dropdown with selectable projects).
			 */
			if (!((TimerModel) arg).isTimerRunning() && !((TimerModel) arg).isProjectSet()) {
				ArrayList<String> projectNames = new ArrayList<>();
				((TimerModel) arg).getProjectList().forEach(project -> {
					projectNames.add(project.get(1).toString());
				});
				this.comboBox.setModel(new DefaultComboBoxModel(projectNames.toArray()));
				System.out.println("Projects loaded into TimerView.");
			}
		}
	}
	public TimerHourController getTimerHourController() {
		return timerHourController;
	}
	public void setTimerHourController(TimerHourController timerHourController) {
		this.timerHourController = timerHourController;
	}
	public TimerView getTimerView() {
		return timerView;
	}
	public void setTimerView(TimerView timerView) {
		this.timerView = timerView;
	}
}
