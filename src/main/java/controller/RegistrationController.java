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
		view.getLoginButton().addActionListener(e -> this.login());
		view.getRegisterButton().addActionListener(e -> this.registration());
	}

	private boolean inputCheck() {
		if (view.getErrorMessage() != null) {
			view.deleteErrorMessage();
		}
		if (checkUsername() && checkEmail() && checkPassword() && checkSecurityQuestion()) {
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
				view.setErrorMessage("Benutzername bereits vergeben");
				return false;
			}
		} else {
			view.setErrorMessage("Benutzername darf nicht leer sein");
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
			view.setErrorMessage("Ungültige E-Mailadresse");
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

		if (pwIn.length < 6) {
			view.setErrorMessage("Passwort muss mind. 6 Zeichen lang sein");
			return false;
		}
		if (!Arrays.equals(pwIn, pwConfirm)) {
			view.setErrorMessage("Passwörter stimmen nicht überein");
			return false;
		}
		return true;
	}

	private boolean checkSecurityQuestion() {

		String answer = view.getSecurityAnswer();

		if (!answer.isEmpty()) {
			return true;
		} else {
			view.setErrorMessage("Keine Sicherheitsfrage gewählt");
			return false;
		}
	}

	private void login() {
		new LoginController();
		this.view.dispose();
	}
	
	private void registration() {
		String hash = null;
		if (inputCheck()) {
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

			dbc.run("INSERT INTO users(username,password,email,security_question,answer)VALUES("
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

	}

	@Override
	public void removeUpdate(DocumentEvent e) {

	}

	@Override
	public void changedUpdate(DocumentEvent e) {

	}

	@Override
	public IModel getModel() {
		return null;
	}

	@Override
	public IView getView() {
		return null;
	}
}
