package test.java;

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
	void testShowErrorMessage_ValidParameters() {
		tv.showErrorMessage("Wo soll dieses Label zu sehen sein?", 100000);
		System.out.println(tv.isErrorVisible());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
