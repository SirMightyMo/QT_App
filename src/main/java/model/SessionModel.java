package main.java.model;

import java.util.ArrayList;
import java.util.Observable;

import main.java.controller.DatabaseController;

@SuppressWarnings("deprecation")
public class SessionModel extends Observable implements IModel {

	private ArrayList<ArrayList<Object>> projectList;
	private ArrayList<ArrayList<Object>> serviceList;
	private ArrayList<ArrayList<Object>> clientList;
	private boolean projectSet;
	private boolean serviceSet;
	private boolean clientSet;
	
	private ArrayList<ArrayList<Object>> projectListNewEntry;
	private ArrayList<ArrayList<Object>> serviceListNewEntry;
	private boolean projectSetNewEntry;
	private boolean serviceSetNewEntry;

	private DatabaseController db = DatabaseController.getInstance();

	// Constructor
	public SessionModel() {
		super();
	}

	public boolean isProjectSet() {
		return projectSet;
	}

	public void setProjectSet(boolean projectSet) {
		this.projectSet = projectSet;
	}

	public boolean isServiceSet() {
		return serviceSet;
	}

	public void setServiceSet(boolean serviceSet) {
		this.serviceSet = serviceSet;
	}

	public boolean isClientSet() {
		return clientSet;
	}

	public void setClientSet(boolean clientSet) {
		this.clientSet = clientSet;
	}

	public ArrayList<ArrayList<Object>> getProjectList() {
		return projectList;
	}

	public void setProjectList(ArrayList<ArrayList<Object>> projectList) {
		this.projectList = projectList;
	}

	public ArrayList<ArrayList<Object>> getServiceList() {
		return serviceList;
	}

	public void setServiceList(ArrayList<ArrayList<Object>> serviceList) {
		this.serviceList = serviceList;
	}

	public ArrayList<ArrayList<Object>> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<ArrayList<Object>> clientList) {
		this.clientList = clientList;
	}
	
	// Second Tab (New Entry)
	public ArrayList<ArrayList<Object>> getProjectListNewEntry() {
		return projectListNewEntry;
	}

	public void setProjectListNewEntry(ArrayList<ArrayList<Object>> projectListNewEntry) {
		this.projectListNewEntry = projectListNewEntry;
	}

	public ArrayList<ArrayList<Object>> getServiceListNewEntry() {
		return serviceListNewEntry;
	}

	public void setServiceListNewEntry(ArrayList<ArrayList<Object>> serviceListNewEntry) {
		this.serviceListNewEntry = serviceListNewEntry;
	}
	
	public boolean isProjectNewEntrySet() {
		return projectSetNewEntry;
	}

	public void setProjectNewEntrySet(boolean projectSetNewEntry) {
		this.projectSetNewEntry = projectSetNewEntry;
	}

	public boolean isServiceNewEntrySet() {
		return serviceSetNewEntry;
	}

	public void setServiceNewEntrySet(boolean serviceSetNewEntry) {
		this.serviceSetNewEntry = serviceSetNewEntry;
	}

	/**
	 * This method retrieves all projects the current user is assigned to from the
	 * database and adds them to an ArrayList for using them in a ComboBox
	 * (Dropdown).
	 * @author Leander
	 */
	public void retrieveProjects() {
		this.projectList = new ArrayList<>();
		ArrayList<Object> result = db.query("SELECT project.p_id, name FROM project "
				+ "LEFT JOIN assign_project_user ON project.p_id = assign_project_user.p_id " + "WHERE u_id = "
				+ User.getUser().getU_id() + ";");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.projectList.add(row);
		});
		setChanged();
		notifyObservers(this);
	}

	/**
	 * This method retrieves all services and adds them to an ArrayList for using
	 * them in a ComboBox (Dropdown).
	 * @author Leander
	 */
	public void retrieveServices() {
		this.serviceList = new ArrayList<>();
		ArrayList<Object> result = db.query("SELECT s_id, name FROM service;");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.serviceList.add(row);
		});
		setChanged();
		notifyObservers(this);
	}

	/**
	 * This method retrieves all clients the current user has written hour entries
	 * for and adds them to an ArrayList for using them in a ComboBox (Dropdown).
	 * @author Leander
	 */
	public void retrieveClients() {
		this.clientList = new ArrayList<>();
		ArrayList<Object> result = db.query(
				"SELECT customer.c_id, company FROM customer " + "LEFT JOIN project ON project.c_id = customer.c_id "
						+ "LEFT JOIN assign_project_user ON assign_project_user.p_id = project.p_id "
						+ "WHERE assign_project_user.u_id = " + User.getUser().getU_id() + " GROUP BY customer.c_id;");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.clientList.add(row);
		});
		setChanged();
		notifyObservers(this);
	}
	
	/**
	 * This method retrieves all projects the current user is assigned to from the
	 * database and adds them to an ArrayList for using them in a ComboBox
	 * (Dropdown).
	 * @author Leander
	 */
	public void retrieveProjectsNewEntry() {
		this.projectListNewEntry = new ArrayList<>();
		ArrayList<Object> result = db.query("SELECT project.p_id, name FROM project "
				+ "LEFT JOIN assign_project_user ON project.p_id = assign_project_user.p_id " + "WHERE u_id = "
				+ User.getUser().getU_id() + ";");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.projectListNewEntry.add(row);
		});
		setChanged();
		notifyObservers(this);
	}

	/**
	 * This method retrieves all services and adds them to an ArrayList for using
	 * them in a ComboBox (Dropdown).
	 * @author Leander
	 */
	public void retrieveServicesNewEntry() {
		this.serviceListNewEntry = new ArrayList<>();
		ArrayList<Object> result = db.query("SELECT s_id, name FROM service;");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.serviceListNewEntry.add(row);
		});
		setChanged();
		notifyObservers(this);
	}
}
