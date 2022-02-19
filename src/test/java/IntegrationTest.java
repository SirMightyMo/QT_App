package test.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.controller.AppMainController;
import main.java.controller.LayoutManager;
import main.java.model.User;
import main.java.view.AppMainView;

import java.awt.Dimension;
import java.util.concurrent.TimeUnit;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;

/**
 * This class tests the application as a whole. For testing purposes<br>
 * a robot is created, that simulates user inputs in different scenarios.<br>
 * @author kevin
 */

class IntegrationTest {
	private FrameFixture window;

	@BeforeAll
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
	}

	@BeforeEach
	public void setUp() {
		new LayoutManager();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		AppMainView frame = GuiActionRunner.execute(() -> new AppMainController().getAppMainView());
		window = new FrameFixture(frame);
		window.show(new Dimension(1850, 1080)); // shows the frame to test in needed Dimension (see AppMainView Class)
	}

	@AfterEach
	public void tearDown() {
		// After each test case we close all windows, deallocate Mouse keys etc.
		window.cleanUp();
	}

	@Test
	void integrationTest() {
		testAllElementsVisible_true(); // Test if all elements are visible
		testTimer_scenario1();
	}
	
	/**
	 * Following steps happen in this scenario:<br>
	 * 1 Select a project<br>
	 * 2 Select a service<br>
	 * 3 Start timer<br>
	 * 4 Pause timer<br>
	 * 5 Stop timer<br>
	 * 6 Enter a comment<br>
	 * 7 Save entry 
	 * @author kevin
	 */
	private void testTimer_scenario1() {
		window.button("btnMenuDashboard").click();
		window.comboBox("comboBoxProject").click().selectItem("Projekt 1");
		window.comboBox("serviceDropdown").click().selectItem("Backend Stuff");
		window.button("btnStart").click();
		window.button("btnPause").click();
		window.button("btnStop").click();
		window.textBox("textFieldCommentTimerView").enterText("Conquering the world!");
		window.button("btnSaveTimerView").click();
	}

	void testAllElementsVisible_true() {
		//////////////////////////////////////////////////////
		///////////////////// Dashboard /////////////////////
		////////////////////////////////////////////////////

		// Main Menu
		window.label("navLabelNavigation").requireVisible();
		window.button("btnMenuDashboard").requireVisible();
		window.button("btnMenuProjects").requireVisible();
		window.button("btnMenuSessions").requireVisible();
		window.label("lblEinstellungen").requireVisible();
		window.button("btnMenuAccount").requireVisible();
		window.textBox("textFieldCommentTimerView").requireVisible();
		window.button("btnMenuLogout").requireVisible();

		// Timer
		window.label("lblProjectTV").requireVisible();
		window.comboBox("comboBoxProject").requireVisible();
		window.button("btnLoadProjectsTV").requireVisible();
		window.label("lblService").requireVisible();
		window.comboBox("serviceDropdown").requireVisible();
		window.button("btnLoadServices").requireVisible();
		window.label("durationLabel").requireVisible();
		window.button("btnStart").requireVisible();
		window.button("btnPause").requireVisible();
		window.button("btnStop").requireVisible();
		window.label("lblDatum").requireVisible();
		window.textBox("txtDateInput").requireVisible();
		window.button("btnDatePicker").requireVisible();
		window.label("lblFrom").requireVisible();
		window.textBox("txtStartTime").requireVisible();
		window.label("lblTo").requireVisible();
		window.textBox("txtEndTime").requireVisible();
		window.label("lblPauseDuration").requireVisible();
		window.textBox("textPauseDuration").requireVisible();
		window.label("lblComment").requireVisible();
		window.textBox("textFieldCommentTimerView").requireVisible();
		window.button("btnReset").requireVisible();
		window.button("btnSaveTimerView").requireVisible();

		// DashboardView (Aktuelle Projekte & Letzte Sitzungen)
		window.label("lblCurrentProjects").requireVisible();
		window.button("btnProjectShowMore").requireVisible();
		window.panel("dashboardProjectsPane").requireVisible();
		window.label("lblLastSessions").requireVisible();
		window.panel("dashboardLastSessionsPane").requireVisible();
		window.button("btnSessionShowMore").requireVisible();

		// Neues Projekt anlegen
		window.label("lblProject").requireVisible();
		window.label("lblProjectnameNPV").requireVisible();
		window.textBox("textFieldProjectname").requireVisible();
		window.label("lblClientNPV").requireVisible();
		window.comboBox("dropDownClient").requireVisible();
		window.label("lblFromNPV").requireVisible();
		window.textBox("txtStartTimeNPV").requireVisible();
		window.label("lblToNPV").requireVisible();
		window.textBox("txtEndTimeNPV").requireVisible();
		window.label("lblCurrentProjects").requireVisible();
		window.button("btnSaveNPV").requireVisible();
		window.button("btnLoadProjectsNPV").requireVisible();
		
		//////////////////////////////////////////////////////
		///////////////////// Projekte //////////////////////
		////////////////////////////////////////////////////
		
		// Projektübersicht (Tab 1)
		window.button("btnMenuProjects").click();
		window.label("lblProjects").requireVisible();
		window.comboBox("comboBoxProject").requireVisible();
		window.button("btnLoadProjects").requireVisible();
		window.label("lblService").requireVisible();
		window.comboBox("comboBoxService").requireVisible();
		window.button("btnSearchButton").requireVisible();
		window.label("lblFrom").requireVisible();
		window.label("lblTo").requireVisible();
		window.textBox("textFieldFrom").requireVisible();
		window.textBox("textFieldTo").requireVisible();
		window.button("btnSetStartDate_1").requireVisible();
		window.button("btnSetStartDate_1").requireVisible();
		window.panel("panel_project_overview").requireVisible();
		
		// Neues Projekt (Tab 2)
		window.tabbedPane().selectTab(1);
		window.label("lblNewProjectHead").requireVisible();
		window.label("lblNewLabel_18").requireVisible();
		window.label("lblNewLabel_19").requireVisible();
		window.label("lblNewLabel_20").requireVisible();
		window.label("lblNewLabel_21").requireVisible();
		window.panel("panel_new_project").requireVisible();
		window.panel("panel_input_form").requireVisible();
		window.checkBox("chckbxActive").requireVisible();
		window.textBox("textFieldProjectName").requireVisible();
		window.textBox("textFieldClient").requireVisible();
		window.textBox("textFieldStartDate").requireVisible();
		window.textBox("textFieldEndDate").requireVisible();
		
		// Neuer Kunde (Tab 3)
		window.tabbedPane().selectTab(2);
		window.label("lblNewCustomer").requireVisible();
		window.label("lblCustomerName").requireVisible();
		window.label("lblContactPerson").requireVisible();
		window.label("lblTelephone").requireVisible();
		window.label("lblMobile").requireVisible();
		window.label("lblStreet").requireVisible();
		window.label("lblHouseNumber").requireVisible();
		window.label("lblZip").requireVisible();
		window.label("lblCity").requireVisible();
		window.label("lblCountry").requireVisible();
		window.textBox("textFieldClientName").requireVisible();
		window.textBox("textFieldContact").requireVisible();
		window.textBox("textFieldTelephone").requireVisible();
		window.textBox("textFieldMobile").requireVisible();
		window.textBox("textFieldStreet").requireVisible();
		window.textBox("textFieldHouseNumber").requireVisible();
		window.textBox("textFieldZip").requireVisible();
		window.textBox("textFieldCity").requireVisible();
		window.textBox("textFieldCountry").requireVisible();
		window.button("btnSaveCustomer").requireVisible();
		
		// Neue Leistung (Tab 4)
		window.tabbedPane().selectTab(3);
		window.label("lblServiceHead").requireVisible();
		window.label("lblNewService").requireVisible();
		window.label("lblInternalRate").requireVisible();
		window.label("lblExternalRate").requireVisible();
		window.textBox("textFieldNewService").requireVisible();
		window.textBox("textFieldInternalRate").requireVisible();
		window.textBox("textFieldExternalRate").requireVisible();
		window.button("btnSaveService").requireVisible();
		
		//////////////////////////////////////////////////////
		///////////////////// Sitzungen /////////////////////
		////////////////////////////////////////////////////
		
		// Tab 1
		window.button("btnMenuSessions").click();
		window.label("lblHeadTitel").requireVisible();
		window.scrollPane("scrollPaneTable").requireVisible();
		window.table("table").requireVisible();
		window.label("lblProjects").requireVisible();
		window.comboBox("comboBoxProject").requireVisible();
		window.button("btnLoadProjects").requireVisible();
		window.label("lblService").requireVisible();
		window.comboBox("comboBoxService").requireVisible();
		window.label("lblTimeFrame").requireVisible();
		window.button("btnApplyFilter").requireVisible();
		window.textBox("textFieldFrom").requireVisible();
		window.label("lblFrom").requireVisible();
		window.label("lblTo").requireVisible();
		window.textBox("textFieldTo").requireVisible();
		window.button("btnSetStartDate").requireVisible();
		window.button("btnSetEndDate").requireVisible();
		
		// Tab 2
		window.tabbedPane().selectTab(1);
		window.label("lblNewHourEntryHeadline").requireVisible();
		window.label("lblProjectNZ").requireVisible();
		window.comboBox("dropDownProjectName").requireVisible();
		window.label("lblServiceName").requireVisible();
		window.label("lblDateNZ").requireVisible();
		window.label("lblStartNZ").requireVisible();
		window.comboBox("dropDownService").requireVisible();
		window.textBox("textFieldDateNZ").requireVisible();
		window.button("btnSetDate").requireVisible();
		window.textBox("textFieldStartNZ").requireVisible();
		window.button("btnSaveHourEntry").requireVisible();
		window.button("btnResetInputFields").requireVisible();
		window.label("lblEnd").requireVisible();
		window.textBox("textFieldEnd").requireVisible();
		window.label("lblPause").requireVisible();
		window.textBox("textFieldPause").requireVisible();
		window.label("lblCommentNZ").requireVisible();
		window.textBox("textFieldCommentNZ").requireVisible();
		
		//////////////////////////////////////////////////////
		///////////////////// Account ///////////////////////
		////////////////////////////////////////////////////
		window.button("btnMenuAccount").click();
		window.label("lblNewLabel").requireVisible();
		window.label("lblNewLabel_1_2_2").requireVisible();
		window.label("lblNewLabel_2").requireVisible();
		window.checkBox("chckbxNewCheckBox_1").requireVisible();
		window.checkBox("chckbxNewCheckBox").requireVisible();
		window.checkBox("chckbxNewCheckBox_4").requireVisible();
		window.checkBox("chckbxNewCheckBox_2").requireVisible();
		window.label("lblNewLabel_1_2_1_3").requireVisible();
		window.label("lblNewLabel_1_2_1_1_3").requireVisible();
		window.textBox("textField_7").requireVisible();
		window.textBox("textField_8").requireVisible();
		window.label("lblNewLabel_1_2_1_1_1_1").requireVisible();
		window.label("lblNewLabel_1_2_1_1_2_2").requireVisible();
		window.textBox("textField_9").requireVisible();
		window.label("lblNewLabel_1_2_1_2").requireVisible();
		window.label("lblNewLabel_1_2_1_1_2_1").requireVisible();
		window.comboBox("comboBox").requireVisible();
		window.textBox("textField_6").requireVisible();
		window.label("lblNewLabel_1_2_1_1_2_1_1").requireVisible();
		window.textBox("textFieldAV").requireVisible();
		window.label("lblNewLabel_1AV").requireVisible();
		window.label("lblNewLabel_1_1").requireVisible();
		window.textBox("textField_2AV").requireVisible();
		window.textBox("textField_1AV").requireVisible();
		window.label("lblNewLabel_1_1_1").requireVisible();
		window.label("lblNewLabel_1_2").requireVisible();
		window.button("btnNewButton").requireVisible();
		
	}

}