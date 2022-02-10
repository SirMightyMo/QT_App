package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;

import main.java.model.IModel;
import main.java.model.ProjectModel;
import main.java.model.StaticActions;
import main.java.model.User;
import main.java.view.IView;
import main.java.view.ProjectView;
import main.java.view.ProjectView;

public class ProjectController implements IController {

	private ProjectModel projectModel;
	private ProjectView projectView;


	private DatabaseController db = DatabaseController.getInstance();

	// Constructor
	@SuppressWarnings("deprecation")
	public ProjectController() {
		
		this.projectModel = new ProjectModel();
		this.projectView = new ProjectView(this);

		this.projectModel.addObserver(this.projectView);
		projectModel.retrieveProjects();

		actionLoadProjects();
	}

	public void actionLoadProjects() {
		this.projectModel.setProjectSet(false);
		this.projectModel.retrieveProjects();

	}
	public ProjectView getProjectView() {
		return projectView;
	}
	public Object[][] getTableModel() {
		// TODO Auto-generated method stub
		return projectModel.getTableModel();
	}

	public void actionResetProjects() {
		projectView.updateTable(this);
	}

	public void actionSearchProjects() {
		// System.out.println(projectView.getComboBox().getItemAt(0).toString());
		projectView.filterProjects(projectView.getComboBox().getSelectedItem().toString());
	}

	public void actionSaveProject() {
		String projectName;
		Date startDate;
		Date endDate;
		boolean active;
		int customerID;

		projectName = projectView.getNewProjectName();
		startDate = projectView.getNewStartDate();
		endDate = projectView.getNewEndDate();
		active = projectView.getNewProjectStat();
		customerID = projectView.getClientID();

		db.insert("INSERT INTO project(name,start_date,end_date,active,c_id) VALUES(" + "'" + projectName + "'," + "'"
				+ startDate + "'," + "'" + endDate + "'," + "'" + active + "'," + "'" + customerID + "')");
		projectView.updateTable(this);
		projectView.setTab(0);

	}

	// ActionListener method
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		System.out.println("ACTION: " + event.toString()); // For debugging

		if (event.equalsIgnoreCase(StaticActions.ACTION_LOAD_PROJECTS)) {
			actionLoadProjects();
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SEARCH_PROJECTS)) {
			actionSearchProjects();
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SAVE_PROJECT)) {
			actionSaveProject();
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_RESET_PROJECTS)) {
			actionResetProjects();
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IView getView() {
		// TODO Auto-generated method stub
		return null;
	}
}
