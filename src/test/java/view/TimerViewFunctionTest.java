package test.java.view;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.controller.TimerHourController;
import main.java.model.User;
import main.java.view.TimerView;

class TimerViewFunctionTest {

	private TimerView tv;
	
	@BeforeEach
	void init() {
		tv = new TimerView(new TimerHourController());
	}

	@Test
	void testInstance() {
		assertNotNull((tv), "instance of TimerView is null");
	}

	@Test
	void testShowErrorMessageTrue() {
		tv.setErrorVisible(true);
		tv.showErrorMessage("XXX Error Message XXX", 5000);
		System.out.println(tv.getLblErrorMessage().getText());
		assertTrue(tv.isErrorVisible());
	}
	
	@Test
	void testShowErrorMessageFalse() {
		tv.setErrorVisible(false);
		tv.showErrorMessage("XXX Error Message XXX", 5000);
		assertFalse(tv.isErrorVisible());
	}
}
