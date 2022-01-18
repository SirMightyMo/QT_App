package main.java.model;
import javax.swing.JFrame;

import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;




final class LoginModel {
	
	private String usernameInput;
	private String savedUsername;
	
	private char[] savedPassword;
	private char[] passwordInput;
	
	
	public LoginModel() {
		
		this.savedUsername = "sven";
		String str = "1234";
		this.savedPassword = str.toCharArray();
		
		
	}
	
	public String getUsernameInput() {
		return this.usernameInput;
	}
	
	public String getSavedUsername() {
		return this.savedUsername;
	}
	
	public char[] getPasswordInput() {
		return this.passwordInput;
	}
	
	public char[] getSavedPassword() {
		return this.savedPassword;
	}
	
	public void updateUserInput(String usrInput, char[] pswInput) {
		this.passwordInput = pswInput;
		this.usernameInput = usrInput;
	}
	
	public void zeroLocals() {
		Arrays.fill(savedPassword, '0');
		
	}

}
