package main.java.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import main.java.model.StaticActions;
import main.java.model.TestHourEntryModel;
import main.java.view.TestHourEntryView;

public class TestHourEntryController implements ActionListener {

	TestHourEntryModel model;
	TestHourEntryView view;
	
	@SuppressWarnings("deprecation")
	public TestHourEntryController() {
		this.model = new TestHourEntryModel();
		this.view = new TestHourEntryView(this);
		
		this.model.addObserver(this.view);
		this.view.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		System.out.println("ACTION: " + event.toString());
		
		// Get Database Entries
		if (event.equalsIgnoreCase(StaticActions.ACTION_GET_HOURLIST)) {
			DatabaseController db = new DatabaseController("sa", "");
			db.connect();
			
			ResultSet rs = null;
		
			Statement statement;
			try {
				System.out.println("Trying to query data...");
				statement = db.getDbConnection().createStatement();
				rs = statement.executeQuery("SELECT * FROM hourentries");
				while(rs.next()) { 
					String[] stringArray = {rs.getBigDecimal(1).toString(), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getTimestamp(6).toString(), rs.getTimestamp(7).toString(), rs.getString(8), rs.getBigDecimal(9).toString(), rs.getBigDecimal(10).toString() };
				    this.model.setData(stringArray);		
				 }
				rs.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}
}
