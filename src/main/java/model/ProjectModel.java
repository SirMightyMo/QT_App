package main.java.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Observable;

import main.java.controller.DatabaseController;

@SuppressWarnings("deprecation")
public class ProjectModel extends Observable implements IModel {

	private ArrayList<ArrayList<Object>> projectList;
	private ArrayList<ArrayList<Object>> clientList;
	private boolean projectSet;
	private boolean clientSet;

	private Object[][] projectTable;
	private DatabaseController db = DatabaseController.getInstance();

	// Constructor
	public ProjectModel() {
		super();
	}
	/**
	 * This method retrieves all projects from the database and formats it so the date can be used as a JTable
	 * 
	 * @author Mo
	 */
	// Creates Object needed for JTable
	public Object[][] getTableModel() {
		this.projectList = new ArrayList<>();
		ArrayList<Object> result = db.query(
				"SELECT project.p_id, name, start_date, end_date,active, c_id FROM project LEFT JOIN assign_project_user ON project.p_id = assign_project_user.p_id WHERE u_id = "
						+ User.getUser().getU_id() + ";");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		projectTable = new Object[result.size()][6];
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < 6; j++) {
				ArrayList<Object> row = (ArrayList<Object>) result.get(i);
				String value = row.get(j).toString();
				if (j == 0) {
					value = String.format("%1$5s", value).replace(' ', '0');
					projectTable[i][j] = value;
				}
				else if (j == 2 || j == 3) {
					java.util.Date date = null;
					try {
						date = formatter.parse(value);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					projectTable[i][j] = date;
				}
				else if (j == 4) {
					if (row.get(j).toString() == "true") {
						projectTable[i][j] = "begonnen";
					} else
						projectTable[i][j] = "abgeschlossen";
				} else if (j == 5) {
					int customerID = (int) row.get(j);
					ArrayList<Object> resultCID = db.query("SELECT * FROM customer WHERE c_id ='" + customerID + "' ;");
					ArrayList<Object> row2 = (ArrayList<Object>) resultCID.get(0);
					projectTable[i][j] = row2.get(1).toString();

				} else
					projectTable[i][j] = row.get(j);
			}
		}

		return projectTable;
	}

	public boolean isProjectSet() {
		return projectSet;
	}

	public void setProjectSet(boolean projectSet) {
		this.projectSet = projectSet;
	}

	public ArrayList<ArrayList<Object>> getProjectList() {
		return projectList;
	}

	public void setProjectList(ArrayList<ArrayList<Object>> projectList) {
		this.projectList = projectList;
	}
	public ArrayList<ArrayList<Object>> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<ArrayList<Object>> clientList) {
		this.clientList = clientList;
	}
	/**
	 * This method retrieves all projects the current user is assigned to from the
	 * database and adds them to an ArrayList for using them in a ComboBox
	 * (Dropdown).
	 * @author Leander
	 */
	public void retrieveProjects() {
		this.projectList = new ArrayList<>();
		ArrayList<Object> result = db.query(
				"SELECT project.p_id, name FROM project LEFT JOIN assign_project_user ON project.p_id = assign_project_user.p_id WHERE u_id = "
						+ User.getUser().getU_id() + ";");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.projectList.add(row);
			//System.out.println(row);
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

	public void setClientSet(boolean clientSet) {
		this.clientSet = clientSet;
	}
	
	public boolean isClientSet() {
		return clientSet;
	}


}
