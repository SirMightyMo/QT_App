package test.java.view;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.formdev.flatlaf.FlatDarkLaf;

import main.java.controller.AppMainController;
import main.java.controller.LayoutManager;
import main.java.model.User;
import main.java.view.AppMainView;
import static org.assertj.swing.fixture.Containers.showInFrame;

class NewTest {
	private FrameFixture window;
	AppMainView frame;

//	@Override
//	@BeforeEach
//	protected void onSetUp() {
//		new LayoutManager();
//		FlatDarkLaf.setup();
//		User.setUser(new User(1, "Bob", "bob@msn.com"));
//		frame = GuiActionRunner.execute(() -> new AppMainView(new AppMainController()));
//		// IMPORTANT: note the call to 'robot()'
//		// we must use the Robot from AssertJSwingJUnitTestCase
//		window = new FrameFixture(robot(), frame);
//		window.show(); // shows the frame to test
//	}

	@Test
	void integrationTest() {
//		MyCoolJPanel panel = createPanel(); // create panel in EDT.
//		frameFixture = showInFrame(panel);
	}

}
