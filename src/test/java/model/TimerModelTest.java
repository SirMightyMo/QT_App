package test.java.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.LayoutManager;
import main.java.model.TimerModel;
import main.java.model.User;

/**
 * Tests for TimerModel
 * @author kevin
 *
 */
class TimerModelTest {
	
	TimerModel tm;

	@BeforeEach
	void setUp() throws Exception {
		new LayoutManager();
		FlatDarkLaf.setup();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		tm = new TimerModel();
	}

	@Test
	void testTimerToString_Valid_True() {
		
		//seconds <10
		tm.setTimerHours(1);
		tm.setTimerMinutes(20);
		tm.setTimerSeconds(5);
		assertEquals(tm.timerToString(true), "01:20:05");
		
		System.out.println();
		
		//seconds > 10
		tm.setTimerHours(1);
		tm.setTimerMinutes(20);
		tm.setTimerSeconds(30);
		
		assertEquals(tm.timerToString(true), "01:20:30");
		
		//minutes <10
		tm.setTimerHours(1);
		tm.setTimerMinutes(2);
		tm.setTimerSeconds(5);
		assertEquals(tm.timerToString(false), "01:02");
		
		System.out.println();
		
		//minutes >10
		tm.setTimerHours(1);
		tm.setTimerMinutes(40);
		tm.setTimerSeconds(30);
		
		assertEquals(tm.timerToString(true), "01:40:30");
		assertEquals(tm.timerToString(false), "01:40");

		//hours <10
		tm.setTimerHours(1);
		tm.setTimerMinutes(2);
		tm.setTimerSeconds(5);
		assertEquals(tm.timerToString(false), "01:02");
		
		System.out.println();
		
		//hours >10
		tm.setTimerHours(12);
		tm.setTimerMinutes(40);
		tm.setTimerSeconds(30);
		
		assertEquals(tm.timerToString(true), "12:40:30");
	}

}
