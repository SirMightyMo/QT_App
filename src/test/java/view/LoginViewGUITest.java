package test.java.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.LayoutManager;
import main.java.view.LoginView;

/**
 * Tests for LoginView
 * @author kevin
 *
 */
class LoginViewGUITest {

	private FrameFixture window;

	@BeforeAll
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@BeforeEach
	public void setUp() {
		FlatDarkLaf.setup();
		new LayoutManager();
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
	void testLoginElements_AllElementsVisible() {
		window.label("usernameLabel").requireVisible();
		window.label("passwordLabel").requireVisible();
		window.textBox("usernameInputField").requireVisible();
		window.textBox("passwordInputField").requireVisible();
		window.button("loginButton").requireVisible();
		window.button("registerButton").requireVisible();
	}
	
	@Test
	void testLoginElements_ButtonsClickable() {
		window.button("loginButton").click();
		window.button("registerButton").click();
	}
	
	@Test
	void testLogin_EnterValidCredentials() {
		window.textBox("usernameInputField").enterText("Bob");
		window.textBox("passwordInputField").enterText("abc");
		window.button("loginButton").click();
	}
	
	@Test
	void testLogin_EnterInvalidCredentials() throws InterruptedException {
		window.textBox("usernameInputField").enterText("Bobo");
		window.textBox("passwordInputField").enterText("abcdef");
		window.button("loginButton").click();
		TimeUnit.SECONDS.sleep(5);
	}
	
	@Test
	void testLoginElements_CorrectLabeling_True() {
		assertTrue(window.label("usernameLabel").text().equals("Benutzername:"));
		assertTrue(window.label("passwordLabel").text().equals("Passwort:"));
	}
	
	@Test
	void testLoginElements_WrongLabeling_True() {
		assertFalse(window.label("usernameLabel").text().equals("User"));
		assertFalse(window.label("passwordLabel").text().equals("Pass"));
	}
}
