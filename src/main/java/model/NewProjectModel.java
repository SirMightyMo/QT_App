package main.java.model;

import java.util.ArrayList;
import java.util.Observable;

import main.java.controller.DatabaseController;

@SuppressWarnings("deprecation")
public class NewProjectModel extends Observable implements IModel {

	private ArrayList<ArrayList<Object>> clientList;

	private DatabaseController db = DatabaseController.getInstance();

	/**
	 * Constructor
	 */
	public NewProjectModel() {
		super();
	}

	public ArrayList<ArrayList<Object>> getClientList() {
		return clientList;
	}

	public void setClientList(ArrayList<ArrayList<Object>> clientList) {
		this.clientList = clientList;
	}

	/**
	 * Retrieves client information from database and saves it 
	 * to an ArrayList. This List is thought to be used for 
	 * setting data of a JComboBox (dropdown) in a view.
	 */
	public void retrieveClients() {
		this.clientList = new ArrayList<>();
		ArrayList<Object> result = db.query("SELECT c_id, company FROM customer;");
		result.forEach(entry -> {
			ArrayList<Object> row = (ArrayList<Object>) entry;
			this.clientList.add(row);
		});
		setChanged();
		notifyObservers(this);
	}

}
