package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.view.LoginView;

class LoginViewGUITest {

	private FrameFixture window;

	@BeforeAll
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@BeforeEach
	public void setUp() {
		FlatDarkLaf.setup();
		LoginView frame = GuiActionRunner.execute(() -> new LoginView());
		window = new FrameFixture(frame);
		window.show(); // shows the frame to test
	}
	
	@AfterEach
	public void tearDown() {
		// After each test case we close all windows, deallocate Mouse keys etc.
		window.cleanUp();
	}
	
	@Test
	void testLoginElements_Visible() {
		window.label("usernameLabel").requireVisible();
		window.label("passwordLabel").requireVisible();
		window.label("passwordLabel").requireVisible();
		window.label("passwordLabel").requireVisible();
		window.textBox("usernameInputField").requireVisible();
		window.textBox("passwordInputField").requireVisible();
		window.button("loginButton").requireVisible();
		window.button("registerButton").requireVisible();
	}
	
	@Test
	void testLoginElements_Clickable() {
		window.button("loginButton").click();
		window.button("registerButton").click();
	}
	
	@Test
	void testInsertComment() {
		window.textBox("usernameInputField").enterText("Testuser");
		window.textBox("passwordInputField").enterText("abc");
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
