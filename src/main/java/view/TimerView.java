package main.java.view;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import main.java.controller.TimerHourController;
import main.java.model.StaticActions;
import main.java.model.TimerModel;

import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;

@SuppressWarnings("deprecation")
public class TimerView extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel; // Container

	private JComboBox comboBox = new JComboBox(); // ComboBox for project-list
	private JLabel durationLabel = new JLabel("00:00:00");
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	private JTextField textFieldComment;
	private JTextField textPauseDuration;
	private JTextField hiddenTextFieldProjectID;
	private JLabel lblErrorMessage;
	private boolean errorVisible;

	
	/**
	 * Create Frame
	 */
	public TimerView(TimerHourController timerHourController) {	
		setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TimerView.class.getResource("/main/resources/img/icons/qtproject_placeholder.gif")));
		setTitle("Quality Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 354, 600); // x, y, width, height
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // top, left, bottom, right
		contentPanel.setBounds(0, 0, 1850, 1080);
		setContentPane(contentPanel);
		contentPanel.setBackground(new Color(31,32,33));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		
		JPanel projectPanel = new JPanel();
		contentPanel.add(projectPanel);
		projectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		projectPanel.setBackground(new Color(31,32,33));
		
		JLabel lblProject = new JLabel("Projekt:");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		projectPanel.add(lblProject);
		
		// Project List
		comboBox.setPreferredSize(new Dimension(200, 20));
		lblProject.setLabelFor(comboBox);
		comboBox.setAlignmentX(0.0f);
		comboBox.addActionListener(timerHourController);
		comboBox.setActionCommand(StaticActions.ACTION_SET_PROJECT);
		projectPanel.add(comboBox);
		
		JButton btnLoadProjects = new JButton("\u21BB");
		btnLoadProjects.addActionListener(timerHourController);
		btnLoadProjects.setActionCommand(StaticActions.ACTION_LOAD_PROJECTS);
		projectPanel.add(btnLoadProjects);
		
		hiddenTextFieldProjectID = new JTextField();
		hiddenTextFieldProjectID.setFont(new Font("Tahoma", Font.PLAIN, 5));
		hiddenTextFieldProjectID.setHorizontalAlignment(SwingConstants.RIGHT);
		hiddenTextFieldProjectID.setEnabled(false);
		hiddenTextFieldProjectID.setEditable(false);
		hiddenTextFieldProjectID.setVisible(false);
		projectPanel.add(hiddenTextFieldProjectID);
		
		JPanel durationPanel = new JPanel();
		durationPanel.setBackground(new Color(31,32,33));
		contentPanel.add(durationPanel);
		
		// Timer Label
		JLabel timerLabel = new JLabel("Dauer: ");
		durationPanel.add(timerLabel);
		timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		durationPanel.add(durationLabel);
		
		// Duration Label
		durationLabel.setBounds(55, 5, 258, 83);
		durationLabel.setFont(new Font("Adam", Font.PLAIN, 78));
		durationLabel.setForeground(Color.WHITE);
		
		// Pane for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(31,32,33));
		contentPanel.add(buttonPanel);
		
		// Button start
		JButton btnStart = new JButton("");
		btnStart.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/play.gif")));
		btnStart.addActionListener(timerHourController);
		btnStart.setActionCommand(StaticActions.ACTION_TIMER_START); // Defined in Class StaticActions
		buttonPanel.add(btnStart);
		
		// Button pause
		JButton btnPause = new JButton("");
		btnPause.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/pause.gif")));
		btnPause.addActionListener(timerHourController);
		btnPause.setActionCommand(StaticActions.ACTION_TIMER_PAUSE);
		buttonPanel.add(btnPause);
		
		// Button stop
		JButton btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/stop.gif")));
		btnStop.addActionListener(timerHourController);
		btnStop.setActionCommand(StaticActions.ACTION_TIMER_STOP);
		buttonPanel.add(btnStop);
		
		JPanel manualEntryPanel = new JPanel();
		manualEntryPanel.setBackground(new Color(31,32,33));
		contentPanel.add(manualEntryPanel);
		
		JLabel lblFrom = new JLabel("Von:");
		manualEntryPanel.add(lblFrom);
		
		txtStartTime = new JTextField();
		txtStartTime.setEnabled(true);
		lblFrom.setLabelFor(txtStartTime);
		txtStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartTime.setText("");
		txtStartTime.getDocument().addDocumentListener(timerHourController);
		manualEntryPanel.add(txtStartTime);
		txtStartTime.setColumns(5);
		
		JLabel lblTo = new JLabel("Bis:");
		manualEntryPanel.add(lblTo);
		
		txtEndTime = new JTextField();
		txtEndTime.setEnabled(true);
		lblTo.setLabelFor(txtEndTime);
		txtEndTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndTime.setText("");
		txtEndTime.getDocument().addDocumentListener(timerHourController);
		manualEntryPanel.add(txtEndTime);
		txtEndTime.setColumns(5);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(31,32,33));
		contentPanel.add(panel);
		
		JLabel lblComment = new JLabel("Kommentar:");
		panel.add(lblComment);
		lblComment.setHorizontalAlignment(SwingConstants.CENTER);
		lblComment.setLabelFor(textFieldComment);
		
		textFieldComment = new JTextField();
		panel.add(textFieldComment);
		textFieldComment.setAlignmentX(Component.LEFT_ALIGNMENT);
		textFieldComment.setPreferredSize(new Dimension(300,20));
		textFieldComment.setToolTipText("");
		textFieldComment.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldComment.setColumns(25);
		
		JLabel lblPauseDuration = new JLabel("Pause:");
		manualEntryPanel.add(lblPauseDuration);
		
		textPauseDuration = new JTextField();
		textPauseDuration.setEnabled(true);
		lblPauseDuration.setLabelFor(textPauseDuration);
		textPauseDuration.setHorizontalAlignment(SwingConstants.CENTER);
		textPauseDuration.getDocument().addDocumentListener(timerHourController);
		manualEntryPanel.add(textPauseDuration);
		textPauseDuration.setColumns(5);
		
		JPanel confirmButtonPanel = new JPanel();
		confirmButtonPanel.setBackground(new Color(31,32,33));
		FlowLayout flowLayout = (FlowLayout) confirmButtonPanel.getLayout();
		contentPanel.add(confirmButtonPanel);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(timerHourController);
		btnReset.setActionCommand(StaticActions.ACTION_TIMER_RESET);
		confirmButtonPanel.add(btnReset);
		
		JButton btnSave = new JButton("Sichern");
		btnSave.addActionListener(timerHourController);
		btnSave.setActionCommand(StaticActions.ACTION_TIMER_SAVE);
		confirmButtonPanel.add(btnSave);
		
		
		JPanel errorPanel = new JPanel();
		errorPanel.setBackground(new Color(31,32,33));
		FlowLayout flowLayout_1 = (FlowLayout) errorPanel.getLayout();
		contentPanel.add(errorPanel);
		
		lblErrorMessage = new JLabel("Error Message");
		lblErrorMessage.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblErrorMessage.setForeground(new Color(255, 140, 0));
		lblErrorMessage.setVisible(false);
		errorPanel.add(lblErrorMessage);
		
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	public JLabel getDurationLabel() {
		return durationLabel;
	}

	public JTextField getTxtStartTime() {
		return txtStartTime;
	}

	public JTextField getTxtEndTime() {
		return txtEndTime;
	}

	public JTextField getTextPauseDuration() {
		return textPauseDuration;
	}

	public JTextField getTextFieldComment() {
		return textFieldComment;
	}
	
	public JComboBox getComboBox() {
		return comboBox;
	}
	
	public JTextField getHiddenTextFieldProjectID() {
		return hiddenTextFieldProjectID;
	}
		
	public JLabel getLblErrorMessage() {
		return lblErrorMessage;
	}
	
	public boolean isErrorVisible() {
		return errorVisible;
	}
	
	public void setErrorVisible(boolean errorVisible) {
		this.errorVisible = errorVisible;
	}
	
	public void showErrorMessage(String message, long duration) {
		lblErrorMessage.setText(message);		
		if (!isErrorVisible()) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					lblErrorMessage.setVisible(false);
					setErrorVisible(false);
				}	
			};			
			timer.schedule(task, duration);
		}
		lblErrorMessage.setVisible(true);
	}
	

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof TimerModel) {
			// update timer-stopwatch
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
}
