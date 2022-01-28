package main.java.controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.model.StaticActions;
import main.java.view.DashboardListView;

public class DashboardProjectListController implements ControllerInterface {

	private DashboardListView view;
	private Object[][] data;
	private String[] columnNames = {
			"Name",
			"Start",
			"Ende",
			"Status",
			"Arbeiszeit"
	};
	
	public DashboardProjectListController() {
		queryData();
		this.view = new DashboardListView(this, data, columnNames);
	}
	
	public void queryData() {
		DatabaseController db = new DatabaseController("sa", "");
		ArrayList<Object> result = db.query(
				"SELECT name, "
				+ "start_date, "
				+ "end_date, "
				+ "active, "
				+ "p_id " // TODO: duration
				+ "FROM project ORDER BY p_id DESC LIMIT 15");
		Object[][] resultArray = new Object[result.size()][5];
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < 5; j++) {
				ArrayList<Object> row = (ArrayList<Object>) result.get(i);
				String value = row.get(j).toString();
				if (j == 1 || j == 2) {
					value = LocalDate.parse(value)
							.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
				} else if (j == 3) {
					if (value.equalsIgnoreCase("true")) {
						value = "begonnen";
					} else {
						value = "abgeschlossen";
					}
				}
				resultArray[i][j] = value;
			}
		}
		this.data = resultArray;
	}
	
	public Object[][] getData() {
		return data;
	}
	
	public DashboardListView getView() {
		return view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		
		// When hour entry is being saved, retrieve new list data
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_SAVE)) {
			queryData();
			System.out.println("ProjectList refreshed.");
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
	

}
