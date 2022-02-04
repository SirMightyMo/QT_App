package main.java.controller;

import java.awt.event.ActionEvent;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.event.DocumentEvent;

import main.java.model.Hashing;
import main.java.model.IModel;
import main.java.model.Regex;
import main.java.model.RegistrationModel;
import main.java.view.IView;
import main.java.view.RegistrationView;

public final class RegistrationController extends Hashing implements IController {
	
	private RegistrationView view;
	private RegistrationModel model;
	private DatabaseController dbc = DatabaseController.getInstance();
	
	public RegistrationController() {
		
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
			return true;
		} else {
			return false;
		}
		
	}
	
	private boolean checkUsername() {
		if (view.getErrorMessage() != null) {
			view.deleteErrorMessage();
		}
		
		String chosenUsername = view.getUsernameInput();
		if (!chosenUsername.isEmpty()) {
			if (!usernameIsTaken(chosenUsername)) {
				return true;				
			} else {
				view.setErrorMessage("Username already taken!");
				return false;
			}
		}
		else {
			System.out.println("username is empty");
			view.setErrorMessage("Choose a username!");
			return false;
		}
	}
	
	
	private boolean checkEmail() {
		
		if (view.getErrorMessage() != null) {
			view.deleteErrorMessage();
		}
		
		String emailIn = view.getEmailInput();
		String emailConfirm = view.getEmailConfirmInput();
		
		if (emailIn.equals(emailConfirm) && !emailIn.isEmpty() && validateEmail(emailIn)) {
			return true;
		}
		
		else {
			System.out.println("Emails arent equal");
			view.setErrorMessage("Wrong E-Mail!");
			return false;
		}
	}
	
	
	private boolean validateEmail(String email) {
		Matcher matcher = Regex.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
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
		System.out.println("Login wird aufgerufen.");
		new LoginController();
		this.view.dispose();
	}
	
	// TODO: Discuss correct use of char[] to write to db (for query, char[] needs to be converted to String)
	// TODO: Hash Password
	private void registration() {
		System.out.println("Benutzer wird registriert.");
		String hash = null;
		if(inputCheck()) {
			String username = view.getUsernameInput();
			char[] password = view.getPasswordInput();
			try {
				hash = generatePasswordHash(password);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
				e.printStackTrace();
			}
			
			String email = view.getEmailInput();
			int selectedQuestion = view.getSecurityQuestionPicker().getSelectedIndex();
			String security_question = view.getQuestions()[selectedQuestion];
			String answer = view.getSecurityAnswer();
			
			dbc.insert("INSERT INTO users(username,password,email,security_question,answer)VALUES("
					+ "'" + username + "',"
					+ "'" + hash + "',"
					+ "'" + email + "',"
					+ "'" + security_question + "',"
					+ "'" + answer + "');"
					);
			login(); // Change View to Login
		};
	}
	

	private boolean usernameIsTaken(String username) {
		ArrayList<Object> result = dbc.query("SELECT username FROM users WHERE username='" + username + "'", true);
		return (!result.isEmpty());
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
