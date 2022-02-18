package test.java.view;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.view.RegistrationView;

class RegistrationViewGUITest {

	private FrameFixture window;

	@BeforeAll
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@BeforeEach
	public void setUp() {
		FlatDarkLaf.setup();
		RegistrationView frame = GuiActionRunner.execute(() -> new RegistrationView());
		window = new FrameFixture(frame);
		window.show(); // shows the frame to test
	}
	
	@AfterEach
	public void tearDown() {
		// After each test case we close all windows, deallocate Mouse keys etc.
		window.cleanUp();
	}
	
	@Test
	void testRegistrationElements_Visible() {
		window.label("usernameLabel").requireVisible();
		window.label("emailLabel").requireVisible();
		window.label("emailConfirmLabel").requireVisible();
		window.label("passwordLabel").requireVisible();
		window.label("passwordConfirmLabel").requireVisible();
		window.label("securityQuestionLabel").requireVisible();
		window.label("securityAnswerLabel").requireVisible();
		
		window.textBox("usernameInputField").requireVisible();
		window.textBox("passwordInputField").requireVisible();
		window.textBox("emailInputField").requireVisible();
		window.textBox("emailConfirmInputField").requireVisible();
		window.textBox("securityAnswerInputField").requireVisible();
		
		window.button("loginButton").requireVisible();
		window.button("registerButton").requireVisible();
	}
	
	@Test
	void testRegistrationElements_Clickable() {
		window.button("loginButton").click();
		window.button("registerButton").click();
		window.comboBox("securityQuestionPicker").click();
	}
	
	@Test
	void testInsertComment() {
		window.textBox("usernameInputField").requireEmpty();
		window.textBox("usernameInputField").enterText("Testuser");
		window.textBox("passwordInputField").requireEmpty();
		window.textBox("passwordInputField").enterText("Testpw");
		window.textBox("emailInputField").requireEmpty();
		window.textBox("emailInputField").enterText("42@fortytwo.com");
		window.textBox("emailConfirmInputField").requireEmpty();
		window.textBox("emailConfirmInputField").enterText("42@fortytwo.com");
		window.textBox("securityAnswerInputField").requireEmpty();
		window.textBox("securityAnswerInputField").enterText("Pelikan");
	}
	
	@Test
	void testLoginElements_CorrectLabeling() {
		assertTrue(window.label("usernameLabel").text().equals("Username:"));
		assertTrue(window.label("passwordLabel").text().equals("Password:"));
	}
	
	@Test
	void testLoginElements_WrongLabeling() {
		assertFalse(window.label("usernameLabel").text().equals("User"));
		assertFalse(window.label("passwordLabel").text().equals("Pass"));
	}
}
