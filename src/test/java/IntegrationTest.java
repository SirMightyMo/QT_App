package test.java;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.controller.AppMainController;
import main.java.controller.DatabaseController;
import main.java.controller.LayoutManager;
import main.java.model.User;
import main.java.view.AppMainView;

import java.awt.Dimension;
import java.awt.Robot;
import java.util.concurrent.TimeUnit;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;

/**
 * This class tests the application as a whole. For testing purposes<br>
 * a robot is created, that simulates user inputs in different scenarios.<br>
 * A full integration test takes approximately 70 seconds. <b>Do not move<br>
 * the mouse or press any keys while the test is running.</b>
 * 
 * @author kevin
 */
class IntegrationTest {
	private FrameFixture window;

	@BeforeAll
	public static void setUpOnce() {
		FailOnThreadViolationRepaintManager.install();
		DatabaseController.getInstance().initializeDB();
	}

	@BeforeEach
	public void setUp() {
		new LayoutManager();
		User.setUser(new User(1, "Bob", "bob@msn.com"));
		AppMainView frame = GuiActionRunner.execute(() -> new AppMainController().getAppMainView());
		window = new FrameFixture(frame);
		window.show(new Dimension(1850, 1000)); // shows the frame to test in needed Dimension (see AppMainView Class)
		frame.setFrameToLeftUpperCorner();
	}

	@AfterEach
	public void tearDown() {
		// After each test case we close all windows, deallocate Mouse keys etc.
		window.cleanUp();
	}

	/**
	 * Main test method that calls all test methods in a certain order.
	 * @author kevin
	 */
	@Test
	void integrationTest() {
		testAllElementsVisible(); // Test if all elements are visible
		testTimer_StartStopSave();
		testTimer_StartStopReset();
		testDashboard_CreateProjectFromDashboard();
		testProjects_CreateProjectFromProjects();
		testProjects_CreateNewClient_ValidInputs();
		testProjects_CreateNewClient_InvalidZip();
		testProjects_CreateNewService_ValidInputs();
		testProjects_CreateNewClient_InvalidZip();
	}
	
	/**
	 * Following steps happen in this scenario:<br>
	 * 1 Enter a service name<br>
	 * 2 Enter invalid internal rate<br>
	 * 3 Enter external rate<br>
	 * 4 Save<br>
	 * 5 Enter valid internal rate<br>
	 * @author kevin
	 */
	void testProjects_CreateNewService_InvalidInternalRate() {
		window.button("btnMenuProjects").click();
		window.tabbedPane().selectTab(3);
		window.textBox("textFieldNewService").enterText("Optimierung");
		window.textBox("textFieldInternalRate").enterText("ABC");
		window.textBox("textFieldExternalRate").enterText("150€");
		window.button("btnSaveService").click();
		window.textBox("textFieldInternalRate").setText("");
		window.textBox("textFieldInternalRate").enterText("20");
	}
	
	/**
	 * Following steps happen in this scenario:<br>
	 * 1 Enter a service name<br>
	 * 2 Enter internal rate<br>
	 * 3 Enter external rate<br>
	 * 4 Save
	 * 5 
	 * @author kevin
	 */
	void testProjects_CreateNewService_ValidInputs() {
		window.button("btnMenuProjects").click();
		window.tabbedPane().selectTab(3);
		window.textBox("textFieldNewService").enterText("Database Engineering");
		window.textBox("textFieldInternalRate").enterText("50");
		window.textBox("textFieldExternalRate").enterText("150€");
		window.button("btnSaveService").click();
	}

	/**
	 * Following steps happen in this scenario:<br>
	 * 1 Enter a client name<br>
	 * 2 Enter a contact<br>
	 * 3 Enter phone<br>
	 * 4 Enter mobile<br>
	 * 5 Enter street<br>
	 * 6 Enter house number<br>
	 * 7 Enter invalid zip<br>
	 * 8 Enter city<br>
	 * 9 Enter country<br>
	 * 10 Save<br>
	 * 11 Enter valid zip<br>
	 * 12 Save
	 * @author kevin
	 */
	void testProjects_CreateNewClient_InvalidZip() {
		window.button("btnMenuProjects").click();
		window.tabbedPane().selectTab(2);
		window.textBox("textFieldClientName").setText("");
		window.textBox("textFieldContact").setText("");
		window.textBox("textFieldTelephone").setText("");
		window.textBox("textFieldMobile").setText("");
		window.textBox("textFieldStreet").setText("");
		window.textBox("textFieldHouseNumber").setText("");
		window.textBox("textFieldZip").setText("");
		window.textBox("textFieldCity").setText("");
		window.textBox("textFieldCountry").setText("");
		window.textBox("textFieldClientName").click().enterText("The New Company");
		window.textBox("textFieldContact").enterText("Mister X");
		window.textBox("textFieldTelephone").enterText("0405554444");
		window.textBox("textFieldMobile").enterText("0172444889998");
		window.textBox("textFieldStreet").enterText("Anonyme Straße");
		window.textBox("textFieldHouseNumber").enterText("100A");
		window.textBox("textFieldZip").enterText("D-12345");
		window.textBox("textFieldCity").enterText("Anonymous Town");
		window.textBox("textFieldCountry").enterText("Deutschland");
		window.button("btnSaveCustomer").click();
		window.textBox("textFieldZip").setText("").enterText("12345");
		window.button("btnSaveCustomer").click();
	}

