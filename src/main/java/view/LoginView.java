package main.java.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public final class LoginView extends WindowSuperclass implements IView {

	private JLabel usernameLabel;
	private JLabel passwordLabel;

	private JTextField usernameInputField;
	private JPasswordField passwordInputField;

	private JButton loginButton;
	private JButton registerButton;

	private JLabel errorMessage;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JLabel lblNewLabel;

	public LoginView() {
		super();
		getContentPane().setPreferredSize(new Dimension(400, 175));
		init();
	}

	private void init() {
		setLocationRelativeTo(null); // Center Frame
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 75));
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		usernameLabel = new JLabel("Benutzername:");
		usernameLabel.setPreferredSize(new Dimension(100, 25));
		sl_panel.putConstraint(SpringLayout.NORTH, usernameLabel, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, usernameLabel, 10, SpringLayout.WEST, panel);
		usernameLabel.setName("usernameLabel");
		panel.add(usernameLabel);

		usernameInputField = new JTextField(20);
		sl_panel.putConstraint(SpringLayout.NORTH, usernameInputField, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, usernameInputField, 10, SpringLayout.EAST, usernameLabel);
		sl_panel.putConstraint(SpringLayout.EAST, usernameInputField, -10, SpringLayout.EAST, panel);
		usernameInputField.setPreferredSize(new Dimension(166, 25));
		usernameInputField.setName("usernameInputField");
		usernameInputField.setBounds(100, 20, 165, 25);
		panel.add(usernameInputField);

		passwordLabel = new JLabel("Passwort:");
		passwordLabel.setPreferredSize(new Dimension(100, 25));
		sl_panel.putConstraint(SpringLayout.NORTH, passwordLabel, 10, SpringLayout.SOUTH, usernameLabel);
		sl_panel.putConstraint(SpringLayout.WEST, passwordLabel, 10, SpringLayout.WEST, panel);
		passwordLabel.setName("passwordLabel");
		panel.add(passwordLabel);

		passwordInputField = new JPasswordField("", 20);
		sl_panel.putConstraint(SpringLayout.NORTH, passwordInputField, 5, SpringLayout.SOUTH, usernameInputField);
		sl_panel.putConstraint(SpringLayout.WEST, passwordInputField, 10, SpringLayout.EAST, passwordLabel);
		sl_panel.putConstraint(SpringLayout.EAST, passwordInputField, -10, SpringLayout.EAST, panel);
		passwordInputField.setPreferredSize(new Dimension(166, 25));
		passwordInputField.setName("passwordInputField");
		passwordInputField.setBounds(100, 20, 165, 25);
		panel.add(passwordInputField);

		this.setTitle("Login");
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, getContentPane());
		getContentPane().setLayout(springLayout);
		getContentPane().add(panel);

		panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(10, 35));
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 10, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel_1);

		registerButton = new JButton("Registrieren");
		panel_1.add(registerButton);
		sl_panel.putConstraint(SpringLayout.NORTH, registerButton, 46, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, registerButton, 166, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, registerButton, 332, SpringLayout.WEST, panel);
		registerButton.setName("registerButton");

		loginButton = new JButton("Einloggen");
		panel_1.add(loginButton);
		sl_panel.putConstraint(SpringLayout.NORTH, loginButton, 46, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, loginButton, 166, SpringLayout.WEST, panel);
		loginButton.setName("loginButton");
		getRootPane().setDefaultButton(loginButton);

		panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 0, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 0, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, 0, SpringLayout.SOUTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_2, 0, SpringLayout.EAST, getContentPane());
		getContentPane().add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		errorMessage = new JLabel("");
		errorMessage.setPreferredSize(new Dimension(400, 25));
		panel_2.add(errorMessage);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public String getUsernameInput() {
		return this.usernameInputField.getText();
	}

	public JPasswordField getPasswordInputField() {
		return passwordInputField;
	}

	public char[] getPasswordInput() {
		return this.passwordInputField.getPassword();
	}

	public JLabel getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String msg) {
		this.errorMessage = new JLabel(msg);
		this.errorMessage.setForeground(new Color(255, 140, 0));
		this.panel_2.add(errorMessage);
		SwingUtilities.updateComponentTreeUI(this);
	}

	public void deleteErrorMessage() {
		this.panel_2.remove(errorMessage);
	}

	public JButton getLoginButton() {
		return this.loginButton;
	}

	public JButton getRegisterButton() {
		return this.registerButton;
	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
