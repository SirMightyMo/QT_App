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
		contentPanel.setBounds(0, 0, 1850, 1080);
		contentPanel.setBackground(new Color(31, 32, 33));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		// Project
		JPanel projectPanel = new JPanel();
		projectPanel.setName("projectPanel");
		contentPanel.add(projectPanel);
		projectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		projectPanel.setBackground(new Color(31, 32, 33));

		JLabel lblProject = new JLabel("Projekt:");
		lblProject.setName("lblProject");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		projectPanel.add(lblProject);

		projectDropdown.setPreferredSize(new Dimension(200, 20));
		projectDropdown.setName("comboBoxProject");
		lblProject.setLabelFor(projectDropdown);
		projectDropdown.setAlignmentX(0.0f);
		projectDropdown.addActionListener(timerHourController);
		projectDropdown.setActionCommand(StaticActions.ACTION_SET_PROJECT);
		projectPanel.add(projectDropdown);

		JButton btnLoadProjects = new JButton("\u21BB");
		btnLoadProjects.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
		btnLoadProjects.setName("btnLoadProjects");
		btnLoadProjects.addActionListener(timerHourController);
		btnLoadProjects.setActionCommand(StaticActions.ACTION_LOAD_PROJECTS);
		projectPanel.add(btnLoadProjects);

		hiddenTextFieldProjectID = new JTextField();
		//hiddenTextFieldProjectID.setFont(new Font("Tahoma", Font.PLAIN, 5));
		hiddenTextFieldProjectID.setHorizontalAlignment(SwingConstants.RIGHT);
		hiddenTextFieldProjectID.setEnabled(false);
		hiddenTextFieldProjectID.setEditable(false);
		hiddenTextFieldProjectID.setVisible(false);
		projectPanel.add(hiddenTextFieldProjectID);

		// Service
		JPanel servicePanel = new JPanel();
		servicePanel.setName("servicePanel");
		contentPanel.add(servicePanel);
		servicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		servicePanel.setBackground(new Color(31, 32, 33));

		JLabel lblService = new JLabel("Leistung:");
		lblService.setName("lblService");
		lblService.setHorizontalAlignment(SwingConstants.CENTER);
		servicePanel.add(lblService);

		serviceDropdown.setPreferredSize(new Dimension(200, 20));
		serviceDropdown.setName("serviceDropdown");
		lblService.setLabelFor(serviceDropdown);
		serviceDropdown.setAlignmentX(0.0f);
		serviceDropdown.addActionListener(timerHourController);
		serviceDropdown.setActionCommand(StaticActions.ACTION_SET_SERVICE);
		servicePanel.add(serviceDropdown);

		JButton btnLoadServices = new JButton("\u21BB");
		btnLoadServices.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
		btnLoadServices.setName("btnLoadServices");
		btnLoadServices.addActionListener(timerHourController);
		btnLoadServices.setActionCommand(StaticActions.ACTION_LOAD_SERVICES);
		servicePanel.add(btnLoadServices);
		
		JPanel durationPanel = new JPanel();
		durationPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(durationPanel);

		// Timer Label
		JLabel timerLabel = new JLabel("");
		lblProject.setName("timerLabel");
		durationPanel.add(timerLabel);
		timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		durationPanel.add(durationLabel);

		// Duration Label
		durationLabel.setName("durationLabel");
		durationLabel.setBounds(55, 5, 258, 83);
		durationLabel.setFont(new Font("Adam", Font.PLAIN, 78));
		durationLabel.setForeground(Color.WHITE);

		// Pane for buttons
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(buttonPanel);

		// Button start
		btnStart = new JButton("");
		btnStart.setName("btnStart");
		btnStart.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/play.gif")));
		btnStart.addActionListener(timerHourController);
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
		datePanel.setBackground(new Color(31, 32, 33));
		datePanel.setName("datePanel");
		contentPanel.add(datePanel);
		JLabel lblDatum = new JLabel("Datum:");
		datePanel.add(lblDatum);
		
		txtDateInput = new JTextField();
		txtDateInput.setName("txtDateInput");
		txtDateInput.setText("");
		txtDateInput.setHorizontalAlignment(SwingConstants.CENTER);
		txtDateInput.setEnabled(true);
		txtDateInput.setColumns(15);
		txtDateInput.getDocument().addDocumentListener(timerHourController);
		datePanel.add(txtDateInput);
		
		btnDatePicker = new JButton("...");
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
		manualEntryPanel.setBackground(new Color(31, 32, 33));
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
		panel.setBackground(new Color(31, 32, 33));
		contentPanel.add(panel);

		JLabel lblComment = new JLabel("Kommentar:");
		panel.add(lblComment);
		lblComment.setHorizontalAlignment(SwingConstants.CENTER);
		lblComment.setLabelFor(textFieldCommentTimerView);

		textFieldCommentTimerView = new JTextField();
		textFieldCommentTimerView.setName("textFieldCommentTimerView");
		panel.add(textFieldCommentTimerView);
		textFieldCommentTimerView.setAlignmentX(Component.LEFT_ALIGNMENT);
		textFieldCommentTimerView.setPreferredSize(new Dimension(300, 20));
		textFieldCommentTimerView.setToolTipText("");
		textFieldCommentTimerView.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldCommentTimerView.setColumns(20);

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
		confirmButtonPanel.setBackground(new Color(31, 32, 33));
		FlowLayout flowLayout = (FlowLayout) confirmButtonPanel.getLayout();
		contentPanel.add(confirmButtonPanel);

		//Reset Button
		btnReset = new JButton("Reset");
		btnReset.setName("btnReset");
		btnReset.addActionListener(timerHourController);
		btnReset.setActionCommand(StaticActions.ACTION_TIMER_RESET);
		confirmButtonPanel.add(btnReset);

		//Save Button
		btnSaveTimerView = new JButton("Sichern");
		btnSaveTimerView.setName("btnSaveTimerView");
		btnSaveTimerView.addActionListener(timerHourController);
		btnSaveTimerView.setActionCommand(StaticActions.ACTION_TIMER_SAVE);
		confirmButtonPanel.add(btnSaveTimerView);

		JPanel errorPanel = new JPanel();
		errorPanel.setBackground(new Color(31, 32, 33));
		FlowLayout flowLayout_1 = (FlowLayout) errorPanel.getLayout();
		contentPanel.add(errorPanel);

		lblErrorMessage = new JLabel("Error Message");
		//lblErrorMessage.setFont(new Font("Tahoma", Font.BOLD, 12));
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

	public JTextField getHiddenTextFieldProjectID() {
		return hiddenTextFieldProjectID;
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
