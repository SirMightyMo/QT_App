package main.java.view;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import main.java.controller.ProjectController;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

@SuppressWarnings("deprecation")
public class ProjectView extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // Container
	private JTable table;
	
	/**
	 * Create Frame
	 */
	public ProjectView(ProjectController projectController) {
		setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TimerView.class.getResource("/main/resources/img/icons/qtproject_placeholder.gif")));
		setTitle("Quality Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 790); // x, y, width, height
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // top, left, bottom, right
		setContentPane(contentPane);
		SpringLayout springLayoutContentPane = new SpringLayout();
		contentPane.setLayout(springLayoutContentPane);
		
		JLabel lblNewLabel = new JLabel("Projekt Übersicht");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 29, SpringLayout.NORTH, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 43, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, scrollPane, 25, SpringLayout.SOUTH, lblNewLabel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblNewLabel);
		springLayoutContentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -145, SpringLayout.SOUTH, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, scrollPane, 929, SpringLayout.WEST, contentPane);
		contentPane.add(scrollPane);
		
		
		// Tabelle erstellen
				table = new JTable();
				table.setShowVerticalLines(false);
				table.setBorder(null);
				table.setModel(new DefaultTableModel(
					new Object[][] {
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
						{null, null, null, null, null, null, null},
					},
					new String[] {
						"Projektname", "Start", "Ende", "Status", "Arbeitszeit", "", "New column"
					}
				));

				scrollPane.setViewportView(table);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		/*
		if(arg instanceof ProjectModel) {
			this.durationLabel.setText(((TimerModel) arg).timerToString());
			//System.out.println("View updated");
		}*/
		
	}
}
