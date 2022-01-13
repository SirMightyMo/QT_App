package main.java.view;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.PatternSyntaxException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.controller.ProjectController;
import main.java.model.ProjectModel;
import main.java.model.StaticActions;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JComboBox;

@SuppressWarnings("deprecation")
public class ProjectView extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // Container
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private TableRowSorter<TableModel> sorter;
	
	JComboBox comboBoxProject = new JComboBox();
	
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
		
		// Titel Label
		JLabel lblNewLabel = new JLabel("Projekt Übersicht");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 29, SpringLayout.NORTH, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 43, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		// Tabel Frame
		JScrollPane scrollPane = new JScrollPane();
		springLayoutContentPane.putConstraint(SpringLayout.WEST, scrollPane, 47, SpringLayout.WEST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -70, SpringLayout.SOUTH, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, scrollPane, 933, SpringLayout.WEST, contentPane);
		contentPane.add(scrollPane);
		
		// Projects Label
		JLabel lblProjects = new JLabel("Projekt Name:");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblProjects, 7, SpringLayout.NORTH, lblNewLabel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblProjects, 56, SpringLayout.EAST, lblNewLabel);
		contentPane.add(lblProjects);
		// Projects DropDown 
		comboBoxProject.setPreferredSize(new Dimension(300,20));
		lblProjects.setLabelFor(comboBoxProject);
		comboBoxProject.setAlignmentX(0.0f);
		comboBoxProject.addActionListener(projectController);
		comboBoxProject.setActionCommand(StaticActions.ACTION_SET_PROJECT);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, comboBoxProject, 3, SpringLayout.NORTH, lblNewLabel);
		contentPane.add(comboBoxProject);
		// refresh button projects
		JButton btnLoadProjects = new JButton("\u21BB");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, btnLoadProjects, 3, SpringLayout.NORTH, lblNewLabel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, btnLoadProjects, 17, SpringLayout.EAST, comboBoxProject);
		btnLoadProjects.addActionListener(projectController);
		btnLoadProjects.setActionCommand(StaticActions.ACTION_LOAD_PROJECTS);
		contentPane.add(btnLoadProjects);
		// Services Label
		JLabel lblNewLabel_2 = new JLabel("Leistung:");
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblNewLabel_2, 0, SpringLayout.WEST, lblProjects);
		contentPane.add(lblNewLabel_2);
		// Services DropDown 
		JComboBox comboBox_1 = new JComboBox();
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, comboBox_1, 12, SpringLayout.SOUTH, comboBoxProject);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 4, SpringLayout.NORTH, comboBox_1);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, comboBox_1, 0, SpringLayout.WEST, comboBoxProject);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, comboBox_1, 0, SpringLayout.EAST, comboBoxProject);
		contentPane.add(comboBox_1);
		// Time Frame Label
		JLabel lblNewLabel_3 = new JLabel("Zeitraum:");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_3, 22, SpringLayout.SOUTH, lblNewLabel_2);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblNewLabel_3, 0, SpringLayout.WEST, lblProjects);
		contentPane.add(lblNewLabel_3);
		// search button
		JButton btnSearchButton = new JButton("Suchen...");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, btnSearchButton, -4, SpringLayout.NORTH, lblNewLabel_3);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, btnSearchButton, 0, SpringLayout.EAST, scrollPane);
		btnSearchButton.addActionListener(projectController);
		btnSearchButton.setActionCommand(StaticActions.ACTION_SEARCH_PROJECTS);
		contentPane.add(btnSearchButton);
		// input start date
		textField = new JTextField();
		springLayoutContentPane.putConstraint(SpringLayout.WEST, comboBoxProject, 0, SpringLayout.WEST, textField);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, scrollPane, 28, SpringLayout.SOUTH, textField);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblNewLabel_3);
		contentPane.add(textField);
		textField.setColumns(10);
		// input start date label
		JLabel lblNewLabel_4 = new JLabel("von:");
		springLayoutContentPane.putConstraint(SpringLayout.WEST, textField, 7, SpringLayout.EAST, lblNewLabel_4);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, lblNewLabel_4, -597, SpringLayout.EAST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_4, 0, SpringLayout.NORTH, lblNewLabel_3);
		contentPane.add(lblNewLabel_4);
		// input end date label
		JLabel lblNewLabel_5 = new JLabel("bis:");
		springLayoutContentPane.putConstraint(SpringLayout.EAST, textField, -12, SpringLayout.WEST, lblNewLabel_5);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_5, 0, SpringLayout.NORTH, lblNewLabel_3);
		contentPane.add(lblNewLabel_5);
		// input end date
		textField_1 = new JTextField();
		springLayoutContentPane.putConstraint(SpringLayout.EAST, comboBoxProject, 0, SpringLayout.EAST, textField_1);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, textField_1, 492, SpringLayout.WEST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, textField_1, -301, SpringLayout.WEST, btnSearchButton);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, lblNewLabel_5, -6, SpringLayout.WEST, textField_1);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, textField_1, -3, SpringLayout.NORTH, lblNewLabel_3);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		// create table
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
		table.setAutoCreateRowSorter(true);
		
		sorter = new TableRowSorter<>(table.getModel());
		table.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
		 
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		 
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		
		scrollPane.setViewportView(table);
		
	}
	
	
	// Getter Setter
	public JComboBox getComboBox() {
		return comboBoxProject;
	}

	public void setComboBox(JComboBox comboBox) {
		this.comboBoxProject = comboBox;
	}

	public JTable getProjectTable() {
		JTable table = this.table;
		return table;
	}
	
	public void setProjectTable(JTable table) {
		this.table = table;
	}
	
	public void filterProjects(String text) {
        if(text.length() == 0) {
           sorter.setRowFilter(null);
        } else {
           try {
              sorter.setRowFilter(RowFilter.regexFilter(text));
           } catch(PatternSyntaxException pse) {
                 System.out.println("Bad regex pattern");
           }
         }
	}
	@Override
	public void update(Observable o, Object arg) {

		if(arg instanceof ProjectModel) {
			ArrayList<String> projectNames = new ArrayList<>();
			((ProjectModel) arg).getProjectList().forEach(project -> {
			projectNames.add(project.get(1).toString());
			});
		this.comboBoxProject.setModel(new DefaultComboBoxModel(projectNames.toArray()));
		System.out.println("Projects loaded into FilterView.");
		}
	}
}
