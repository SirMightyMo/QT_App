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

import main.java.controller.ProjectController;
import main.java.model.User;
import main.java.view.ProjectView;

class ProjectViewGUITest {

	private FrameFixture window;

	@BeforeAll
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@BeforeEach
	public void setUp() {
		FlatDarkLaf.setup();
		ProjectView frame = GuiActionRunner.execute(() -> new ProjectView(new ProjectController()));
		window = new FrameFixture(frame);
		window.show(); // shows the frame to test
	}

	@AfterEach
	public void tearDown() {
		// After each test case we close all windows, deallocate Mouse keys etc.
		window.cleanUp();
	}

	@Test
	void testElementsProjectOverview_Visible() {
		window.label("lblProjects").requireVisible();
		window.label("lblService").requireVisible();
		window.label("lblTimeFrame").requireVisible();
		window.label("lblFrom").requireVisible();
		window.label("lblTo").requireVisible();
		window.label("lblHeadTitel").requireVisible();
		
		window.panel("panel_project_overview").requireVisible();
		
		window.comboBox("comboBoxProject").requireVisible();
		window.comboBox("comboBoxService").requireVisible();
		
		window.textBox("textFieldFrom").requireVisible();
		window.textBox("textFieldTo").requireVisible();
		
		window.button("btnLoadProjects").requireVisible();
		window.button("btnSearchButton").requireVisible();
		window.button("btnSetStartDate_1").requireVisible();
		window.button("btnSetStartDate_1_1").requireVisible();
	}
	
	@Test
	void testElementsNewProject_Visible() {
		window.tabbedPane("tabbedPane_panel_new_project").click();
		window.label("lblNewProjectHead").requireVisible();
		window.label("lblNewLabel_18").requireVisible();
		window.label("lblNewLabel_19").requireVisible();
		window.label("lblNewLabel_20").requireVisible();
		window.label("lblNewLabel_21").requireVisible();
		window.label("lblHeadTitel").requireVisible();
		
//		window.panel("panel_new_project").requireVisible();
//		window.panel("panel_input_form").requireVisible();
//		
//		window.checkBox("chckbxActive").requireVisible();
//				
//		window.textBox("textFieldProjectName").requireVisible();
//		window.textBox("textFieldClient").requireVisible();
//		window.textBox("textFieldStartDate").requireVisible();
//		window.textBox("textFieldEndDate").requireVisible();
//		
//		window.button("btnSetStartDate").requireVisible();
//		window.button("btnSetEndDate").requireVisible();
//		window.button("btnSaveProject").requireVisible();
//		window.button("btnResetProjects").requireVisible();
	}
}
