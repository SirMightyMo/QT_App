package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.event.DocumentEvent;

import main.java.view.IView;
import main.java.view.LoginView;
import main.java.model.IModel;
import main.java.model.LoginModel;
import main.java.model.User;

public final class LoginController implements IController {

	private LoginView view;
	private LoginModel model;
	private DatabaseController dbc = DatabaseController.getInstance();

	public LoginController() {
		this.view = new LoginView();
		this.model = new LoginModel();
		this.init();
	}

	private void init() {
		view.getLoginButton().addActionListener(e -> this.securityCheck());
		view.getRegisterButton().addActionListener(e -> this.registration());
	}

	private void securityCheck() {
		if (view.getErrorMessage() != null) {
			view.deleteErrorMessage();
		}
		
		String user = view.getUsernameInput();
		char[] pass = view.getPasswordInput();
		String sql = "SELECT u_id, username, email FROM users WHERE username='" + user + "' AND password='" + String.valueOf(pass) + "';"; 
		ArrayList<Object> result = dbc.query(sql, true);
		
		if (result.isEmpty()) {
			view.setErrorMessage("Login failed!");
		} else {
			if (view.getErrorMessage() != null) {
				view.deleteErrorMessage();
			}
			
			int u_id = (int) result.get(0);
			String name = (String) result.get(1);
			String email = (String) result.get(2);
			
			login(u_id, name, email);
		}
		
	}

	private void login(int u_id, String name, String email) {
		System.out.println("Sie werden eingeloggt");
		new DashboardController(new User(u_id, name, email));
		//new ProjectController();
		this.view.dispose();
	}

	private void registration() {
		System.out.println("Sie werden weitergeleitet");
		RegistrationController r = new RegistrationController();
		this.view.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IView getView() {
		// TODO Auto-generated method stub
		return null;
	}

}
