package main.java.view;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import main.java.controller.AccountController;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;

public class AccountView implements IView {

	
	private JPanel accountPanel;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	

	/**
	 * Create the panel.
	 */
	public AccountView(AccountController accountController) {

		accountPanel = new JPanel();
		accountPanel.setName("dashboardMainPane");
		accountPanel.setBounds(0, 0, 1490, 1060);
		accountPanel.setBackground(new Color(47,48,52));
		accountPanel.setLayout(null);
		
		JPanel accountPanel1 = new JPanel();
		accountPanel1.setBounds(10, 87, 1470, 943);
		accountPanel1.setName("dashboardTimerPane");
		accountPanel1.setBackground(new Color(31,32,33));
		accountPanel.add(accountPanel1);
		
		JLabel lblNewLabel = new JLabel("Account Einstellungen");
		lblNewLabel.setBounds(10, 60, 134, 24);
		accountPanel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);
		accountPanel1.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(31,32,33));
		panel.setBounds(30, 510, 457, 314);
		accountPanel1.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1_2_2 = new JLabel("Email Benachrichtigungen");
		lblNewLabel_1_2_2.setBounds(0, 0, 260, 31);
		panel.add(lblNewLabel_1_2_2);
		
		JLabel lblNewLabel_2 = new JLabel("Wählen sie die Art von Benachrichtigungen, die sie erhalten möchten:");
		lblNewLabel_2.setBounds(0, 30, 400, 31);
		panel.add(lblNewLabel_2);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\tProjektende");
		chckbxNewCheckBox_1.setBounds(10, 68, 210, 31);
		panel.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setOpaque(false);
		chckbxNewCheckBox_1.setContentAreaFilled(false);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Monatliche Zusammenfassung");
		chckbxNewCheckBox.setBounds(10, 118, 210, 31);
		panel.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setOpaque(false);
		chckbxNewCheckBox.setContentAreaFilled(false);
		
		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Passwortänderungen");
		chckbxNewCheckBox_4.setBounds(10, 171, 210, 31);
		panel.add(chckbxNewCheckBox_4);
		chckbxNewCheckBox_4.setOpaque(false);
		chckbxNewCheckBox_4.setContentAreaFilled(false);
		
		JCheckBox chckbxNewCheckBox_1_1 = new JCheckBox("PDF-Export");
		chckbxNewCheckBox_1_1.setBounds(10, 222, 210, 31);
		panel.add(chckbxNewCheckBox_1_1);
		chckbxNewCheckBox_1_1.setOpaque(false);
		chckbxNewCheckBox_1_1.setContentAreaFilled(false);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("qualitytime Newsletter");
		chckbxNewCheckBox_2.setBounds(10, 272, 210, 31);
		panel.add(chckbxNewCheckBox_2);
		chckbxNewCheckBox_2.setOpaque(false);
		chckbxNewCheckBox_2.setContentAreaFilled(false);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(31, 32, 33));
		panel_1.setBounds(30, 252, 457, 247);
		accountPanel1.add(panel_1);
		
		JLabel lblNewLabel_1_2_1_3 = new JLabel("Passwort ändern");
		lblNewLabel_1_2_1_3.setBounds(0, 0, 420, 31);
		panel_1.add(lblNewLabel_1_2_1_3);
		
		JLabel lblNewLabel_1_2_1_1_3 = new JLabel("altes Passwort");
		lblNewLabel_1_2_1_1_3.setBounds(10, 70, 150, 31);
		panel_1.add(lblNewLabel_1_2_1_1_3);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(170, 70, 250, 31);
		panel_1.add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(170, 130, 250, 31);
		panel_1.add(textField_8);
		
		JLabel lblNewLabel_1_2_1_1_1_1 = new JLabel("neues Passwort");
		lblNewLabel_1_2_1_1_1_1.setBounds(10, 130, 150, 31);
		panel_1.add(lblNewLabel_1_2_1_1_1_1);
		
		JLabel lblNewLabel_1_2_1_1_2_2 = new JLabel("Passwort wiederholen\r\n");
		lblNewLabel_1_2_1_1_2_2.setBounds(10, 190, 150, 31);
		panel_1.add(lblNewLabel_1_2_1_1_2_2);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(170, 190, 250, 31);
		panel_1.add(textField_9);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(31, 32, 33));
		panel_1_1.setBounds(497, 252, 457, 247);
		accountPanel1.add(panel_1_1);
		
		JLabel lblNewLabel_1_2_1_2 = new JLabel("Sicherheitsfrage ändern");
		lblNewLabel_1_2_1_2.setBounds(0, 0, 420, 31);
		panel_1_1.add(lblNewLabel_1_2_1_2);
		
		JLabel lblNewLabel_1_2_1_1_2_1 = new JLabel("Sicherheitsfrage wählen");
		lblNewLabel_1_2_1_1_2_1.setBounds(10, 60, 150, 31);
		panel_1_1.add(lblNewLabel_1_2_1_1_2_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(170, 60, 250, 31);
		panel_1_1.add(comboBox);
		
		textField_6 = new JTextField();
		textField_6.setBounds(170, 120, 250, 31);
		panel_1_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_1_2_1_1_2_1_1 = new JLabel("Antwort");
		lblNewLabel_1_2_1_1_2_1_1.setBounds(10, 120, 150, 31);
		panel_1_1.add(lblNewLabel_1_2_1_1_2_1_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBackground(new Color(31, 32, 33));
		panel_1_2.setBounds(30, 11, 457, 230);
		accountPanel1.add(panel_1_2);
		
		JTextField textField = new JTextField();
		textField.setBounds(10, 95, 190, 31);
		panel_1_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Vorname");
		lblNewLabel_1.setBounds(10, 64, 120, 31);
		panel_1_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nachname");
		lblNewLabel_1_1.setBounds(230, 64, 120, 31);
		panel_1_2.add(lblNewLabel_1_1);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(10, 168, 410, 31);
		panel_1_2.add(textField_2);
		textField_2.setColumns(10);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(230, 95, 190, 31);
		panel_1_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Email Adresse");
		lblNewLabel_1_1_1.setBounds(10, 137, 188, 20);
		panel_1_2.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Profil");
		lblNewLabel_1_2.setBounds(0, 0, 120, 31);
		panel_1_2.add(lblNewLabel_1_2);
		
		JPanel panel_1_1_1 = new JPanel();
		panel_1_1_1.setLayout(null);
		panel_1_1_1.setBackground(new Color(31, 32, 33));
		panel_1_1_1.setBounds(30, 835, 457, 65);
		accountPanel1.add(panel_1_1_1);
		
		JButton btnNewButton = new JButton("Änderungen verwerfen");
		btnNewButton.setBounds(40, 11, 170, 36);
		panel_1_1_1.add(btnNewButton);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnNewButton.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnNewButton.setForeground(Color.WHITE);
		    }
		});
		
		
		JButton btnnderungenSpeichern = new JButton("Änderungen speichern");
		btnnderungenSpeichern.setBounds(242, 11, 170, 36);
		panel_1_1_1.add(btnnderungenSpeichern);
		btnnderungenSpeichern.setContentAreaFilled(false);
		btnnderungenSpeichern.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	btnnderungenSpeichern.setForeground(Color.ORANGE);
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	btnnderungenSpeichern.setForeground(Color.WHITE);
		    }
		});
		
	}
	
public JPanel getAccountPanel() {
		return accountPanel;
	}

	public void setAccountPanel(JPanel accountPanel) {
		this.accountPanel = accountPanel;
	}
	@Override
	public void update(Observable o, Object arg) {
		
	}
}
