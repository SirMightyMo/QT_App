package main.java.controller;

import java.awt.event.ActionEvent;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

import javax.swing.event.DocumentEvent;

import main.java.model.Hashing;
import main.java.model.IModel;
import main.java.model.LoginModel;
import main.java.model.User;
import main.java.view.IView;
import main.java.view.LoginView;

public final class LoginController extends Hashing implements IController {

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

	/**
	 * Queries the hashed password from database and compares it to the
	 * plaintext password-input by the user.
	 * When passwords match it calls the method login() method.
	 * If not, it show an error message.
	 * 
	 * @see main.java.model.Hashing#validatePassword(String, String)
	 * @author Leander, Sven
	 */
	private void securityCheck() {
		if (view.getErrorMessage() != null) {
			view.deleteErrorMessage();
		}
		
		String user = view.getUsernameInput();
		char[] pass = view.getPasswordInput();
		String passStr = String.valueOf(pass);
		boolean pwMatches = false;
		
		try {
			String hash = generatePasswordHash(pass);
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT u_id, username, email, password FROM users WHERE username='" + user + "';"; 
		ArrayList<Object> result = dbc.query(sql, true);
		
		if (result.isEmpty()) {
			view.setErrorMessage("Ungültiger Benutzername");
		} else {
			if (view.getErrorMessage() != null) {
				view.deleteErrorMessage();
			}
			
			// read result from database
			int u_id = (int) result.get(0);
			String name = (String) result.get(1);
			String email = (String) result.get(2);
			String hash = (String) result.get(3);
			try {
				pwMatches = validatePassword(passStr, hash); // check password with hash
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
			}
			
			if (pwMatches) {
				login(u_id, name, email);
			} else {
				view.setErrorMessage("Passwort inkorrekt");
			}
		}
		
	}

	private void login(int u_id, String name, String email) {
		System.out.println("Sie werden eingeloggt");
		new User(u_id, name, email);
		new AppMainController();
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
