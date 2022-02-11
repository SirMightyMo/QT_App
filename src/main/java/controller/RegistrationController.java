package main.java.controller;

import java.awt.event.ActionEvent;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

import javax.swing.event.DocumentEvent;

import main.java.model.Hashing;
import main.java.model.IModel;
import main.java.model.Regex;
import main.java.view.IView;
import main.java.view.RegistrationView;

public final class RegistrationController extends Hashing implements IController {
	
	private RegistrationView view;
	private DatabaseController dbc = DatabaseController.getInstance();
	
	public RegistrationController() {
		
		this.view = new RegistrationView();
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
		if (view.getErrorMessage() != null) {
			view.deleteErrorMessage();
		}
		if(checkUsername() && checkEmail() && checkPassword() && checkSecurityQuestion()) {
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
			view.setErrorMessage("Invalid E-Mail!");
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
		
		if (pwIn.length < 6){
			view.setErrorMessage("Password needs at least 6 characters.");
			return false;
		}
		if (!Arrays.equals(pwIn, pwConfirm)) {			
			view.setErrorMessage("Passwords not matching.");
			return false;
		}
		return true;
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
		new LoginController();
		this.view.dispose();
	}
	
	private void registration() {
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
