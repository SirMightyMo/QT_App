package main.java.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import main.java.controller.TestHourEntryController;
import main.java.controller.TimerHourController;
import main.java.model.HourEntry;
import main.java.model.StaticActions;
import main.java.model.TestHourEntryModel;
import main.java.model.TimerModel;

import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Color;

import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.BoxLayout;

@SuppressWarnings("deprecation")
public class TestHourEntryView extends JFrame implements Observer {

	JFrame frame;
	DefaultTableModel tableModel;

	public TestHourEntryView(TestHourEntryController controller) {
		frame = new JFrame();

		String columnNames[] = { "ID", "DATE", "USER", "PROJECT", "SERVICE", "START", "END", "COMMENT", "DURATION",
				"PAUSE" }; // id, date, user, project, service, start, end, comment, durationInSeconds,
							// pauseInSeconds
		tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		table.setBounds(30, 40, 200, 300);
		JScrollPane scrollPane = new JScrollPane(table);
		frame.getContentPane().add(scrollPane);

		JButton btnNewButton = new JButton("Aktualisieren");
		btnNewButton.addActionListener(controller);
		btnNewButton.setActionCommand(StaticActions.ACTION_GET_HOURLIST); // Defined in Class StaticActions
		scrollPane.setRowHeaderView(btnNewButton);

		frame.setSize(1000, 400);
		frame.setVisible(true);

	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("Trying to update TestHourEntryView");
		System.out.println(arg.getClass());
		if (arg instanceof TestHourEntryModel) {
			tableModel.addRow(((TestHourEntryModel) arg).getData());
			System.out.println("HourEntry-View updated");
		}
	}

}
