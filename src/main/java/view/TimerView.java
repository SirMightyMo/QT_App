package main.java.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.controller.DatePicker;
import main.java.controller.TimerHourController;
import main.java.model.StaticActions;
import main.java.model.TimerModel;
import javax.swing.SpringLayout;
import java.awt.ComponentOrientation;
import javax.swing.UIManager;

@SuppressWarnings("deprecation")
public class TimerView implements IView {
	private JPanel contentPanel; // Container

	private JComboBox<String> projectDropdown = new JComboBox<String>();
	private JComboBox<String> serviceDropdown = new JComboBox<String>();
	private JLabel durationLabel = new JLabel("00:00:00");
	private JButton btnStart;
	private JButton btnStop;
	private JButton btnPause;
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	private JTextField textFieldCommentTimerView;
	private JTextField textPauseDuration;
	private JTextField hiddenTextFieldProjectID;
	private JLabel lblErrorMessage;
	private JButton btnSaveTimerView;
	private JButton btnReset;
	private boolean errorVisible;
	private boolean buttonsHighlighted;
	private JTextField txtDateInput;
	private JButton btnDatePicker;

	/**
	 * Create Frame
	 */
	public TimerView(TimerHourController timerHourController) {
		//setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		contentPanel = new JPanel();
		contentPanel.setName("contentPanelTimerView");
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // top, left, bottom, right
		contentPanel.setBounds(0, 0, 354, 600);
		contentPanel.setBackground(new Color(31, 32, 33));
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);

