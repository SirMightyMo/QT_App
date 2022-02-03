package main.java.model;

import java.util.ArrayList;
import java.util.Observable;

import main.java.controller.DatabaseController;

@SuppressWarnings("deprecation")
public class ProjectModel extends Observable implements IModel {

	private ArrayList<ArrayList<Object>> projectList;
	private boolean projectSet;
	private Object[][] projectTable;

	// Constructor
	public ProjectModel() {
		super();
	}

	// Creates Object needed for JTable
	public Object[][] getTableModel() {
		this.projectList = new ArrayList<>();
		DatabaseController db = new DatabaseController("sa", "");
		ArrayList<Object> result = db.query("SELECT * FROM project ;");
		projectTable = new Object[result.size()][7];
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < 7; j++) {
				ArrayList<Object> row = (ArrayList<Object>) result.get(i);
				if (j == 4) {
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
			// TO DO Abfangen von Customer ID

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

	public void retrieveProjects() {
		this.projectList = new ArrayList<>();
		DatabaseController db = new DatabaseController("sa", "");
		ArrayList<Object> result = db.query("SELECT p_id, name FROM project;");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.projectList.add(row);

			// row.forEach(value -> {
			// System.out.println(value);
			// });
		});
		setChanged();
		notifyObservers(this);
	}
}
