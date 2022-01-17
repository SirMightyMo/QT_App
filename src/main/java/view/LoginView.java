import javax.swing.JFrame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JPasswordField;
import javax.swing.JPanel;



public class LoginView extends JFrame {
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	
	private JTextField usernameInputField;
	private JPasswordField passwordInputField;
	
	private JButton loginButton;
	private JButton registerButton;
	
	private JLabel errorMessage;
	private  JPanel panel;
	
	
	public LoginView() {
		
		init();
	}
	
	
	private void init() {
		
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		
		
		usernameLabel = new JLabel("Username:");
		panel.add(usernameLabel);
		
		usernameInputField = new JTextField(20);
		usernameInputField.setBounds(100, 20, 165, 25);
		panel.add(usernameInputField);
		
		passwordLabel = new JLabel("Password:");
		panel.add(passwordLabel);
		
		
		passwordInputField = new JPasswordField("P1sswort", 20);
		passwordInputField.setBounds(100, 20, 165, 25);
		panel.add(passwordInputField);
		
		loginButton = new JButton("Login");
		panel.add(loginButton);
		
		registerButton = new JButton("Register");
		panel.add(registerButton);
		
		
		
		this.setTitle("Login");
		this.add(panel);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(500,500);
		this.setVisible(true);
		
	}
	
	public String getUsernameInput() {
		return this.usernameInputField.getText();
	}
	
	public char[] getPasswordInput() {
		
		return this.passwordInputField.getPassword();
	}
	
	public JLabel getErrorMessage() {
		return this.errorMessage;
	}
	
	public void setErrorMessage(String msg) {
		
		this.errorMessage = new JLabel(msg);
		this.errorMessage.setForeground(Color.red);
		this.panel.add(errorMessage);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void deleteErrorMessage() {
		this.panel.remove(errorMessage);
	}
	
	public JButton getLoginButton(){
		return this.loginButton;
	}
	
	public JButton getRegisterButton() {
		return this.registerButton;
	}
}
