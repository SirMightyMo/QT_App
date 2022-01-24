package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import main.java.view.LoginView;
import main.java.model.LoginModel;

public final class LoginController implements ActionListener{
	
	private LoginView view;
	private LoginModel model;
	private DatabaseController dbc;
	
	public LoginController(DatabaseController dbc) {
		
		this.dbc = dbc;
		this.view = new LoginView();
		this.model = new LoginModel(dbc);
		 
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
		
		this.checkUserName(model.getUsernameInput(), model.getSavedUsernames());
		
		//model.zeroLocals();
		
		
	}
	
	
	private void checkUserName(String usernameInput, ArrayList<String> savedUsernames) {
		
		System.out.println(savedUsernames);
		System.out.println(usernameInput);

		if(savedUsernames.contains(usernameInput.trim())) {
			model.setCorrectUserName(usernameInput.trim());
			//this.checkPassword(model.getPasswordInput(), model.getSavedPassword());

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
		new DashboardController();
		//new ProjectController();
		this.view.dispose();
	}
	
	private void registration() {
		System.out.println("sie werden weitergeleitet");
		RegistrationController r = new RegistrationController(this.dbc);
		this.view.dispose();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
