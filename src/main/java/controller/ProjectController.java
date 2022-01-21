package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import main.java.model.ProjectModel;
import main.java.model.StaticActions;
import main.java.view.ProjectView;
import main.java.view.ProjectView;

public class ProjectController implements ActionListener {
	
	private ProjectModel projectModel;
	private ProjectView projectView;

	//Constructor
	@SuppressWarnings("deprecation")
	public ProjectController() {
		this.projectModel = new ProjectModel();
		this.projectView = new ProjectView(this);
		
		this.projectModel.addObserver(this.projectView);
		this.projectView.setVisible(true);
		
		projectModel.retrieveProjects();
		
		actionLoadProjects();
		
	}

	public void actionLoadProjects() {
		this.projectModel.setProjectSet(false);
		this.projectModel.retrieveProjects();	
		
	}

	public Object[][] getTableModel() {
		// TODO Auto-generated method stub
		return projectModel.getTableModel();
	}
	
	public void actionResetProjects() {
		projectView.updateTable(this);
	}
	
	public void actionSearchProjects() {
		//System.out.println(projectView.getComboBox().getItemAt(0).toString());
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
		
		DatabaseController db = new DatabaseController("sa", "");
		db.insert("INSERT INTO project(name,start_date,end_date,active,c_id) VALUES("
				+ "'" + projectName + "',"
				+ "'" + startDate + "',"
				+ "'" + endDate + "',"
				+ "'" + active + "',"
				+ "'" + customerID + "')");
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
	
}
