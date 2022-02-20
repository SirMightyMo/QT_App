package main.java.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public final class RegistrationView extends WindowSuperclass implements IView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	private JButton loginButton;
	private JButton registerButton;

	private JLabel errorMessage;
	private JPanel panelOne;
	private JPanel panelTwo;
	private JPanel panelThree;

	private String[] questions;
	private JPanel panel;
	private JPasswordField passwordConfirmInputField;

	public RegistrationView() {
		super();
		getContentPane().setPreferredSize(new Dimension(400, 420));
		String[] q = { "Welches war Ihr erstes Konzert, das Sie besucht haben?",
				"Geben Sie Marke und Modell Ihres ersten Autos an.",
				"An welchem Ort haben sich Ihre Eltern kennengelernt?" };
		setSecurityQuestions(q);
		init();
	}

	private void init() {
		setLocationRelativeTo(null); // Center Frame
		setResizable(false);

		panelTwo = new JPanel();
		panelTwo.setPreferredSize(new Dimension(10, 125));
		panelTwo.setName("panelTwo");
		SpringLayout sl_panelTwo = new SpringLayout();
		panelTwo.setLayout(sl_panelTwo);

		securityQuestionLabel = new JLabel("Sicherheitsfrage wählen:");
		sl_panelTwo.putConstraint(SpringLayout.EAST, securityQuestionLabel, -10, SpringLayout.EAST, panelTwo);
		securityQuestionLabel.setPreferredSize(new Dimension(119, 25));
		sl_panelTwo.putConstraint(SpringLayout.NORTH, securityQuestionLabel, 7, SpringLayout.NORTH, panelTwo);
		sl_panelTwo.putConstraint(SpringLayout.WEST, securityQuestionLabel, 10, SpringLayout.WEST, panelTwo);
		securityQuestionLabel.setName("securityQuestionLabel");
		panelTwo.add(securityQuestionLabel);

		panelThree = new JPanel();
		panelThree.setName("panelThree");
		FlowLayout fl_panelThree = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panelThree.setLayout(fl_panelThree);

		loginButton = new JButton("Zum Login");
		loginButton.setName("loginButton");
		panelThree.add(loginButton);

		registerButton = new JButton("Jetzt Registrieren");
		registerButton.setName("registerButton");
		panelThree.add(registerButton);
		getRootPane().setDefaultButton(registerButton);

		this.setTitle("Registrierung");
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, panelThree, 10, SpringLayout.SOUTH, panelTwo);
		springLayout.putConstraint(SpringLayout.EAST, panelThree, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelTwo, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelThree, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panelTwo, 0, SpringLayout.WEST, getContentPane());
		getContentPane().setLayout(springLayout);
		getContentPane().add(panelTwo);

		securityQuestionPicker = new JComboBox(questions);
		sl_panelTwo.putConstraint(SpringLayout.NORTH, securityQuestionPicker, 5, SpringLayout.SOUTH,
				securityQuestionLabel);
		sl_panelTwo.putConstraint(SpringLayout.WEST, securityQuestionPicker, 10, SpringLayout.WEST, panelTwo);
		sl_panelTwo.putConstraint(SpringLayout.EAST, securityQuestionPicker, -10, SpringLayout.EAST, panelTwo);
		securityQuestionPicker.setPreferredSize(new Dimension(250, 25));
		securityQuestionPicker.setMinimumSize(new Dimension(250, 25));
		securityQuestionPicker.setName("securityQuestionPicker");
		securityQuestionPicker.setBounds(100, 20, 165, 25);
		panelTwo.add(securityQuestionPicker);

		securityAnswerLabel = new JLabel("Ihre Antwort:");
		sl_panelTwo.putConstraint(SpringLayout.EAST, securityAnswerLabel, -10, SpringLayout.EAST, panelTwo);
		securityAnswerLabel.setPreferredSize(new Dimension(66, 25));
		sl_panelTwo.putConstraint(SpringLayout.NORTH, securityAnswerLabel, 5, SpringLayout.SOUTH,
				securityQuestionPicker);
		sl_panelTwo.putConstraint(SpringLayout.WEST, securityAnswerLabel, 10, SpringLayout.WEST, panelTwo);
		panelTwo.add(securityAnswerLabel);
		securityAnswerLabel.setName("securityAnswerLabel");

		securityAnswerInputField = new JTextField(20);
		sl_panelTwo.putConstraint(SpringLayout.WEST, securityAnswerInputField, 10, SpringLayout.WEST, panelTwo);
		sl_panelTwo.putConstraint(SpringLayout.EAST, securityAnswerInputField, -10, SpringLayout.EAST, panelTwo);
		securityAnswerInputField.setPreferredSize(new Dimension(166, 25));
		sl_panelTwo.putConstraint(SpringLayout.NORTH, securityAnswerInputField, 5, SpringLayout.SOUTH,
				securityAnswerLabel);
		panelTwo.add(securityAnswerInputField);
		securityAnswerInputField.setName("securityAnswerInputField");
		securityAnswerInputField.setBounds(100, 20, 165, 25);
		getContentPane().add(panelThree);
		panelOne = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panelOne, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panelOne, 0, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, panelTwo, 20, SpringLayout.SOUTH, panelOne);
		getContentPane().add(panelOne);
		panelOne.setPreferredSize(new Dimension(10, 180));
		SpringLayout sl_panelOne = new SpringLayout();
		panelOne.setLayout(sl_panelOne);

		usernameLabel = new JLabel("Benutzername:");
		usernameLabel.setPreferredSize(new Dimension(130, 25));
		sl_panelOne.putConstraint(SpringLayout.WEST, usernameLabel, 10, SpringLayout.WEST, panelOne);
		usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		usernameLabel.setName("usernameLabel");
		panelOne.add(usernameLabel);

		usernameInputField = new JTextField(0);
		sl_panelOne.putConstraint(SpringLayout.NORTH, usernameLabel, 0, SpringLayout.NORTH, usernameInputField);
		sl_panelOne.putConstraint(SpringLayout.WEST, usernameInputField, 10, SpringLayout.EAST, usernameLabel);
		sl_panelOne.putConstraint(SpringLayout.NORTH, usernameInputField, 10, SpringLayout.NORTH, panelOne);
		sl_panelOne.putConstraint(SpringLayout.EAST, usernameInputField, -10, SpringLayout.EAST, panelOne);
		usernameInputField.setPreferredSize(new Dimension(7, 25));
		usernameInputField.setName("usernameInputField");
		usernameInputField.setBounds(100, 20, 165, 25);
		panelOne.add(usernameInputField);

		emailLabel = new JLabel("E-Mail:");
		emailLabel.setPreferredSize(new Dimension(130, 25));
		sl_panelOne.putConstraint(SpringLayout.WEST, emailLabel, 10, SpringLayout.WEST, panelOne);
		emailLabel.setName("emailLabel");
		panelOne.add(emailLabel);

		emailInputField = new JTextField(0);
		sl_panelOne.putConstraint(SpringLayout.NORTH, emailLabel, 0, SpringLayout.NORTH, emailInputField);
		sl_panelOne.putConstraint(SpringLayout.WEST, emailInputField, 10, SpringLayout.EAST, emailLabel);
		sl_panelOne.putConstraint(SpringLayout.NORTH, emailInputField, 5, SpringLayout.SOUTH, usernameInputField);
		sl_panelOne.putConstraint(SpringLayout.EAST, emailInputField, -10, SpringLayout.EAST, panelOne);
		emailInputField.setPreferredSize(new Dimension(7, 25));
		emailInputField.setName("emailInputField");
		emailInputField.setBounds(100, 20, 165, 25);
		panelOne.add(emailInputField);

		emailConfirmLabel = new JLabel("E-Mail wiederholen:");
		emailConfirmLabel.setPreferredSize(new Dimension(130, 25));
		sl_panelOne.putConstraint(SpringLayout.WEST, emailConfirmLabel, 10, SpringLayout.WEST, panelOne);
		emailConfirmLabel.setName("emailConfirmLabel");
		panelOne.add(emailConfirmLabel);

		emailConfirmInputField = new JTextField(0);
		sl_panelOne.putConstraint(SpringLayout.NORTH, emailConfirmLabel, 0, SpringLayout.NORTH, emailConfirmInputField);
		sl_panelOne.putConstraint(SpringLayout.WEST, emailConfirmInputField, 10, SpringLayout.EAST, emailConfirmLabel);
		sl_panelOne.putConstraint(SpringLayout.NORTH, emailConfirmInputField, 5, SpringLayout.SOUTH, emailInputField);
		sl_panelOne.putConstraint(SpringLayout.EAST, emailConfirmInputField, -10, SpringLayout.EAST, panelOne);
		emailConfirmInputField.setPreferredSize(new Dimension(7, 25));
		emailConfirmInputField.setName("emailConfirmInputField");
		emailConfirmInputField.setBounds(100, 20, 165, 25);
		panelOne.add(emailConfirmInputField);

		passwordLabel = new JLabel("Passwort:");
		passwordLabel.setPreferredSize(new Dimension(130, 25));
		sl_panelOne.putConstraint(SpringLayout.WEST, passwordLabel, 10, SpringLayout.WEST, panelOne);
		passwordLabel.setName("passwordLabel");
		panelOne.add(passwordLabel);

		passwordInputField = new JPasswordField(0);
		sl_panelOne.putConstraint(SpringLayout.NORTH, passwordLabel, 0, SpringLayout.NORTH, passwordInputField);
		sl_panelOne.putConstraint(SpringLayout.WEST, passwordInputField, 10, SpringLayout.EAST, passwordLabel);
		sl_panelOne.putConstraint(SpringLayout.NORTH, passwordInputField, 5, SpringLayout.SOUTH,
				emailConfirmInputField);
		sl_panelOne.putConstraint(SpringLayout.EAST, passwordInputField, -10, SpringLayout.EAST, panelOne);
		passwordInputField.setPreferredSize(new Dimension(7, 25));
		passwordInputField.setName("passwordInputField");
		passwordInputField.setBounds(100, 20, 165, 25);
		panelOne.add(passwordInputField);

		passwordConfirmLabel = new JLabel("Passwort wiederholen:");
		passwordConfirmLabel.setPreferredSize(new Dimension(130, 25));
		sl_panelOne.putConstraint(SpringLayout.WEST, passwordConfirmLabel, 10, SpringLayout.WEST, panelOne);
		passwordConfirmLabel.setName("passwordConfirmLabel");
		panelOne.add(passwordConfirmLabel);

		passwordConfirmInputField = new JPasswordField(0);
		sl_panelOne.putConstraint(SpringLayout.NORTH, passwordConfirmLabel, 0, SpringLayout.NORTH,
				passwordConfirmInputField);
		sl_panelOne.putConstraint(SpringLayout.NORTH, passwordConfirmInputField, 5, SpringLayout.SOUTH,
				passwordInputField);
		sl_panelOne.putConstraint(SpringLayout.WEST, passwordConfirmInputField, 10, SpringLayout.EAST,
				passwordConfirmLabel);
		sl_panelOne.putConstraint(SpringLayout.EAST, passwordConfirmInputField, -10, SpringLayout.EAST, panelOne);
		passwordConfirmInputField.setPreferredSize(new Dimension(7, 25));
		passwordConfirmInputField.setName("passwordConfirmInputField");
		panelOne.add(passwordConfirmInputField);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, panelThree);
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel);

		this.errorMessage = new JLabel();
		panel.add(errorMessage);
		springLayout.putConstraint(SpringLayout.NORTH, errorMessage, 457, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, errorMessage, 84, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, errorMessage, -10, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, errorMessage, 287, SpringLayout.WEST, getContentPane());
		errorMessage.setHorizontalTextPosition(SwingConstants.CENTER);
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		errorMessage.setPreferredSize(new Dimension(380, 25));
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);

	}

	public String[] getQuestions() {
		return questions;
	}

	public void setQuestions(String[] questions) {
		this.questions = questions;
	}

	public JComboBox getSecurityQuestionPicker() {
		return securityQuestionPicker;
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
		this.errorMessage.setForeground(new Color(255, 140, 0));
		this.errorMessage.setVisible(true);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void deleteErrorMessage() {
		this.errorMessage.setVisible(false);

	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
