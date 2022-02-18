package main.java.controller;

import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.table.TableRowSorter;

import main.java.model.CustomTableModel;
import main.java.model.IModel;
import main.java.model.SessionModel;
import main.java.model.StaticActions;
import main.java.model.User;
import main.java.view.IView;
import main.java.view.SessionView;

public class SessionController implements IController {

	private SessionModel sessionModel;
	private SessionView sessionView;
	private CustomTableModel tableData;
	private DatabaseController db = DatabaseController.getInstance();

	// Constructor
	@SuppressWarnings("deprecation")
	public SessionController() {
		
		this.tableData = new CustomTableModel(new String[] {
				"Datum",
				"Projekt",
				"Leistung",
				"Beschreibung",
				"Start",
				"Ende",
				"Dauer"
			});
		
		queryData();
		this.sessionModel = new SessionModel();
		this.sessionView = new SessionView(this);

		this.sessionModel.addObserver(this.sessionView);

		actionLoadProjects();
		actionLoadServices();
		actionLoadClients();
	}

	public void queryData() {
		ArrayList<Object> result = db.query(
				"SELECT entry_date, project.name, service.name, hour_entry.description, start_time, end_time, time_minutes "
				+ "FROM hour_entry "
				+ "LEFT JOIN service "
				+ "ON hour_entry.s_id = service.s_id "
				+ "LEFT JOIN project "
				+ "ON hour_entry.p_id = project.p_id "
				+ "WHERE u_id = " + User.getUser().getU_id()
				+ " ORDER BY h_id "
				+ "DESC LIMIT 15;");
		Object[][] resultArray = new Object[result.size()][7];
		for (int i = 0; i < result.size(); i++) {
			for (int j = 0; j < 7; j++) {
				ArrayList<Object> row = (ArrayList<Object>) result.get(i);
				String value = row.get(j).toString();
				if (j == 0) {
					value = LocalDate.parse(value)
							.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
				} else if (j == 4 || j == 5) {				
					String[] split = value.split("\\.");
					value = split[0] + ".000";
					value = LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
							.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + " Uhr";
				} else if (j == 6) {
					String hours = (Integer.parseInt(value) / 60)+"";
					String minutes = (Integer.parseInt(value) % 60)+"";
					value = ("00" + hours).substring(hours.length()) + ":" + ("00" + minutes).substring(minutes.length()) + " h";
				}
				resultArray[i][j] = value;
			}
		}
		this.tableData.setData(resultArray);		
	}
	
	public void actionLoadProjects() {
		this.sessionModel.setProjectSet(false);
		this.sessionModel.retrieveProjects();
	}
	
	public void actionLoadServices() {
		this.sessionModel.setServiceSet(false);
		this.sessionModel.retrieveServices();
	}
	
	public void actionLoadClients() {
		this.sessionModel.setClientSet(false);
		this.sessionModel.retrieveClients();
	}
	
	
	public SessionView getSessionView() {
		return sessionView;
	}

	public void actionFilterEntries() {
		String projectFilter = "";
		String clientFilter = "";
		String serviceFilter = "";
		String startFilter = sessionView.getTextFieldFrom().getText();
		String endFilter = sessionView.getTextFieldTo().getText();
		List<RowFilter<Object,Object>> filters = new ArrayList<RowFilter<Object,Object>>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-YYYY");
		
		if (sessionView.getComboBoxProject().getItemCount() > 0) {
			projectFilter = sessionView.getComboBoxProject().getSelectedItem().toString();
			if (!projectFilter.equals("")) {
				filters.add(RowFilter.regexFilter("^" + projectFilter  + "$", 1));
			}
		}
		if (sessionView.getComboBoxClient().getItemCount() > 0) {
			clientFilter = sessionView.getComboBoxClient().getSelectedItem().toString();
			if (!clientFilter.equals("")) {
				filters.add(RowFilter.regexFilter("^" + clientFilter + "$"));
			}
		}
		if (sessionView.getComboBoxService().getItemCount() > 0) {
			serviceFilter = sessionView.getComboBoxService().getSelectedItem().toString();
			if (!serviceFilter.equals("")) {
				filters.add(RowFilter.regexFilter("^" + serviceFilter  + "$", 2));
			}
		}
		if (!startFilter.equals("")) {
			String start = startFilter.split(" ",1)[0].replace(".", "-");
			Date startDate = null;
			try {
				startDate = formatter.parse(start);
				System.out.println("Filter from: " + startDate);
			} catch (ParseException e) {
				System.out.println("Error while parsing Date: " + start);
			}
			if (startDate != null)
				filters.add(RowFilter.dateFilter(ComparisonType.AFTER, startDate, 4));			
		}
		if (!endFilter.equals("")) {
			String end = startFilter.split(" ",1)[0].replace(".", "-");
			Date endDate = null;
			try {
				endDate = formatter.parse(end);
				System.out.println("Filter to: " + endDate);
			} catch (ParseException e) {
				System.out.println("Error while parsing Date: " + end);
			}
			if (endDate != null)
				filters.add(RowFilter.dateFilter(ComparisonType.AFTER, endDate, 5));				
		}
		
		
		
		if (filters.size() > 0) {
			sessionView.getSorter().setRowFilter(RowFilter.andFilter(filters));	
		} else {
			sessionView.getSorter().setRowFilter(null);
		}
		
	}

	public CustomTableModel getTableModel() {
		return tableData;
	}
	
	// ActionListener method
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		System.out.println("ACTION: " + event.toString());

		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_OVERVIEW_LOAD)) {
			actionLoadProjects();
			actionLoadServices();
			actionLoadClients();
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_OVERVIEW_SEARCH)) {
			actionFilterEntries();
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_NEW_SAVE)) {
			
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_OVERVIEW_RESET)) {
			actionLoadProjects();
			actionLoadServices();
			actionLoadClients();
			queryData();
			sessionView.getSorter().setRowFilter(null);
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_OVERVIEW_SET_PROJECT)) {
			this.sessionModel.setProjectSet(true);
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_OVERVIEW_SET_CLIENT)) {
			this.sessionModel.setServiceSet(true);
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_OVERVIEW_SET_SERVICE)) {
			this.sessionModel.setClientSet(true);
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_NEW_SET_PROJECT)) {
			this.sessionModel.setProjectSet(true);
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_NEW_SET_CLIENT)) {
			this.sessionModel.setClientSet(true);
		}
		if (event.equalsIgnoreCase(StaticActions.ACTION_SESSION_NEW_SET_SERVICE)) {
			this.sessionModel.setServiceSet(true);
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
