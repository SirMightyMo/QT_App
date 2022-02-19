package test.java.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.java.controller.AppMainController;
import main.java.controller.LayoutManager;
import main.java.model.User;
import main.java.view.AppMainView;

import java.awt.Dimension;

import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;

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
	void testAllElementsVisible_True() {

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
	}

//	@Test
//	void testAllButtons() {
//		window.button("btnStart").click();
//		window.button("btnPause").click();
//		window.button("btnStop").click();
//		window.button("btnSave").click();
//		window.button("btnReset").click();		window.button("btnMenuSessions").click();

//		window.button("btnLoadProjects").click();

	// window.button("btnMenuProjects").click();
//	window.tabbedPane().selectTab(3);
//	window.textBox("textFieldNewService").enterText("abc");
//	window.button("btnMenuProjects").click();
//	window.button("btnMenuSessions").click();
//	window.button("btnMenuDashboard").click();
//	window.button("btnStart").click();
//	window.button("btnStop").click();
//	window.button("btnSave").click();
//	window.button("btnMenuProjects").click();
//	window.tabbedPane().selectTab(1);
//	window.tabbedPane().selectTab(2);
//	window.tabbedPane().selectTab(3);
//	window.tabbedPane().selectTab(0);
//	window.button("btnMenuSessions").click();
//	window.tabbedPane().selectTab(1);
//	window.tabbedPane().selectTab(0);

	// window.button("btnStart").requireVisible();
//	window.button("btnMenuDashboard").click();
//	window.button("btnStart").requireVisible();

//	window.tabbedPane().selectTab(1);

//	try {
//		TimeUnit.SECONDS.sleep(100);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//	window.
//	window.button("btnStart").click();
//	window.textBox("textFieldCommentTimerView").requireVisible();
//	window.textBox("textFieldCommentTimerView").enterText("IntegTest");
//	window.button("btnStop").click();
//	window.button("btnSaveTimerView").click();
//	FrameFixture mainFrame = findFrame("main").using(robot());
//	window.comboBox("comboBoxProject").click();

//	}

//	@Test
//	void testInsertComment() {
//		window.textBox("textFieldComment").requireVisible();
//		window.textBox("textFieldComment").enterText("Comment");
//	}

//	@Test
//	void testProjectPanel() {
//		window.panel("projectPanel").isEnabled();
//	}

//	@Test
//	void testAllGuiElements() {
//		JButtonFixture btnStart = window.button("btnStart");
//		assertNotNull(btnStart);
//		window.button("btnStart").click();
//
//		JButtonFixture btnPause = window.button("btnPause");
//		assertNotNull(btnPause);
//		window.button("btnPause").click();
//
//		JButtonFixture btnStop = window.button("btnStop");
//		assertNotNull(btnStop);
//		window.button("btnStop").click();
//
//		JButtonFixture btnSave = window.button("btnSave");
//		assertNotNull(btnSave);
//		window.button("btnSave").click();
//
//		JButtonFixture btnReset = window.button("btnReset");
//		assertNotNull(btnReset);
//		window.button("btnReset").click();
//
//		JButtonFixture btnLoadProjects = window.button("btnLoadProjects");
//		assertNotNull(btnLoadProjects);
//		window.button("btnLoadProjects").click();
//
//		JPanelFixture projectPanel = window.panel("projectPanel");
//		assertNotNull(projectPanel);
//		window.panel("projectPanel").isEnabled();
//	}
}
