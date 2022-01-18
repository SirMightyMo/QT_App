import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegistrationController implements ActionListener{
	
	private RegistrationView view;
	private RegistrationModel model;
	
	public RegistrationController() {
		
		this.view = new RegistrationView();
		this.model = new RegistrationModel();
		 
		this.init();
		
		
	}
	
	
	private void init() {
		view.getLoginButton().addActionListener(e -> 
			new LoginController()			
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
		if(checkPassword(view.getPasswordInput(), view.getPasswordConfirmInput()) && )
		
		return true;
	}
	
	
	
	
	private boolean checkPassword(char[] passwordInput, char[] passwordConfirm) {
		
		
		if (Arrays.equals(passwordInput, passwordConfirm)) {			
			return true;
		}
		else{
			view.setErrorMessage("Passwords are not equal");
			System.out.println("wrong psw");
			return false;
		}
	}
	
	
	private void login() {
		System.out.println("sie werden eingeloggt");
	}
	
	private void registration() {
		System.out.println("sie werden weitergeleitet");
		if(inputCheck());
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
