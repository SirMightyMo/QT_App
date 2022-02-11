package main.java.controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.event.DocumentEvent;

import main.java.model.CustomTableModel;
import main.java.model.StaticActions;
import main.java.model.User;
import main.java.view.DashboardListView;


public class DashboardHourListController implements IController {

	private DashboardListView view;
	private CustomTableModel tableData;
	private DatabaseController db = DatabaseController.getInstance();
	
	
	public DashboardHourListController() {
		this.tableData = new CustomTableModel(new String[] {
			"Datum",
			"Projekt",
			"Start",
			"Ende",
			"Dauer"
		});
		queryData();
		this.view = new DashboardListView(this, tableData); // Give view tabledata for creating JTable
		tableData.addTableModelListener(this.view.getTable()); // add JTable as listener for data changes
		int columnCount = view.getTable().getColumnModel().getColumnCount(); // get columncount for following for-loop
		for (int i = 0; i < columnCount; i++) {
			view.getTable().getColumnModel().getColumn(i).setHeaderValue(tableData.getColumnNames()[i]); // set headers manually, since columnames dont refresh?
		}
	}
	
	public void queryData() {
		ArrayList<Object> result = db.query(
				"SELECT entry_date, "
				+ "project.name, "
				+ "start_time, "
				+ "end_time, "
				+ "time_minutes "
				+ "FROM hour_entry "
				+ "LEFT JOIN project "
				+ "ON hour_entry.p_id = project.p_id "
				+ "WHERE u_id = " + User.getUser().getU_id()
				+ " ORDER BY h_id DESC LIMIT 15;");
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
		this.tableData.setData(resultArray);
	}
	
	
	public DashboardListView getView() {
		return (DashboardListView) view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		
		// When hour entry is being saved, retrieve new list data after one second
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_SAVE)) {
			Timer timer = new Timer();
			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					queryData();
				}
			};
			timer.schedule(task, 1000);
			
			
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		
	}

	@Override
	public CustomTableModel getModel() {
		return tableData;
	}
	

}
