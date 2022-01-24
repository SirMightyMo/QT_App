package main.java.model;
import javax.swing.JFrame;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;

import main.java.controller.DatabaseController;




public final class LoginModel {
	
	private DatabaseController dbc;
	
	private String usernameInput;
	private ArrayList<String> savedUsernames = new ArrayList<String>();
	private String correctUsername;
	
	private char[] savedPassword;
	private char[] passwordInput;
	private int i = 0;
	
	public LoginModel(DatabaseController dbc) {
		
		this.dbc = dbc;
		dbc.connect();

		ArrayList<Object> usernames = dbc.query("SELECT email FROM users");
		usernames.forEach(entry -> {
			System.out.println(entry.toString());

			savedUsernames.add(entry.toString());
		});
		dbc.close();
		
		
		
	}
	
	public String getUsernameInput() {
		return this.usernameInput;
	}
	
	public ArrayList<String> getSavedUsernames() {
		return this.savedUsernames;
	}
	
	public char[] getPasswordInput() {
		return this.passwordInput;
	}
	
	public void setCorrectUserName(String usr) {
		this.correctUsername = usr;
	}
	
	public char[] getSavedPassword() {
		this.dbc.connect();
		ArrayList<Object> passwords = dbc.query("SELECT password FROM users WHERE email = " + this.correctUsername);
		System.out.println(passwords);
		passwords.forEach(entry -> {
			this.savedPassword = entry.toString().toCharArray();
		});
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
