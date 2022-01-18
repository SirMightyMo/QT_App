package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

final class LoginController implements ActionListener{
	
	private LoginView view;
	private LoginModel model;
	
	public LoginController() {
		
		this.view = new LoginView();
		this.model = new LoginModel();
		 
		this.init();
		
		
	}
	
	
	private void init() {
		view.getLoginButton().addActionListener(e -> 
			this.securityCheck()			
		);
		view.getRegisterButton().addActionListener(e->
			this.registration()
		);
		
	}
	
	
	private void securityCheck() {
		System.out.println("SecurityCheck");
		if (view.getErrorMessage() != null) {
			view.deleteErrorMessage();
		}
		model.updateUserInput(view.getUsernameInput(), view.getPasswordInput());
		checkUserName(model.getUsernameInput(), model.getSavedUsername());
		model.zeroLocals();
		
		
	}
	
	
	private void checkUserName(String usernameInput, String savedUsername) {
		if (usernameInput.trim().equals(savedUsername)) {
			
			
			this.checkPassword(model.getPasswordInput(), model.getSavedPassword());
		}
		else {
			
			view.setErrorMessage("Wrong Username");
			
			System.out.println("wrong username");
		}
	}
	
	private void checkPassword(char[] passwordInput, char[] savedPassword) {
		
		
		if (Arrays.equals(passwordInput, savedPassword)) {			
			this.login();
		}
		else{
			view.setErrorMessage("Password incorrect");
			System.out.println("wrong psw");
			
		}
	}
	
	
	private void login() {
		System.out.println("sie werden eingeloggt");
	}
	
	private void registration() {
		System.out.println("sie werden weitergeleitet");
		RegistrationController r = new RegistrationController();
		this.view.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
