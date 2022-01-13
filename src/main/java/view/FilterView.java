package main.java.view;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.controller.ProjectController;
import main.java.controller.TimerHourController;
import main.java.model.StaticActions;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

@SuppressWarnings("deprecation")
public class FilterView extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // Container
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	JComboBox comboBox = new JComboBox();
	
	/**
	 * Create Frame
	 */
	public FilterView(ProjectController projectController) {
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
		springLayoutContentPane.putConstraint(SpringLayout.WEST, scrollPane, 47, SpringLayout.WEST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -70, SpringLayout.SOUTH, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, scrollPane, 933, SpringLayout.WEST, contentPane);
		contentPane.add(scrollPane);
		
		JLabel lblProjects = new JLabel("Projekt Name:");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblProjects, 7, SpringLayout.NORTH, lblNewLabel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblProjects, 56, SpringLayout.EAST, lblNewLabel);
		contentPane.add(lblProjects);
		// DropDown projects
		comboBox.setPreferredSize(new Dimension(300,20));
		lblProjects.setLabelFor(comboBox);
		comboBox.setAlignmentX(0.0f);
		comboBox.addActionListener(projectController);
		comboBox.setActionCommand(StaticActions.ACTION_SET_PROJECT);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, comboBox, 3, SpringLayout.NORTH, lblNewLabel);
		contentPane.add(comboBox);
		
		JButton btnLoadProjects = new JButton("\u21BB");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, btnLoadProjects, 3, SpringLayout.NORTH, lblNewLabel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, btnLoadProjects, 17, SpringLayout.EAST, comboBox);
		btnLoadProjects.addActionListener(projectController);
		btnLoadProjects.setActionCommand(StaticActions.ACTION_LOAD_PROJECTS);
		contentPane.add(btnLoadProjects);
		

		
		JLabel lblNewLabel_2 = new JLabel("Leistung:");
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblProjects);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Zeitraum:");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 22, SpringLayout.SOUTH, lblNewLabel_2);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblProjects);
		contentPane.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, comboBox_1, 12, SpringLayout.SOUTH, comboBox);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 4, SpringLayout.NORTH, comboBox_1);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, comboBox_1, 0, SpringLayout.WEST, comboBox);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, comboBox_1, 0, SpringLayout.EAST, comboBox);
		contentPane.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Suchen...");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, btnNewButton, -4, SpringLayout.NORTH, lblNewLabel_3);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, btnNewButton, 0, SpringLayout.EAST, scrollPane);
		contentPane.add(btnNewButton);
		
		textField = new JTextField();
		springLayoutContentPane.putConstraint(SpringLayout.WEST, comboBox, 0, SpringLayout.WEST, textField);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, scrollPane, 28, SpringLayout.SOUTH, textField);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblNewLabel_3);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("von:");
		springLayoutContentPane.putConstraint(SpringLayout.WEST, textField, 7, SpringLayout.EAST, lblNewLabel_4);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, lblNewLabel_4, -597, SpringLayout.EAST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 0, SpringLayout.NORTH, lblNewLabel_3);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("bis:");
		springLayoutContentPane.putConstraint(SpringLayout.EAST, textField, -12, SpringLayout.WEST, lblNewLabel_5);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 0, SpringLayout.NORTH, lblNewLabel_3);
		contentPane.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		springLayoutContentPane.putConstraint(SpringLayout.EAST, comboBox, 0, SpringLayout.EAST, textField_1);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, textField_1, 492, SpringLayout.WEST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, textField_1, -301, SpringLayout.WEST, btnNewButton);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, lblNewLabel_5, -6, SpringLayout.WEST, textField_1);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblNewLabel_3);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		// Tabelle erstellen
				table = new JTable();
				table.setModel(new DefaultTableModel(projectController.getTableModel(),
					new String[] {
						"#", "Projektname", "Start", "Ende", "Status", "Dauer", "Kunde"
					}
				
				));
				table.getColumnModel().getColumn(0).setPreferredWidth(8);
				table.getColumnModel().getColumn(1).setPreferredWidth(41);
				table.getColumnModel().getColumn(2).setPreferredWidth(44);
				table.getColumnModel().getColumn(4).setPreferredWidth(52);
				table.getColumnModel().getColumn(5).setPreferredWidth(115);
				scrollPane.setViewportView(table);
		
	}
	
	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBox = comboBox;
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
