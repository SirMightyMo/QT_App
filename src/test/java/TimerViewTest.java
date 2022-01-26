package test.java;

import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.TimeUnit;
import javax.swing.UIManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.formdev.flatlaf.FlatDarkLaf;
import main.java.controller.TimerHourController;
import main.java.view.TimerView;

import static org.assertj.core.api.Assertions.*;

class TimerViewTest {

	TimerView tv;

	@BeforeEach
	void init() {
		FlatDarkLaf.setup();
		tv = new TimerView(new TimerHourController());
		UIManager.put("Button.arc", 999);
		UIManager.put("Component.arc", 999);
		UIManager.put("ProgressBar.arc", 999);
		UIManager.put("TextComponent.arc", 999);
	}

	@Test
	void testInstance() {
		assertNotNull((tv), "instance of TimerView is null");
	}

	@Test
	void testShowErrorMessage_ValidParameters() {
		tv.showErrorMessage("Wo soll dieses Label zu sehen sein?", 100000);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testAssertJ() {
		// test if assertJ works
		assertThat(tv.getTitle().equals("QualityTime"));
	}
}
