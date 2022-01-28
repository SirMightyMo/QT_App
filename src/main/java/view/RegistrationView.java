package main.java.view;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import java.awt.Rectangle;

public final class RegistrationView extends JFrame {

	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JLabel passwordConfirmLabel;
	private JLabel emailLabel;
	private JLabel emailConfirmLabel;
	private JLabel securityQuestionLabel;
	private JLabel securityAnswerLabel;

	private JComboBox securityQuestionPicker;
	private JTextField securityAnswerInputField;
	private JTextField usernameInputField;
	private JTextField emailInputField;
	private JTextField emailConfirmInputField;

	private JPasswordField passwordInputField;
	private JPasswordField passwordConfirmInputField;

	private JButton loginButton;
	private JButton registerButton;

	private JLabel errorMessage;
	private JPanel panel;
	private JPanel panelTwo;
	private JPanel panelThree;

	private String[] questions;

	public RegistrationView() {

		String[] q = { "Your mothers maiden name?", "Your Fathers maiden name?" };
		setSecurityQuestions(q);
		init();
	}

	private void init() {

		panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2));

		usernameLabel = new JLabel("Username:");
		panel.add(usernameLabel);

		usernameInputField = new JTextField(20);
		usernameInputField.setBounds(100, 20, 165, 25);
		panel.add(usernameInputField);

		emailLabel = new JLabel("E-Mail:");
		panel.add(emailLabel);

		emailInputField = new JTextField(20);
		emailInputField.setBounds(100, 20, 165, 25);
		panel.add(emailInputField);

		emailConfirmLabel = new JLabel("Repeat your E-Mail to confirm:");
		panel.add(emailConfirmLabel);

		emailConfirmInputField = new JTextField(20);
		emailConfirmInputField.setBounds(100, 20, 165, 25);
		panel.add(emailConfirmInputField);

		passwordLabel = new JLabel("Password:");
		panel.add(passwordLabel);

		passwordInputField = new JPasswordField(20);
		passwordInputField.setBounds(100, 20, 165, 25);
		panel.add(passwordInputField);

		passwordConfirmLabel = new JLabel("Repeat your password to confirm:");
		panel.add(passwordConfirmLabel);

		passwordConfirmInputField = new JPasswordField(20);
		passwordConfirmInputField.setBounds(100, 20, 165, 25);
		panel.add(passwordConfirmInputField);

		panelTwo = new JPanel();
		panelTwo.setLayout(new BorderLayout());

		securityQuestionLabel = new JLabel("Choose your secure Question:");
		panelTwo.add(securityQuestionLabel, BorderLayout.NORTH);

		securityQuestionPicker = new JComboBox(questions);
		securityQuestionPicker.setBounds(100, 20, 165, 25);
		panelTwo.add(securityQuestionPicker, BorderLayout.CENTER);

		panelThree = new JPanel();
		panelThree.setLayout(new GridLayout(2, 2));

		securityAnswerLabel = new JLabel("Your answer:");
		panelThree.add(securityAnswerLabel);

		securityAnswerInputField = new JTextField(20);
		securityAnswerInputField.setBounds(100, 20, 165, 25);
		panelThree.add(securityAnswerInputField);

		loginButton = new JButton("Switch to Login");
		panelThree.add(loginButton);

		registerButton = new JButton("Register");
		panelThree.add(registerButton);

		this.errorMessage = new JLabel();

		this.setTitle("Register");
		getContentPane().setLayout(new GridLayout(4,1));
		getContentPane().add(panel);
		getContentPane().add(panelTwo);
		getContentPane().add(panelThree);
		getContentPane().add(errorMessage);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	public void setSecurityQuestions(String[] questions) {
		this.questions = questions;
	}

	public String getEmailInput() {
		return this.emailInputField.getText();
	}

	public String getEmailConfirmInput() {
		return this.emailConfirmInputField.getText();
	}

	public String getSecurityAnswer() {
		return this.securityAnswerInputField.getText();
	}

	// from LoginView:

	public JButton getLoginButton() {
		return this.loginButton;
	}

	public JButton getRegisterButton() {
		return this.registerButton;
	}

	public JLabel getErrorMessage() {
		return this.errorMessage;
	}

	public String getUsernameInput() {
		return this.usernameInputField.getText();
	}

	public char[] getPasswordInput() {

		return this.passwordInputField.getPassword();
	}

	public char[] getPasswordConfirmInput() {
		return this.passwordConfirmInputField.getPassword();
	}

	public void setErrorMessage(String msg) {

		this.errorMessage.setText(msg);
		this.errorMessage.setForeground(Color.red);
		this.errorMessage.setVisible(true);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void deleteErrorMessage() {
		this.errorMessage.setVisible(false);

	}

}
