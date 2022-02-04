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

import main.java.controller.NewProjectController;
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
public class NewProjectView implements IView {
	private static final long serialVersionUID = 1L;
	private JPanel contentPanel; // Container
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	private JTextField textFieldComment;
	private JLabel lblErrorMessage;
	private JButton btnSave;
	private boolean errorVisible;
	private boolean buttonsHighlighted;
	private JTextField textField;

	public NewProjectView(NewProjectController newProjectController) {
		//setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // top, left, bottom, right
		contentPanel.setBounds(0, 0, 365, 245);
		contentPanel.setBackground(new Color(31, 32, 33));
		contentPanel.setLayout(null);

		JPanel projectPanel = new JPanel();
		projectPanel.setBounds(10, 11, 345, 30);
		projectPanel.setName("projectPanel");
		contentPanel.add(projectPanel);
		projectPanel.setBackground(new Color(31, 32, 33));
		projectPanel.setLayout(null);

		JLabel lblProject = new JLabel("Neues Projekt anlegen");
		lblProject.setBounds(0, 0, 177, 30);
		lblProject.setName("lblProject");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		projectPanel.add(lblProject);
		lblProject.setName("timerLabel");

		JPanel manualEntryPanel = new JPanel();
		manualEntryPanel.setBounds(10, 98, 345, 30);
		manualEntryPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(manualEntryPanel);
		manualEntryPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblFrom = new JLabel("Start");
		manualEntryPanel.add(lblFrom);

		txtStartTime = new JTextField();
		txtStartTime.setEnabled(true);
		lblFrom.setLabelFor(txtStartTime);
		txtStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartTime.setText("");
		txtStartTime.getDocument().addDocumentListener(newProjectController);
		manualEntryPanel.add(txtStartTime);
		txtStartTime.setColumns(5);

		JLabel lblTo = new JLabel("Ende");
		manualEntryPanel.add(lblTo);

		txtEndTime = new JTextField();
		txtEndTime.setEnabled(true);
		lblTo.setLabelFor(txtEndTime);
		txtEndTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndTime.setText("");
		txtEndTime.getDocument().addDocumentListener(newProjectController);
		manualEntryPanel.add(txtEndTime);
		txtEndTime.setColumns(5);

		JPanel panel = new JPanel();
		panel.setBounds(10, 52, 345, 35);
		panel.setBackground(new Color(31, 32, 33));
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblComment = new JLabel("Projektname");
		panel.add(lblComment);
		lblComment.setHorizontalAlignment(SwingConstants.CENTER);
		lblComment.setLabelFor(textFieldComment);

		textFieldComment = new JTextField();
		textFieldComment.setName("textFieldComment");
		panel.add(textFieldComment);
		textFieldComment.setAlignmentX(Component.LEFT_ALIGNMENT);
		textFieldComment.setPreferredSize(new Dimension(300, 20));
		textFieldComment.setToolTipText("");
		textFieldComment.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldComment.setColumns(20);

		JPanel confirmButtonPanel = new JPanel();
		confirmButtonPanel.setBounds(10, 185, 230, 41);
		confirmButtonPanel.setBackground(new Color(31, 32, 33));
		FlowLayout flowLayout = (FlowLayout) confirmButtonPanel.getLayout();
		contentPanel.add(confirmButtonPanel);

		//Save Button
		btnSave = new JButton("Sichern");
		btnSave.setName("btnSave");
		btnSave.addActionListener(newProjectController);
		btnSave.setActionCommand(StaticActions.ACTION_TIMER_SAVE);
		confirmButtonPanel.add(btnSave);
		
				JButton btnLoadProjects = new JButton("\u21BB");
				confirmButtonPanel.add(btnLoadProjects);
				btnLoadProjects.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
				btnLoadProjects.setName("btnLoadProjects");
				btnLoadProjects.addActionListener(newProjectController);
				btnLoadProjects.setActionCommand(StaticActions.ACTION_LOAD_PROJECTS);

		JPanel errorPanel = new JPanel();
		errorPanel.setBounds(240, 185, 115, 41);
		errorPanel.setBackground(new Color(31, 32, 33));
		FlowLayout flowLayout_1 = (FlowLayout) errorPanel.getLayout();
		contentPanel.add(errorPanel);
		
				lblErrorMessage = new JLabel("Error Message");
				errorPanel.add(lblErrorMessage);
				//lblErrorMessage.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblErrorMessage.setForeground(new Color(255, 140, 0));
				lblErrorMessage.setVisible(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(31, 32, 33));
		panel_1.setBounds(10, 139, 345, 35);
		contentPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblKommentar = new JLabel("Kommentar\r\n");
		lblKommentar.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblKommentar);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setPreferredSize(new Dimension(300, 20));
		textField.setName("textFieldComment");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(20);
		textField.setAlignmentX(0.0f);
		panel_1.add(textField);

	}

	public JPanel getContentPanel() {
		return contentPanel;
	}



	public JTextField getTxtStartTime() {
		return txtStartTime;
	}

	public JTextField getTxtEndTime() {
		return txtEndTime;
	}


	public JTextField getTextFieldComment() {
		return textFieldComment;
	}


	public JLabel getLblErrorMessage() {
		return lblErrorMessage;
	}

	public JButton getBtnSave() {
		return btnSave;
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
		

		}
	}

