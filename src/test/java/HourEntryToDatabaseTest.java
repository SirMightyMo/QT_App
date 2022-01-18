/*package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import main.java.controller.DatabaseController;
import main.java.controller.TimerHourController;
import main.java.model.HourEntry;
import main.java.model.TimerModel;

class HourEntryToDatabaseTest {

	DatabaseController db;
	TimerHourController thc;
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Before
	void setUp() {
		db = new DatabaseController("sa", "");
		thc = new TimerHourController();
		thc.createHourEntry();
	}
	
	@Test
	void testHourEntryInstanceVSDatabaseValues() {
		thc.actionStartTimer();
		thc.actionStopTimer();
		
		thc.getHourEntry().setDate("2022-01-10");
		thc.getHourEntry().setStartTime(LocalDateTime.of(2022,Month.JANUARY,10,9,30,0,0));
		thc.getHourEntry().setEndTime(LocalDateTime.of(2022,Month.JANUARY,10,18,0,0,0));
		thc.getHourEntry().setPauseStart(LocalDateTime.of(2022,Month.JANUARY,10,12,30,0,0));
		thc.getHourEntry().setPauseEnd(LocalDateTime.of(2022,Month.JANUARY,10,13,0,0,0));
		thc.getHourEntry().setComment("Ein Testkommentar Nr.");
		thc.getHourEntry().setProjectID(1);

		thc.actionSaveTimer();
		System.out.println("Test");
		
		ArrayList<Object> projectList = new ArrayList<>();
		ArrayList<Object> result = db.query("SELECT * FROM hour_entry");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			projectList.add(row);
			
			row.forEach(value -> {
				assertEquals(thc.getHourEntry().getDate(), value);
			});
		});
		
		
	}

}
*/