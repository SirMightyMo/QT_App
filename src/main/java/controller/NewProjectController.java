package main.java.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.event.DocumentEvent;

import main.java.model.IModel;
import main.java.model.NewProjectModel;
import main.java.model.StaticActions;
import main.java.model.User;
import main.java.view.IView;
import main.java.view.NewProjectView;

/**
 * Controlls view and model for small panel on dashboard to
 * create new projects.
 * @author Leander
 *
 */

public class NewProjectController implements IController {

	private NewProjectModel newProjectModel;
	private NewProjectView newProjectView;
	private DatabaseController db = DatabaseController.getInstance();

	// Constructor
	@SuppressWarnings("deprecation")
	public NewProjectController() {
		this.newProjectModel = new NewProjectModel();
		this.newProjectView = new NewProjectView(this);
		this.newProjectModel.addObserver(this.newProjectView);
		this.newProjectModel.retrieveClients();
	}
	
	public NewProjectModel getNewProjectModel() {
		return newProjectModel;
	}

	public void setNewProjectModel(NewProjectModel newProjectModel) {
		this.newProjectModel = newProjectModel;
	}
	
	public NewProjectView getNewProjectView() {
		return newProjectView;
	}

	public void setNewProjectView(NewProjectView newProjectView) {
		this.newProjectView = newProjectView;
	}
	
	/**
	 * Reads user input for new project information and writes data to project table 
	 * as well as to assign_project_user table. 
	 * @author Leander
	 */
	public void actionSaveProject() {
		String projectName = this.newProjectView.getTextFieldProjectname().getText();
		java.sql.Date startDate = getStartDate();
		java.sql.Date endDate = getEndDate();
		boolean active = true;		
		

		int comboBoxIndex = this.newProjectView.getDropDownClient().getSelectedIndex();
		int clientID = (int) newProjectModel.getClientList().get(comboBoxIndex).get(0);

		db.run("INSERT INTO project(name, start_date, end_date, active, c_id) VALUES(" 
		+ "'" + projectName + "'," 
		+ "'" + startDate + "'," 
		+ "'" + endDate + "'," 
		+ "'" + active + "'," 
		+ "'" + clientID + "');");
		
		db.run("INSERT INTO assign_project_user(p_id, u_id) VALUES("
				+ "(SELECT MAX(p_id) FROM project)," 	// get newest projectID
				+ User.getUser().getU_id() + ");");		// get User-ID
		actionPerformed(new ActionEvent(this, 1, StaticActions.ACTION_NPROJECT_RESET));
	}
	
	/**
	 * This method converts the start date String to a sql date format.
	 */
	public Date getStartDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		java.sql.Date startDate = new Date(System.currentTimeMillis());
		java.util.Date inputStartDate;
		try {
			inputStartDate = format.parse(newProjectView.getTxtStartTime().getText().replace(".", "-"));
		} catch (ParseException e) { // start date set to actual date in case nothing was entered
			inputStartDate = new Date(System.currentTimeMillis());
			// e.printStackTrace();
		}
		startDate.setTime(inputStartDate.getTime());
		return startDate;
	}

	/**
	 * This method converts the end date String to a sql date format.
	 */
	public Date getEndDate() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		java.sql.Date endDate = new Date(System.currentTimeMillis());
		java.util.Date inputEndDate;
		try {
			inputEndDate = format.parse(newProjectView.getTxtEndTime().getText().replace(".", "-"));
		} catch (ParseException e) { // end date set to actual date in case nothing was entered
			inputEndDate = new Date(System.currentTimeMillis());
			// e.printStackTrace();
		}
		endDate.setTime(inputEndDate.getTime());
		return endDate;
	}
	
	// ActionListener method
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_NPROJECT_SAVE)) {
			if (inputsValid()) {
				actionSaveProject();				
			}
		}
		
		if (event.equalsIgnoreCase(StaticActions.ACTION_NPROJECT_RESET)) {
			newProjectView.getTextFieldProjectname().setBackground(new Color(70, 73, 75));
			newProjectView.getTxtStartTime().setBackground(new Color(70, 73, 75));
			newProjectView.getTxtEndTime().setBackground(new Color(70, 73, 75));
			newProjectView.getTextFieldProjectname().setText("");
			newProjectView.getTxtStartTime().setText("...");
			newProjectView.getTxtEndTime().setText("...");
			newProjectModel.retrieveClients();
		}

		
	}

	/**
	 * Checks, if inputs were made and if start date lies before end date.
	 * Marks invalid input fields red.
	 */
	private boolean inputsValid() {
		String name = newProjectView.getTextFieldProjectname().getText();
		String start = newProjectView.getTxtStartTime().getText();
		String end = newProjectView.getTxtEndTime().getText();
		
		Date startDate = getStartDate();
		Date endDate = getEndDate();
		boolean startBeforeEnd = (startDate.before(endDate) || startDate.equals(endDate));
		
		if (!name.equals("") && !start.equals("...") && !end.equals("...") && startBeforeEnd) {
			newProjectView.getTextFieldProjectname().setBackground(new Color(70, 73, 75));
			newProjectView.getTxtStartTime().setBackground(new Color(70, 73, 75));
			newProjectView.getTxtEndTime().setBackground(new Color(70, 73, 75));
			return true;
		} else {
			if (name.equals("")) {
				newProjectView.getTextFieldProjectname().setBackground(new Color(175,25,65));
			}
			if (start.equals("...") || !startBeforeEnd) {
				newProjectView.getTxtStartTime().setBackground(new Color(175,25,65));			
						}
			if (end.equals("...") || !startBeforeEnd) {
				newProjectView.getTxtEndTime().setBackground(new Color(175,25,65));
			}
			return false;
		}
	}

	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IView getView() {
		return newProjectView;
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
}
