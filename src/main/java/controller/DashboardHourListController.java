package main.java.controller;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.event.DocumentEvent;

import main.java.model.IModel;
import main.java.model.StaticActions;
import main.java.view.DashboardListView;


public class DashboardHourListController implements IController {

	private DashboardListView view;
	private String[] projectnames;
	private Object[][] data;
	private String[] columnNames = {
			"Datum",
			"Projekt",
			"Start",
			"Ende",
			"Dauer"
	};
	
	public DashboardHourListController() {
		queryData();
		this.view = new DashboardListView(this, data, columnNames);

	}
	
	public void queryData() {
		DatabaseController db = new DatabaseController("sa", "");
		ArrayList<Object> result = db.query(
				"SELECT entry_date, "
				+ "project.name, "
				+ "start_time, "
				+ "end_time, "
				+ "time_minutes "
				+ "FROM hour_entry "
				+ "LEFT JOIN project "
				+ "ON hour_entry.p_id = project.p_id "
				+ "ORDER BY h_id DESC LIMIT 15;");
		Object[][] resultArray = new Object[result.size()][5];
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < 5; j++) {
				ArrayList<Object> row = (ArrayList<Object>) result.get(i);
				String value = row.get(j).toString();
				if (j == 0) {
					value = LocalDate.parse(value)
							.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
				} else if (j == 2 || j == 3) {				
					String[] split = value.split("\\.");
					value = split[0] + ".000";
					value = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
							.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
				} else if (j == 4) {
					String hours = (Integer.parseInt(value) / 60)+"";
					String minutes = (Integer.parseInt(value) % 60)+"";
					value = ("00" + hours).substring(hours.length()) + ":" + ("00" + minutes).substring(minutes.length());
				}
				resultArray[i][j] = value;
			}
		}
		this.data = resultArray;
	}
	
	String formatDateString(String date) {
		return "";
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
			System.out.println("HourList refreshed.");
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
	

}
