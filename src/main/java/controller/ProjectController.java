package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import main.java.model.Project;
import main.java.model.ProjectModel;
import main.java.model.StaticActions;
import main.java.view.FilterView;
import main.java.view.ProjectView;

public class ProjectController implements ActionListener {
	
	private ProjectModel projectModel;
	//private ProjectView projectView;
	//alternative
	private FilterView filterView;
	private Project project;

	//Constructor
	@SuppressWarnings("deprecation")
	public ProjectController() {
		this.projectModel = new ProjectModel();
		//this.projectView = new ProjectView(this);
		this.filterView = new FilterView(this);
		
		this.projectModel.addObserver(this.filterView);
		//this.projectView.setVisible(true);
		this.filterView.setVisible(true);
		
		projectModel.retrieveProjects();
		
		actionLoadProjects();
		
	}

	
	
	public void actionLoadProjects() {
		this.projectModel.setProjectSet(false);
		this.projectModel.retrieveProjects();
		
		// if project was not set yet, select last used project (highest h_id)
		if (!this.projectModel.isProjectSet()) {
			
			DatabaseController db = new DatabaseController("sa", "");
			ArrayList<Object> result = db.query("SELECT p_id FROM hour_entry WHERE h_id = (SELECT MAX(h_id) FROM hour_entry);"); // TODO: Check, if querying for specific user is necessary or if this table already contains hour entries of user only
			if (!result.isEmpty()) {
				// find out projectListIndex by looking for p_id in ArrayList projectList of timerModel
				int projectListIndex = 0; // initialize variable for list index in timerView
				int latestHourEntryProjectID = (int) ((ArrayList<Object>) result.get(0)).get(0); // get actual projectID of latest project used
				
				// iterator through project list of timerModel for every project
				for (ArrayList<Object> project : this.projectModel.getProjectList()) {
									//System.out.println(this.timerModel.getProjectList().indexOf(project));
									//System.out.println(project);
					// if one of the projectIDs equal the projectID of the latest project used, condition is met
					if ((int) project.get(0) == latestHourEntryProjectID) {
						projectListIndex = this.projectModel.getProjectList().indexOf(project);
						break;
					}
				}
				// set selected item to latest project
				this.filterView.getComboBox().setSelectedIndex(projectListIndex);
			}
		}
		
	}


	public Object[][] getTableModel() {
		// TODO Auto-generated method stub
		return projectModel.getTableModel();
	}
	
	// ActionListener method
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		System.out.println("ACTION: " + event.toString()); // For debugging
		
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_LOAD_PROJECTS)) {
			actionLoadProjects();
		}
		

		
	}
	
}

