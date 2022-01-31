package main.java.view;


import java.awt.Color;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import main.java.controller.IController;
import main.java.controller.LayoutManager;
import main.java.model.CustomTableModel;

@SuppressWarnings("deprecation")
public class DashboardListView implements IView {

	private IController controller;
	private JTable table;
	private JScrollPane scrollPane;
	
	public DashboardListView(IController controller, CustomTableModel tableModel) {
				
		this.controller = controller;
		this.table = new JTable(tableModel);
		table.getColumnModel();
		
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

	public JTable getTable() {
		return table;
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}