		// Project
		JPanel projectPanel = new JPanel();
		projectPanel.setPreferredSize(new Dimension(10, 40));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, projectPanel, 5, SpringLayout.NORTH, contentPanel);
		projectPanel.setMinimumSize(new Dimension(10, 25));
		sl_contentPanel.putConstraint(SpringLayout.WEST, projectPanel, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, projectPanel, -5, SpringLayout.EAST, contentPanel);
		projectPanel.setName("projectPanel");
		contentPanel.add(projectPanel);
		projectPanel.setBackground(new Color(31, 32, 33));
		SpringLayout sl_projectPanel = new SpringLayout();
		sl_projectPanel.putConstraint(SpringLayout.NORTH, projectDropdown, 10, SpringLayout.NORTH, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.WEST, projectDropdown, 80, SpringLayout.WEST, projectPanel);
		projectPanel.setLayout(sl_projectPanel);

		JLabel lblProject = new JLabel("Projekt:");
		lblProject.setForeground(Color.WHITE);
		sl_projectPanel.putConstraint(SpringLayout.NORTH, lblProject, 13, SpringLayout.NORTH, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.WEST, lblProject, 5, SpringLayout.WEST, projectPanel);
		lblProject.setName("lblProject");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		projectPanel.add(lblProject);

		projectDropdown.setPreferredSize(new Dimension(200, 25));
		projectDropdown.setName("comboBoxProject");
		lblProject.setLabelFor(projectDropdown);
		projectDropdown.setAlignmentX(0.0f);
		projectDropdown.addActionListener(timerHourController);
		projectDropdown.setActionCommand(StaticActions.ACTION_SET_PROJECT);
		projectPanel.add(projectDropdown);

		JButton btnLoadProjects = new JButton("\u21BB");
		sl_projectPanel.putConstraint(SpringLayout.NORTH, btnLoadProjects, 5, SpringLayout.NORTH, projectPanel);
		sl_projectPanel.putConstraint(SpringLayout.WEST, btnLoadProjects, 5, SpringLayout.EAST, projectDropdown);
		btnLoadProjects.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
		btnLoadProjects.setName("btnLoadProjects");
		btnLoadProjects.addActionListener(timerHourController);
		btnLoadProjects.setActionCommand(StaticActions.ACTION_LOAD_PROJECTS);
		projectPanel.add(btnLoadProjects);

		// Service
		JPanel servicePanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, servicePanel, 5, SpringLayout.SOUTH, projectPanel);
		servicePanel.setPreferredSize(new Dimension(10, 40));
		sl_contentPanel.putConstraint(SpringLayout.WEST, servicePanel, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, servicePanel, -5, SpringLayout.EAST, contentPanel);
		servicePanel.setName("servicePanel");
		contentPanel.add(servicePanel);
		servicePanel.setBackground(new Color(31, 32, 33));
		SpringLayout sl_servicePanel = new SpringLayout();
		sl_servicePanel.putConstraint(SpringLayout.NORTH, serviceDropdown, 10, SpringLayout.NORTH, servicePanel);
		sl_servicePanel.putConstraint(SpringLayout.WEST, serviceDropdown, 80, SpringLayout.WEST, servicePanel);
		servicePanel.setLayout(sl_servicePanel);

		JLabel lblService = new JLabel("Leistung:");
		lblService.setForeground(Color.WHITE);
		sl_servicePanel.putConstraint(SpringLayout.NORTH, lblService, 13, SpringLayout.NORTH, servicePanel);
		sl_servicePanel.putConstraint(SpringLayout.WEST, lblService, 5, SpringLayout.WEST, servicePanel);
		lblService.setName("lblService");
		lblService.setHorizontalAlignment(SwingConstants.CENTER);
		servicePanel.add(lblService);

		serviceDropdown.setPreferredSize(new Dimension(200, 25));
		serviceDropdown.setName("serviceDropdown");
		lblService.setLabelFor(serviceDropdown);
		serviceDropdown.setAlignmentX(0.0f);
		serviceDropdown.addActionListener(timerHourController);
		serviceDropdown.setActionCommand(StaticActions.ACTION_SET_SERVICE);
		servicePanel.add(serviceDropdown);

		JButton btnLoadServices = new JButton("\u21BB");
		sl_servicePanel.putConstraint(SpringLayout.NORTH, btnLoadServices, 5, SpringLayout.NORTH, servicePanel);
		sl_servicePanel.putConstraint(SpringLayout.WEST, btnLoadServices, 5, SpringLayout.EAST, serviceDropdown);
		btnLoadServices.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
		btnLoadServices.setName("btnLoadServices");
		btnLoadServices.addActionListener(timerHourController);
		btnLoadServices.setActionCommand(StaticActions.ACTION_LOAD_SERVICES);
		servicePanel.add(btnLoadServices);
		
		JPanel durationPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, durationPanel, 20, SpringLayout.SOUTH, servicePanel);
		durationPanel.setPreferredSize(new Dimension(10, 80));
		sl_contentPanel.putConstraint(SpringLayout.EAST, durationPanel, -5, SpringLayout.EAST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, durationPanel, 5, SpringLayout.WEST, contentPanel);
		durationPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(durationPanel);

		// Timer Label
		lblProject.setName("timerLabel");
		SpringLayout sl_durationPanel = new SpringLayout();
		sl_durationPanel.putConstraint(SpringLayout.NORTH, durationLabel, 0, SpringLayout.NORTH, durationPanel);
		sl_durationPanel.putConstraint(SpringLayout.WEST, durationLabel, 0, SpringLayout.WEST, durationPanel);
		sl_durationPanel.putConstraint(SpringLayout.SOUTH, durationLabel, 0, SpringLayout.SOUTH, durationPanel);
		sl_durationPanel.putConstraint(SpringLayout.EAST, durationLabel, 0, SpringLayout.EAST, durationPanel);
		durationPanel.setLayout(sl_durationPanel);
		durationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		durationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		durationPanel.add(durationLabel);

		// Duration Label
		durationLabel.setName("durationLabel");
		durationLabel.setBounds(55, 5, 258, 83);
		durationLabel.setFont(new Font("Tahoma", Font.PLAIN, 72));
		durationLabel.setForeground(Color.WHITE);

		// Pane for buttons
		JPanel buttonPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, buttonPanel, 25, SpringLayout.SOUTH, durationPanel);
		buttonPanel.setPreferredSize(new Dimension(10, 50));
		sl_contentPanel.putConstraint(SpringLayout.WEST, buttonPanel, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, buttonPanel, -5, SpringLayout.EAST, contentPanel);
		buttonPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(buttonPanel);

		// Button start
		btnStart = new JButton("");
		btnStart.setName("btnStart");
		btnStart.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/play.gif")));
		btnStart.addActionListener(timerHourController);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnStart.setActionCommand(StaticActions.ACTION_TIMER_START); // Defined in Class StaticActions
		buttonPanel.add(btnStart);

		// Button pause
		btnPause = new JButton("");
		btnPause.setName("btnPause");
		btnPause.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/pause.gif")));
		btnPause.addActionListener(timerHourController);
		btnPause.setActionCommand(StaticActions.ACTION_TIMER_PAUSE);
		buttonPanel.add(btnPause);

		// Button stop
		btnStop = new JButton("");
		btnStop.setName("btnStop");
		btnStop.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/stop.gif")));
		btnStop.addActionListener(timerHourController);
		btnStop.setActionCommand(StaticActions.ACTION_TIMER_STOP);
		buttonPanel.add(btnStop);
		
		// Date Picker
		JPanel datePanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, datePanel, 5, SpringLayout.SOUTH, buttonPanel);
		datePanel.setPreferredSize(new Dimension(10, 40));
		sl_contentPanel.putConstraint(SpringLayout.WEST, datePanel, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, datePanel, -5, SpringLayout.EAST, contentPanel);
		datePanel.setBackground(new Color(31, 32, 33));
		datePanel.setName("datePanel");
		contentPanel.add(datePanel);
		SpringLayout sl_datePanel = new SpringLayout();
		datePanel.setLayout(sl_datePanel);
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setForeground(Color.WHITE);
		sl_datePanel.putConstraint(SpringLayout.NORTH, lblDatum, 9, SpringLayout.NORTH, datePanel);
		sl_datePanel.putConstraint(SpringLayout.WEST, lblDatum, 5, SpringLayout.WEST, datePanel);
		datePanel.add(lblDatum);
		
		txtDateInput = new JTextField();
		sl_datePanel.putConstraint(SpringLayout.WEST, txtDateInput, 80, SpringLayout.WEST, datePanel);
		txtDateInput.setPreferredSize(new Dimension(200, 25));
		sl_datePanel.putConstraint(SpringLayout.NORTH, txtDateInput, 6, SpringLayout.NORTH, datePanel);
		txtDateInput.setName("txtDateInput");
		txtDateInput.setText("");
		txtDateInput.setHorizontalAlignment(SwingConstants.CENTER);
		txtDateInput.setEnabled(true);
		//txtDateInput.setColumns(24);
		txtDateInput.getDocument().addDocumentListener(timerHourController);
		datePanel.add(txtDateInput);
		
		btnDatePicker = new JButton("...");
		btnDatePicker.setPreferredSize(new Dimension(41, 25));
		sl_datePanel.putConstraint(SpringLayout.NORTH, btnDatePicker, 5, SpringLayout.NORTH, datePanel);
		sl_datePanel.putConstraint(SpringLayout.WEST, btnDatePicker, 5, SpringLayout.EAST, txtDateInput);
		btnDatePicker.setName("btnDatePicker");
		final JFrame popupFrame = new JFrame();
		popupFrame.setName("popupFrame");
		btnDatePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				txtDateInput.setText(new DatePicker(popupFrame).setPickedDate().replace("-", "."));
			}
		});
		btnDatePicker.addActionListener(timerHourController);
		datePanel.add(btnDatePicker);

		JPanel manualEntryPanel = new JPanel();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, manualEntryPanel, 5, SpringLayout.SOUTH, datePanel);
		manualEntryPanel.setPreferredSize(new Dimension(10, 40));
		sl_contentPanel.putConstraint(SpringLayout.WEST, manualEntryPanel, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, manualEntryPanel, -5, SpringLayout.EAST, contentPanel);
		manualEntryPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(manualEntryPanel);
		FlowLayout fl_manualEntryPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		manualEntryPanel.setLayout(fl_manualEntryPanel);

		JLabel lblFrom = new JLabel("Von:");
		lblFrom.setForeground(Color.WHITE);
		manualEntryPanel.add(lblFrom);

		txtStartTime = new JTextField();
		txtStartTime.setPreferredSize(new Dimension(7, 25));
		txtStartTime.setEnabled(true);
		lblFrom.setLabelFor(txtStartTime);
		txtStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartTime.setText("");
		txtStartTime.getDocument().addDocumentListener(timerHourController);
		manualEntryPanel.add(txtStartTime);
		txtStartTime.setColumns(5);

		JLabel lblTo = new JLabel("Bis:");
		lblTo.setForeground(Color.WHITE);
		manualEntryPanel.add(lblTo);

		txtEndTime = new JTextField();
		txtEndTime.setPreferredSize(new Dimension(7, 25));
		txtEndTime.setEnabled(true);
		lblTo.setLabelFor(txtEndTime);
		txtEndTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndTime.setText("");
		txtEndTime.getDocument().addDocumentListener(timerHourController);
		manualEntryPanel.add(txtEndTime);
		txtEndTime.setColumns(5);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 40));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, panel, 395, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, panel, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, panel, -5, SpringLayout.EAST, contentPanel);
		panel.setBackground(new Color(31, 32, 33));
		contentPanel.add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel lblComment = new JLabel("Kommentar:");
		lblComment.setForeground(Color.WHITE);
		sl_panel.putConstraint(SpringLayout.NORTH, lblComment, 8, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblComment, 5, SpringLayout.WEST, panel);
		panel.add(lblComment);
		lblComment.setHorizontalAlignment(SwingConstants.CENTER);
		lblComment.setLabelFor(textFieldCommentTimerView);

		textFieldCommentTimerView = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textFieldCommentTimerView, 5, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textFieldCommentTimerView, 80, SpringLayout.WEST, panel);
		textFieldCommentTimerView.setName("textFieldCommentTimerView");
		panel.add(textFieldCommentTimerView);
		textFieldCommentTimerView.setAlignmentX(Component.LEFT_ALIGNMENT);
		textFieldCommentTimerView.setPreferredSize(new Dimension(246, 25));
		textFieldCommentTimerView.setToolTipText("");
		textFieldCommentTimerView.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel lblPauseDuration = new JLabel("Pause:");
		lblPauseDuration.setForeground(Color.WHITE);
		manualEntryPanel.add(lblPauseDuration);

		textPauseDuration = new JTextField();
		textPauseDuration.setPreferredSize(new Dimension(7, 25));
		textPauseDuration.setEnabled(true);
		lblPauseDuration.setLabelFor(textPauseDuration);
		textPauseDuration.setHorizontalAlignment(SwingConstants.CENTER);
		textPauseDuration.getDocument().addDocumentListener(timerHourController);
		manualEntryPanel.add(textPauseDuration);
		textPauseDuration.setColumns(5);

		JPanel confirmButtonPanel = new JPanel();
		confirmButtonPanel.setPreferredSize(new Dimension(10, 40));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, confirmButtonPanel, 460, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, confirmButtonPanel, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, confirmButtonPanel, -5, SpringLayout.EAST, contentPanel);
		confirmButtonPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(confirmButtonPanel);

		//Reset Button
		btnReset = new JButton("Reset");
		btnReset.setName("btnReset");
		btnReset.addActionListener(timerHourController);
		confirmButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnReset.setActionCommand(StaticActions.ACTION_TIMER_RESET);
		confirmButtonPanel.add(btnReset);

		//Save Button
		btnSaveTimerView = new JButton("Sichern");
		btnSaveTimerView.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnSaveTimerView.setName("btnSaveTimerView");
		btnSaveTimerView.addActionListener(timerHourController);
		btnSaveTimerView.setActionCommand(StaticActions.ACTION_TIMER_SAVE);
		confirmButtonPanel.add(btnSaveTimerView);

		JPanel errorPanel = new JPanel();
		errorPanel.setPreferredSize(new Dimension(10, 40));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, errorPanel, 525, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, errorPanel, 5, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, errorPanel, -5, SpringLayout.EAST, contentPanel);
		errorPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(errorPanel);

		lblErrorMessage = new JLabel("Error Message");
		lblErrorMessage.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblErrorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		//lblErrorMessage.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblErrorMessage.setForeground(new Color(255, 140, 0));
		lblErrorMessage.setVisible(false);
		SpringLayout sl_errorPanel = new SpringLayout();
		sl_errorPanel.putConstraint(SpringLayout.NORTH, lblErrorMessage, 0, SpringLayout.NORTH, errorPanel);
		sl_errorPanel.putConstraint(SpringLayout.WEST, lblErrorMessage, 50, SpringLayout.WEST, errorPanel);
		sl_errorPanel.putConstraint(SpringLayout.SOUTH, lblErrorMessage, 0, SpringLayout.SOUTH, errorPanel);
		sl_errorPanel.putConstraint(SpringLayout.EAST, lblErrorMessage, -50, SpringLayout.EAST, errorPanel);
		errorPanel.setLayout(sl_errorPanel);
		errorPanel.add(lblErrorMessage);

	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JLabel getDurationLabel() {
		return durationLabel;
	}

	public JButton getBtnStart() {
		return btnStart;
	}

	public JButton getBtnStop() {
		return btnStop;
	}

	public JTextField getTxtDateInput() {
		return txtDateInput;
	}

	public void setTxtDateInput(JTextField txtDateInput) {
		this.txtDateInput = txtDateInput;
	}

	public JButton getBtnDatePicker() {
		return btnDatePicker;
	}

	public JButton getBtnPause() {
		return btnPause;
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
		return textFieldCommentTimerView;
	}

	public JComboBox getProjectDropdown() {
		return projectDropdown;
	}
	
	public JComboBox getServiceDropdown() {
		return serviceDropdown;
	}

	public JLabel getLblErrorMessage() {
		return lblErrorMessage;
	}

	public JButton getBtnSave() {
		return btnSaveTimerView;
	}
	
	public JButton getBtnReset() {
		return btnReset;
	}

	public boolean isErrorVisible() {
		return errorVisible;
	}

	public void setErrorVisible(boolean errorVisible) {
		this.errorVisible = errorVisible;
	}

	public boolean isButtonsHighlighted() {
		return buttonsHighlighted;
	}

	public void setButtonsHighlighted(boolean buttonsHighlighted) {
		this.buttonsHighlighted = buttonsHighlighted;
	}
	
	public void setTextFieldCommentTimerView(JTextField textFieldCommentTimerView) {
		this.textFieldCommentTimerView = textFieldCommentTimerView;
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
		if (arg instanceof TimerModel) {
			// update timer-stopwatch
			this.durationLabel.setText(((TimerModel) arg).timerToString(true));

			/*
			 * Update project list: ProjectList from TimerModel is an ArrayList of Objects
			 * containing ArrayLists of Objects (project data). The inner ArrayList contains
			 * the project id (index 0) and the project name (index 1). To only display the
			 * projects names, the program is iterating over the project data and adds the
			 * names to a new ArrayList 'projectNames'. This ArrayList is then converted to
			 * an Array and provides the needed information for the ComboBox (dropdown with
			 * selectable projects).
			 */
			if (!((TimerModel) arg).isTimerRunning() && !((TimerModel) arg).isProjectSet()) {
				ArrayList<String> projectNames = new ArrayList<>();
				((TimerModel) arg).getProjectList().forEach(project -> {
					projectNames.add(project.get(1).toString());
				});
				this.projectDropdown.setModel(new DefaultComboBoxModel(projectNames.toArray()));
				System.out.println("Projects loaded into TimerView.");
			}
			
			if (!((TimerModel) arg).isTimerRunning() && !((TimerModel) arg).isServiceSet() && ((TimerModel) arg).getServiceList() != null) {
				ArrayList<String> services = new ArrayList<>();
				((TimerModel) arg).getServiceList().forEach(service -> {
					services.add(service.get(1).toString());
				});
				this.serviceDropdown.setModel(new DefaultComboBoxModel(services.toArray()));
				System.out.println("Services loaded into TimerView.");
			}
		}
	}
}
