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
		return projectModel.getTableModel();
	}

	public void actionResetProjects() {
		projectView.updateTable(this);
	}

	public void actionSearchProjects() {
		if (projectView.getComboBox().getItemCount() > 0) {
			projectView.filterProjects(projectView.getComboBox().getSelectedItem().toString());			
		}
	}

	/**
	 * This method saves a new project to the database
	 * @author Mo
	 */
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

		db.insert("INSERT INTO project(name, start_date, end_date, active, c_id) VALUES(" 
		+ "'" + projectName + "'," 
		+ "'" + startDate + "'," 
		+ "'" + endDate + "'," 
		+ "'" + active + "'," 
		+ "'" + customerID + "');");
		
		db.insert("INSERT INTO assign_project_user(p_id, u_id) VALUES("
				+ "(SELECT MAX(p_id) FROM project)," 	// get newest projectID
				+ User.getUser().getU_id() + ");");		// get User-ID
		
		projectModel.retrieveProjects();
		projectView.updateTable(this);
		projectView.setTab(0);

	}
	
	/**
	 * This method saves a new client to the database
	 * @author kevin
	 */
	public void actionSaveClient() {
		
		String company = projectView.getTextFieldClientName();
		String contact = projectView.getTextFieldContact();
		String phone = projectView.getTextFieldTelephone();
		String mobile = projectView.getTextFieldMobile();
		String street = projectView.getTextFieldStreet();
		String houseNumber = projectView.getTextFieldHouseNumber();
		String city = projectView.getTextFieldCity();
		String country = projectView.getTextFieldCountry();
		String zip = projectView.getTextFieldZip();
		
		projectView.setLblErrorVisible(false);
		
		try {
			if (Integer.parseInt(zip) > 99999 || Integer.parseInt(zip) < 0) {
				projectView.showErrorMessage(projectView.getLblErrorClient(), "PLZ bitte im Format 00000 bis 99999 eingeben", 5000);
				return;
			}
		} catch (NumberFormatException e) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "PLZ bitte als Zahl im Format 00000 bis 99999 eingeben", 5000);
			e.printStackTrace();
			return;
		}
		
		if (company.length() > 50) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "Firmenname zu lang, max. 50 Zeichen", 5000);
			return;
		}
		
		if (contact.length() > 50) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "Ansprechpartner zu lang, max. 50 Zeichen", 5000);
			return;
		}
		
		if (phone.length() > 30) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "Telefon zu lang, max. 30 Zeichen", 5000);
			return;
		}
		
		if (mobile.length() > 30) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "Handy zu lang, max. 30 Zeichen", 5000);
			return;
		}
		
		if (street.length() > 50) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "Handy zu lang, max. 50 Zeichen", 5000);
			return;
		}
		
		if (houseNumber.length() > 30) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "Hausnummer zu lang, max. 20 Zeichen", 5000);
			return;
		}
		
		if (city.length() > 30) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "Ort zu lang, max. 50 Zeichen", 5000);
			return;
		}
		
		if (country.length() > 30) {
			projectView.showErrorMessage(projectView.getLblErrorClient(), "Land zu lang, max. 50 Zeichen", 5000);
			return;
		}
				
		db.insert("INSERT INTO customer(company, contact, phone, mobile, street, house_number, zip, city, country) VALUES(" 
		+ "'" + company + "'," 
		+ "'" + contact + "'," 
		+ "'" + phone + "'," 
		+ "'" + mobile + "'," 
		+ "'" + street + "'," 
		+ "'" + houseNumber + "'," 
		+ "'" + zip + "'," 
		+ "'" + city + "'," 
		+ "'" + country + "');");
	}
	
	/**
	 * This method saves a new service to the database
	 * @author kevin
	 */
	public void actionSaveService() {
		
		String service;
		double internal_rate;
		double external_rate;
		
		try {
			internal_rate = Double.parseDouble(projectView.getTextFieldInternalRate().replace(',', '.'));
			external_rate = Double.parseDouble(projectView.getTextFieldExternalRate().replace(',', '.'));
		} catch (NumberFormatException e) {
			projectView.showErrorMessage(projectView.getLblErrorService(), "Interner- und externer Satz muss eine Zahl sein", 5000);
			e.printStackTrace();
			return;
		}
		
		service = projectView.getTextFieldNewService();
		
		if (service.length() > 255) {
			projectView.showErrorMessage(projectView.getLblErrorService(), "Servicename zu lang, max. 255 Zeichen", 5000);
			return;
		}
		

		db.insert("INSERT INTO service(name, internal_rate, external_rate) VALUES(" 
		+ "'" + service + "'," 
		+ "'" + internal_rate + "'," 
		+ "'" + external_rate + "');");		
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
		if (event.equalsIgnoreCase(StaticActions.ACTION_SAVE_CUSTOMER)) {
			actionSaveClient();
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SAVE_SERVICE)) {
			actionSaveService();
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
