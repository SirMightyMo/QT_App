package main.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

public final class LostPwView extends WindowSuperclass implements IView{
	
	private JComboBox securityQuestionPicker;
	private String[] questions;
	
	private JButton backToLoginButton;
	private JButton backToRegistrationButton;
	private JButton checkButton;
	private JLabel errorMessage;
	private JLabel emailLabel;
	private JTextField emailInput;
	private JLabel answerLabel;
	private JTextField answerInput;
	private JLabel newPasswordLabel;
	private JLabel newPasswordConfirmLabel;
	private JPasswordField newPasswordInputField;
	private JPasswordField newPasswordConfirmField;
	
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel3;
	private JPanel panel4;
	private JLabel securityQuestionLabel;
	
	public LostPwView() {
		super();
		String[] q = { "Welches war Ihr erstes Konzert, das Sie besucht haben?",
				"Geben Sie Marke und Modell Ihres ersten Autos an.",
				"An welchem Ort haben sich Ihre Eltern kennengelernt?" };
		setSecurityQuestions(q);
		init();
	}
	
	private void init() {
		setLocationRelativeTo(null); // Center Frame
		setResizable(false);
		
		this.setLayout(new GridLayout(4,1));
		
						
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(5,5));
		emailLabel = new JLabel("E-Mail:");
		panel1.add(emailLabel);
		emailInput = new JTextField();
		panel1.add(emailInput);
		securityQuestionLabel = new JLabel("Sicherheitsfrage:");
		panel1.add(securityQuestionLabel);
		securityQuestionPicker = new JComboBox(questions);
		panel1.add(securityQuestionPicker);
		answerLabel = new JLabel("Antwort:");
		panel1.add(answerLabel);
		answerInput = new JTextField();
		panel1.add(answerInput);
		
		newPasswordLabel = new JLabel("Neues Passwort: ");
		panel1.add(newPasswordLabel);
		newPasswordInputField = new JPasswordField();
		panel1.add(newPasswordInputField);
		newPasswordConfirmLabel = new JLabel("Neues Passwort wiederholen: ");
		panel1.add(newPasswordConfirmLabel);
		newPasswordConfirmField = new JPasswordField();
		panel1.add(newPasswordConfirmField);
		
		
		panel2 = new JPanel();
		
		checkButton = new JButton("Passwort zurücksetzen");
		panel2.add(checkButton);
		
		panel3 = new JPanel();
		
		
		backToLoginButton = new JButton("Login");
		panel3.add(backToLoginButton);
	
		backToRegistrationButton = new JButton("Registrieren");
		panel3.add(backToRegistrationButton);
		
		panel4 = new JPanel();
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	
	
	
	public void setSecurityQuestions(String[] questions) {
		this.questions = questions;
	}
	public JLabel getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String msg) {
		this.errorMessage = new JLabel(msg);
		this.errorMessage.setForeground(new Color(255, 140, 0));
		this.panel4.add(errorMessage);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void deleteErrorMessage() {
		this.panel4.remove(errorMessage);
	}
	
	
	
	public JButton getBackToLoginButton() {
		
		return this.backToLoginButton;
	}

	public JButton getBackToRegisterButton() {
		
		return this.backToRegistrationButton;
	}

	public JButton getCheckButton() {
		return this.checkButton;
	}
	
	public JComboBox getQuestionPicker() {
		return this.securityQuestionPicker;
	}
	
	public String getEmailInput() {
		return this.emailInput.getText().trim();
	}
	
	public String getAnswerInput() {
		return this.answerInput.getText().trim();
	}
	
	
	public String getSelectedSecurityQuestion() {
		
		return this.securityQuestionPicker.getSelectedItem().toString();
	}
	
	public char[] getNewPasswordInput() {
		return this.newPasswordInputField.getPassword();
	}
	
	public char[] getNewPasswordConfirmInput() {
		return this.newPasswordConfirmField.getPassword();
	}
	
	@Override
	public void update(Observable o, Object arg) {
				// TODO Auto-generated method stub
				
	}

}
