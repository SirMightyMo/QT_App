package main.java.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

/**
 * Class for creating custom table models.
 * @author Leander
 *
 */

public class CustomTableModel extends AbstractTableModel implements IModel {

	private static final long serialVersionUID = 1L;

	private TableColumnModel columns;
	private Object[][] data;
	private String[] columnNames;
	private final PropertyChangeSupport support = new PropertyChangeSupport(this);

	public CustomTableModel(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public CustomTableModel(Object[][] data, String[] columnNames) {
		this.data = data;
		this.columnNames = columnNames;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
		fireTableDataChanged();
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		support.removePropertyChangeListener(listener);
	}

	@Override
	public void addTableModelListener(TableModelListener l) {
		listenerList.add(TableModelListener.class, l);
	}

	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	@SuppressWarnings("unchecked")
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
