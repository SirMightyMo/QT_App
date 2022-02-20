package main.java.controller;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableCellRenderer;

import main.java.model.CustomTableModel;
import main.java.model.StaticActions;
import main.java.model.User;
import main.java.view.DashboardListView;

public class DashboardProjectListController implements IController {

	private DashboardListView view;
	private CustomTableModel tableData;
	private DatabaseController db = DatabaseController.getInstance();
	Runnable runQueryData = () -> queryData();
	
	public DashboardProjectListController() {
		this.tableData = new CustomTableModel(new String[] {
				"ID",
				"Projekt",
				"Kunde",
				"Start",
				"Ende",
				"Status"
		});
		
		this.view = new DashboardListView(this, tableData);
		queryData();
		this.view = new DashboardListView(this, tableData); // Give view tabledata for creating JTable
		tableData.addTableModelListener(this.view.getTable()); // add JTable as listener for data changes
		int columnCount = view.getTable().getColumnModel().getColumnCount(); // get columncount for following for-loop
		for (int i = 0; i < columnCount; i++) {
			view.getTable().getColumnModel().getColumn(i).setHeaderValue(tableData.getColumnNames()[i]); // set headers manually, since columnames dont refresh?
		}
		// Set column/cell properties (here, because one view-class is used for different views)
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		view.getTable().setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		view.getTable().getColumnModel().getColumn(0).setPreferredWidth(50);
		view.getTable().getColumnModel().getColumn(1).setPreferredWidth(240);
		view.getTable().getColumnModel().getColumn(2).setPreferredWidth(220);
		view.getTable().getColumnModel().getColumn(3).setPreferredWidth(80);
		view.getTable().getColumnModel().getColumn(4).setPreferredWidth(80);
		view.getTable().getColumnModel().getColumn(5).setPreferredWidth(155);
		view.getTable().getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		view.getTable().getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		view.getTable().getColumnModel().getColumn(2).setCellRenderer(leftRenderer);
		view.getTable().getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		view.getTable().getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		view.getTable().getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
	}
	
	/**
	 * Queries the project data of the last 15 projects entered in db (descending p_id).
	 * Table 'project' is joined with 'assign_project_user' to query projects assigned to logged in 
	 * user only. It also joins the table 'customer' to get the company names of the client referenced
	 * by its c_id.
	 * Data is then set as tabledata.
	 * 
	 * @author Leander
	 */
	public void queryData() {
		ArrayList<Object> result = db.query(
				"SELECT project.p_id, name, customer.company, start_date, end_date, active "
				+ "FROM project "
				+ "LEFT JOIN assign_project_user "
				+ "ON project.p_id = assign_project_user.p_id "
				+ "LEFT JOIN customer "
				+ "ON project.c_id = customer.c_id "
				+ "WHERE u_id = " + User.getUser().getU_id()
				+ " ORDER BY project.p_id "
				+ "DESC LIMIT 15;");
		Object[][] resultArray = new Object[result.size()][6];
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < 6; j++) {
				ArrayList<Object> row = (ArrayList<Object>) result.get(i);
				String value = row.get(j).toString();
				if (j == 0) {
					value = String.format("%1$5s", value).replace(' ', '0');
				}
				if (j == 3 || j == 4) {
					value = LocalDate.parse(value)
							.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
				} else if (j == 5) {
					if (value.equalsIgnoreCase("true")) {
						value = "aktiv";
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
		if (event.equalsIgnoreCase(StaticActions.ACTION_TIMER_SAVE) || event.equalsIgnoreCase(StaticActions.ACTION_NPROJECT_SAVE)) {
			ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
			executorService.schedule(runQueryData, 1, TimeUnit.SECONDS);
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