	/**
	 * Following steps happen in this scenario:<br>
	 * 1 Enter a client name<br>
	 * 2 Enter a contact<br>
	 * 3 Enter phone<br>
	 * 4 Enter mobile<br>
	 * 5 Enter street<br>
	 * 6 Enter house number<br>
	 * 7 Enter zip<br>
	 * 8 Enter city<br>
	 * 9 Enter country<br>
	 * 10 Save
	 * 
	 * @author kevin
	 */
	void testProjects_CreateNewClient_ValidInputs() {
		window.button("btnMenuProjects").click();
		window.tabbedPane().selectTab(2);
		window.textBox("textFieldClientName").enterText("Anonymous Company");
		window.textBox("textFieldContact").enterText("Mister X");
		window.textBox("textFieldTelephone").enterText("0405554444");
		window.textBox("textFieldMobile").enterText("0172444889998");
		window.textBox("textFieldStreet").enterText("Anonyme Straße");
		window.textBox("textFieldHouseNumber").enterText("100A");
		window.textBox("textFieldZip").enterText("12345");
		window.textBox("textFieldCity").enterText("Anonymous Town");
		window.textBox("textFieldCountry").enterText("Deutschland");
		window.button("btnSaveCustomer").click();
	}

	/**
	 * Following steps happen in this scenario:<br>
	 * 1 Enter a project name<br>
	 * 2 Select a client<br>
	 * 3 Set the start date<br>
	 * 4 Set the end date<br>
	 * 5 Set project active<br>
	 * 6 Save new project<br>
	 * 
	 * @author kevin
	 */
	void testProjects_CreateProjectFromProjects() {
		window.button("btnMenuProjects").click();
		window.tabbedPane().selectTab(1);
		window.textBox("textFieldProjectName").enterText("Bytecode Velocity");
		window.comboBox("textFieldClient").selectItem(1);
		window.textBox("textFieldStartDate").setText("03-03-2020");
		window.textBox("textFieldEndDate").setText("04-04-2030");
		window.button("btnSaveProject").click();
	}

	/**
	 * Following steps happen in this scenario:<br>
	 * 1 Enter a project name<br>
	 * 2 Select a client<br>
	 * 3 Set the start date<br>
	 * 4 Set the end date<br>
	 * 5 Save new project<br>
	 * 
	 * @author kevin
	 */
	void testDashboard_CreateProjectFromDashboard() {
		window.button("btnMenuDashboard").click();
		window.moveToFront();
		window.textBox("textFieldProjectname").enterText("Runtime Terror");
		window.comboBox("dropDownClient").selectItem("Apple");
		window.textBox("txtStartTimeNPV").setText("01-01-2021");
		window.textBox("txtEndTimeNPV").setText("02-02-2022");
		window.button("btnSaveNPV").click();
	}

	/**
	 * Following steps happen in this scenario:<br>
	 * 1 Select a service<br>
	 * 2 Select a project<br>
	 * 3 Start timer<br>
	 * 4 Stop timer<br>
	 * 5 Reset
	 * 
	 * @author kevin
	 */
	void testTimer_StartStopReset() {
		window.button("btnMenuDashboard").click();
		window.comboBox("serviceDropdown").click().selectItem("Frontend Design");
		window.comboBox("comboBoxProject").click().selectItem("Projekt 2");
		window.button("btnStart").click();
		window.button("btnStop").click();
		window.button("btnReset").click();
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
	 * 
	 * @author kevin
	 */
	void testTimer_StartStopSave() {
		window.button("btnMenuDashboard").click();
		window.comboBox("comboBoxProject").click().selectItem("Projekt 1");
		window.comboBox("serviceDropdown").click().selectItem("Backend Stuff");
		window.button("btnStart").click();
		window.button("btnPause").click();
		window.button("btnStop").click();
		window.textBox("textFieldCommentTimerView").enterText("Conquering the world!");
		window.button("btnSaveTimerView").click();
	}

	/**
	 * Tests if all elements are visible.
	 * 
	 * @author kevin
	 */
	void testAllElementsVisible() {
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
		window.tabbedPane().selectTab(0);
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
		window.textBox("textFieldProjectName").requireVisible();
		window.comboBox("textFieldClient").requireVisible();
		window.textBox("textFieldStartDate").requireVisible();
		window.textBox("textFieldEndDate").requireVisible();
		window.button("btnSaveProject").requireVisible();

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