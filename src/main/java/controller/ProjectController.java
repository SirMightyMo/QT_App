package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	
	public void actionSearchProjects() {
		//System.out.println(filterView.getComboBox().getItemAt(0).toString());
		projectView.filterProjects(projectView.getComboBox().getSelectedItem().toString());
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
		

		
	}
	
}

