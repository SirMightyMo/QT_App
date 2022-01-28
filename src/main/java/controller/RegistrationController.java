package main.java.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import main.java.view.RegistrationView;
import main.java.model.RegistrationModel;

public final class RegistrationController implements ActionListener{
	
	private RegistrationView view;
	private RegistrationModel model;
	private DatabaseController dbc;
	
	public RegistrationController(DatabaseController dbc) {
		
		this.dbc = dbc;
		this.view = new RegistrationView();
		this.model = new RegistrationModel();
		 
		this.init();
	}
	
	
	private void init() {
		view.getLoginButton().addActionListener(e -> 
			this.login()	
		);
		view.getRegisterButton().addActionListener(e->
			this.registration()
		);
	}
	
	
	private boolean inputCheck() {
		System.out.println("SecurityCheck");
		if (view.getErrorMessage() != null) {
			view.deleteErrorMessage();
		}
		if(checkUsername() && checkEmail() && checkPassword() &&  checkSecurityQuestion()) {
			System.out.println("Success");
		}
		
		return true;
	}
	
	private boolean checkUsername() {
		if (!view.getUsernameInput().isEmpty()) {
			return true;
		}
		else {
			System.out.println("username is empty");
			view.setErrorMessage("Choose a username!");
			return false;
		}
	}
	
	
	private boolean checkEmail() {
		
		String emailIn = view.getEmailInput();
		String emailConfirm = view.getEmailConfirmInput();
		
		if (emailIn.equals(emailConfirm) && !emailIn.isEmpty()) {
			return true;
		}
		
		else {
			System.out.println("Emails arent equal");
			view.setErrorMessage("Wrong E-Mail!");
			return false;
		}
	}
	
	
	private boolean checkPassword() {
		
		char[] pwIn = this.view.getPasswordInput();
		char[] pwConfirm = this.view.getPasswordConfirmInput();
		
		if (Arrays.equals(pwIn, pwConfirm) &&  pwIn.length != 0) {			
			return true;
		}
		else{
			view.setErrorMessage("Passwords are not equal");
			System.out.println("wrong psw");
			return false;
		}
	}
	
	private boolean checkSecurityQuestion() {
		
		String answer = view.getSecurityAnswer();
		
		if (!answer.isEmpty()) {
			return true;
		}
		else {
			view.setErrorMessage("Choose a security-question and answer it!");
			return false;
		}
	}
	
	private void login() {
		System.out.println("sie werden eingeloggt");
		new LoginController(this.dbc);
		this.view.dispose();
	}
	
	private void registration() {
		System.out.println("sie werden weitergeleitet");
		if(inputCheck());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
