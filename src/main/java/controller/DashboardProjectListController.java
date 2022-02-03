package main.java.controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.model.CustomTableModel;
import main.java.model.IModel;
import main.java.model.StaticActions;
import main.java.model.User;
import main.java.view.DashboardListView;
import main.java.view.IView;

public class DashboardProjectListController implements IController {

	private DashboardListView view;
	private CustomTableModel tableData;
	private DatabaseController db = DatabaseController.getInstance();
	
	public DashboardProjectListController() {
		this.tableData = new CustomTableModel(new String[] {
				"Datum",
				"Projekt",
				"Start",
				"Ende",
				"Dauer"
			});
			this.view = new DashboardListView(this, tableData);
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
		this.tableData.setData(resultArray);
	}
		
	public DashboardListView getView() {
		return (DashboardListView) view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		
		// When hour entry is being saved, retrieve new list data
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
	public CustomTableModel getModel() {
		return tableData;
	}


}
