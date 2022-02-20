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
import main.java.view.LostPwView;

public final class LostPwController extends Hashing implements IController {

		private LostPwView view;
		private DatabaseController dbc = DatabaseController.getInstance();

		public LostPwController() {
			this.view = new LostPwView();
			this.init();
		}
		
		private void init() {
			view.getBackToLoginButton().addActionListener(e -> this.backToLogin());
			view.getBackToRegisterButton().addActionListener(e -> this.backToRegistration());
			view.getCheckButton().addActionListener(e -> this.check());
		}

		
		private void check() {
			if (view.getErrorMessage() != null) {
						view.deleteErrorMessage();
			}
			
			System.out.println("Checking");
			
			String emailInput = this.view.getEmailInput();
			
			if(validateEmail(emailInput)) {
				
				String sql = "SELECT security_question,answer FROM users WHERE email='" + emailInput + "';"; 
				ArrayList<Object> result = dbc.query(sql, true);
				
				if (result.isEmpty()) {
					view.setErrorMessage("E-Mail ist nicht registriert!");
				} else {
					
					
					
					String securityQuestionInput = this.view.getSelectedSecurityQuestion();
					String answerInput = this.view.getAnswerInput();
					
					// read result from database
					String securityQuestionDb = (String) result.get(0);
					String answerDb = (String) result.get(1);
					
					System.out.println("selected: " + securityQuestionInput + " from db: " + securityQuestionDb);
					System.out.println("answered: "+ answerInput + " from db: " + answerDb);
					
					if (securityQuestionInput.equals(securityQuestionDb)) {
						if(answerInput.equals(answerDb)) {
							
							char[] newPassword = this.view.getNewPasswordInput();
							char[] newPasswordConfirm = this.view.getNewPasswordConfirmInput();
							
							if (newPassword.length >= 6) 

								if (Arrays.equals(newPassword, newPasswordConfirm)) {
									
									this.resetPassword(newPassword);
								
								}
								else {
									view.setErrorMessage("Passwörter stimmen nicht überein");
								}
							else {
								view.setErrorMessage("Passwort muss mind. 6 Zeichen lang sein");
							}
							
						}
						else {
							view.setErrorMessage("Falsche Antwort!");
						}
					} else {
						view.setErrorMessage("Dies ist nicht ihre Sicherheitsfrage!");
					}
				}
				
				
			}
			else {
				this.view.setErrorMessage("Ungültige E-Mail!");
			}
			
			
		}
		
		private void resetPassword(char[] newPw) {
			if (view.getErrorMessage() != null) {
				view.deleteErrorMessage();
			}
			
			
			String hash = null;
			
			String email = view.getEmailInput();
			char[] newPassword = newPw;
			try {
				hash = generatePasswordHash(newPassword);
			} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
					e.printStackTrace();
			}

			dbc.run("UPDATE users SET password='" + hash +"' WHERE email='" + email + "';");
			backToLogin(); // Change View to Login
			
			
			
			System.out.println("Ihr Passwort wurde zurückgesetzt!");
			
		};

		private boolean validateEmail(String email) {
			Matcher matcher = Regex.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
			return matcher.find();
		};

		private void backToRegistration() {
			new RegistrationController();
			this.view.dispose();
		};

		private void backToLogin() {
			new LoginController();
			this.view.dispose();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		};

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		};

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		};

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		};

		@Override
		public IModel getModel() {
			return null;
		};

		@Override
		public IView getView() {
			return this.view;
		}

}
