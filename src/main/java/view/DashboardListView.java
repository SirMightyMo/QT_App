package main.java.view;


import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.java.controller.IController;
import main.java.controller.LayoutManager;

@SuppressWarnings("deprecation")
public class DashboardListView implements IView {

	private IController controller;
	private JScrollPane scrollPane;
	private JTable table;
	
	public DashboardListView(IController controller, Object[][] data, String[] columnNames) {
				
		this.controller = controller;
		
		table = new JTable(data, columnNames);
		table.setFillsViewportHeight(true);
		table.setFont(LayoutManager.getFont("dinNeuzeitGrotesk_regular").deriveFont(16.0f));
		table.setBackground(new Color(31,32,33));
		table.setShowGrid(false);
		table.setShowHorizontalLines(true);
		table.setDefaultEditor(Object.class, null);
		table.setCellSelectionEnabled(false);
		table.getTableHeader().setForeground(Color.ORANGE);
		table.getTableHeader().setFont(LayoutManager.getFont("dinNeuzeitGrotesk_regular").deriveFont(18.0f));
		table.getTableHeader().setBackground(new Color(31,32,33));
		table.getTableHeader().setReorderingAllowed(false);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
	}

	
	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	
	public void setTableAndRefresh(JTable table) {
		this.table = table;
		scrollPane = new JScrollPane(table);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
