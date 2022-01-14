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
import javax.swing.border.EmptyBorder;

import main.java.controller.DatePicker;
import main.java.controller.ProjectController;
import main.java.model.ProjectModel;
import main.java.model.StaticActions;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JTabbedPane;

@SuppressWarnings("deprecation")
public class ProjectView extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // Container
	private JTable table;
	private JTextField textFieldFrom;
	private JTextField textFieldTo;
	private TableRowSorter<TableModel> sorter;
	
	JComboBox comboBoxProject = new JComboBox();
	private JTextField textFieldProjectName;
	private JTextField textFieldClient;
	private JTextField textFieldEndDate;
	
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

		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, tabbedPane, 10, SpringLayout.NORTH, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, tabbedPane, 10, SpringLayout.WEST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.SOUTH, tabbedPane, -15, SpringLayout.SOUTH, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, tabbedPane, 920, SpringLayout.WEST, contentPane);
		contentPane.add(tabbedPane);
		
		JPanel panel_project_overview = new JPanel();
		tabbedPane.addTab("Projekt Übersicht", null, panel_project_overview, null);
		SpringLayout sl_panel_project_overview = new SpringLayout();
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, comboBoxProject, -434, SpringLayout.EAST, panel_project_overview);
		panel_project_overview.setLayout(sl_panel_project_overview);
		
		// Titel Label
		JLabel lblHeadTitel = new JLabel("Projekt Übersicht");
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, lblHeadTitel, 10, SpringLayout.NORTH, panel_project_overview);
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, lblHeadTitel, 2, SpringLayout.WEST, panel_project_overview);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, comboBoxProject, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, comboBoxProject, 10, SpringLayout.WEST, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblHeadTitel, 38, SpringLayout.NORTH, panel_project_overview);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblHeadTitel, 47, SpringLayout.WEST, panel_project_overview);
		springLayoutContentPane.putConstraint(SpringLayout.SOUTH, lblHeadTitel, 384, SpringLayout.NORTH, panel_project_overview);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, lblHeadTitel, 918, SpringLayout.WEST, panel_project_overview);
		panel_project_overview.add(lblHeadTitel);
		lblHeadTitel.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		// Tabel Frame
		JScrollPane scrollPaneTable = new JScrollPane();
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, scrollPaneTable, -541, SpringLayout.SOUTH, panel_project_overview);
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, scrollPaneTable, 10, SpringLayout.WEST, panel_project_overview);
		sl_panel_project_overview.putConstraint(SpringLayout.SOUTH, scrollPaneTable, -35, SpringLayout.SOUTH, panel_project_overview);
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, scrollPaneTable, 895, SpringLayout.WEST, panel_project_overview);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, scrollPaneTable, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, scrollPaneTable, 10, SpringLayout.WEST, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.SOUTH, scrollPaneTable, -741, SpringLayout.SOUTH, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, scrollPaneTable, -133, SpringLayout.EAST, contentPane);
		panel_project_overview.add(scrollPaneTable);
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
		
		
		// Tabel sort activate
		sorter = new TableRowSorter<>(table.getModel());	
		List<RowSorter.SortKey> sortKeys = new ArrayList<>();	 
		int columnIndexToSort = 0;
		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		sorter.sort();
		table.setRowSorter(sorter);
		
		scrollPaneTable.setViewportView(table);
		
		// Projects Label
		JLabel lblProjects = new JLabel("Projekt Name:");
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, comboBoxProject, -3, SpringLayout.NORTH, lblProjects);
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, comboBoxProject, 22, SpringLayout.EAST, lblProjects);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblProjects, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblProjects, 10, SpringLayout.WEST, lblHeadTitel);
		panel_project_overview.add(lblProjects);
		panel_project_overview.add(comboBoxProject);
		// Projects DropDown 
		comboBoxProject.setPreferredSize(new Dimension(300,20));
		comboBoxProject.setAlignmentX(0.0f);
		comboBoxProject.addActionListener(projectController);
		comboBoxProject.setActionCommand(StaticActions.ACTION_SET_PROJECT);
		lblProjects.setLabelFor(comboBoxProject);
		// refresh button projects
		JButton btnLoadProjects = new JButton("\u21BB");
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, btnLoadProjects, -4, SpringLayout.NORTH, lblProjects);
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, btnLoadProjects, 14, SpringLayout.EAST, comboBoxProject);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, btnLoadProjects, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, btnLoadProjects, 10, SpringLayout.WEST, lblHeadTitel);
		panel_project_overview.add(btnLoadProjects);
		btnLoadProjects.addActionListener(projectController);
		btnLoadProjects.setActionCommand(StaticActions.ACTION_LOAD_PROJECTS);
		// Services Label
		JLabel lblService = new JLabel("Leistung:");
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, lblProjects, 0, SpringLayout.WEST, lblService);
		sl_panel_project_overview.putConstraint(SpringLayout.SOUTH, lblProjects, -17, SpringLayout.NORTH, lblService);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblService, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblService, 10, SpringLayout.WEST, lblHeadTitel);
		panel_project_overview.add(lblService);
		// Services DropDown 
		JComboBox comboBoxService = new JComboBox();
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, comboBoxService, -4, SpringLayout.NORTH, lblService);
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, comboBoxService, 0, SpringLayout.WEST, comboBoxProject);
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, comboBoxService, 0, SpringLayout.EAST, comboBoxProject);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, comboBoxService, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, comboBoxService, 10, SpringLayout.WEST, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, comboBoxService, -85, SpringLayout.EAST, comboBoxProject);
		panel_project_overview.add(comboBoxService);
		// Time Frame Label
		JLabel lblTimeFrame = new JLabel("Zeitraum:");
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, lblService, 0, SpringLayout.WEST, lblTimeFrame);
		sl_panel_project_overview.putConstraint(SpringLayout.SOUTH, lblService, -20, SpringLayout.NORTH, lblTimeFrame);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblTimeFrame, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblTimeFrame, 10, SpringLayout.WEST, lblHeadTitel);
		panel_project_overview.add(lblTimeFrame);
		// search button
		JButton btnSearchButton = new JButton("Suchen...");
		sl_panel_project_overview.putConstraint(SpringLayout.SOUTH, btnSearchButton, -19, SpringLayout.NORTH, scrollPaneTable);
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, btnSearchButton, -10, SpringLayout.EAST, panel_project_overview);
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, lblTimeFrame, 4, SpringLayout.NORTH, btnSearchButton);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, btnSearchButton, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, btnSearchButton, 10, SpringLayout.WEST, lblHeadTitel);
		panel_project_overview.add(btnSearchButton);
		btnSearchButton.addActionListener(projectController);
		btnSearchButton.setActionCommand(StaticActions.ACTION_SEARCH_PROJECTS);
		// input start date
		textFieldFrom = new JTextField();
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, textFieldFrom, 1, SpringLayout.NORTH, btnSearchButton);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, textFieldFrom, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, textFieldFrom, 10, SpringLayout.WEST, lblHeadTitel);
		panel_project_overview.add(textFieldFrom);
		textFieldFrom.setColumns(10);
		// input start date label
		JLabel lblFrom = new JLabel("von:");
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, textFieldFrom, 9, SpringLayout.EAST, lblFrom);
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, lblFrom, -669, SpringLayout.EAST, panel_project_overview);
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, lblTimeFrame, -13, SpringLayout.WEST, lblFrom);
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, lblFrom, 4, SpringLayout.NORTH, btnSearchButton);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblFrom, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblFrom, 10, SpringLayout.WEST, lblHeadTitel);
		panel_project_overview.add(lblFrom);
		// input end date label
		JLabel lblTo = new JLabel("bis:");
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, textFieldFrom, -45, SpringLayout.WEST, lblTo);
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, lblTo, 0, SpringLayout.NORTH, lblTimeFrame);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, lblTo, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, lblTo, 10, SpringLayout.WEST, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, textFieldFrom, -97, SpringLayout.WEST, lblTo);
		panel_project_overview.add(lblTo);
		// input end date
		textFieldTo = new JTextField();
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, lblTo, -6, SpringLayout.WEST, textFieldTo);
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, textFieldTo, -6, SpringLayout.EAST, comboBoxProject);
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, textFieldTo, -3, SpringLayout.NORTH, lblTimeFrame);
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, textFieldTo, 392, SpringLayout.WEST, panel_project_overview);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, textFieldTo, 6, SpringLayout.SOUTH, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, textFieldTo, 10, SpringLayout.WEST, lblHeadTitel);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, textFieldTo, -386, SpringLayout.WEST, btnSearchButton);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, comboBoxProject, -85, SpringLayout.EAST, textFieldTo);
		panel_project_overview.add(textFieldTo);
		textFieldTo.setColumns(10);
		
		JButton btnSetStartDate_1 = new JButton("...");
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, btnSetStartDate_1, -4, SpringLayout.NORTH, lblTimeFrame);
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, btnSetStartDate_1, 13, SpringLayout.EAST, textFieldFrom);
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, btnSetStartDate_1, -6, SpringLayout.WEST, lblTo);
		panel_project_overview.add(btnSetStartDate_1);
		
		JButton btnSetStartDate_1_1 = new JButton("...");
		sl_panel_project_overview.putConstraint(SpringLayout.NORTH, btnSetStartDate_1_1, -4, SpringLayout.NORTH, lblTimeFrame);
		sl_panel_project_overview.putConstraint(SpringLayout.WEST, btnSetStartDate_1_1, 6, SpringLayout.EAST, textFieldTo);
		sl_panel_project_overview.putConstraint(SpringLayout.EAST, btnSetStartDate_1_1, -319, SpringLayout.WEST, btnSearchButton);
		panel_project_overview.add(btnSetStartDate_1_1);
		

		
		// Second Tab / new project
		
		JPanel panel_new_project = new JPanel();
		tabbedPane.addTab("Neues Projekt", null, panel_new_project, null);
		SpringLayout sl_panel_new_project = new SpringLayout();
		panel_new_project.setLayout(sl_panel_new_project);
		
		JLabel lblNewProjectHead = new JLabel("Neues Projekt:");
		sl_panel_new_project.putConstraint(SpringLayout.NORTH, lblNewProjectHead, 10, SpringLayout.NORTH, panel_new_project);
		sl_panel_new_project.putConstraint(SpringLayout.WEST, lblNewProjectHead, 10, SpringLayout.WEST, panel_new_project);
		lblNewProjectHead.setFont(new Font("Tahoma", Font.BOLD, 18));
		panel_new_project.add(lblNewProjectHead);
		
		JPanel panel_input_form = new JPanel();
		sl_panel_new_project.putConstraint(SpringLayout.NORTH, panel_input_form, 80, SpringLayout.NORTH, panel_new_project);
		sl_panel_new_project.putConstraint(SpringLayout.WEST, panel_input_form, 150, SpringLayout.WEST, panel_new_project);
		sl_panel_new_project.putConstraint(SpringLayout.SOUTH, panel_input_form, -333, SpringLayout.SOUTH, panel_new_project);
		sl_panel_new_project.putConstraint(SpringLayout.EAST, panel_input_form, -425, SpringLayout.EAST, panel_new_project);
		panel_new_project.add(panel_input_form);
		SpringLayout sl_panel_input_form = new SpringLayout();
		panel_input_form.setLayout(sl_panel_input_form);
		
		JLabel lblNewLabel_18 = new JLabel("Projekt Name:");
		sl_panel_input_form.putConstraint(SpringLayout.EAST, lblNewLabel_18, 120, SpringLayout.WEST, panel_input_form);
		panel_input_form.add(lblNewLabel_18);
		
		textFieldProjectName = new JTextField();
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, lblNewLabel_18, 3, SpringLayout.NORTH, textFieldProjectName);
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, textFieldProjectName, 10, SpringLayout.NORTH, panel_input_form);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, textFieldProjectName, 144, SpringLayout.WEST, panel_input_form);
		sl_panel_input_form.putConstraint(SpringLayout.EAST, textFieldProjectName, -10, SpringLayout.EAST, panel_input_form);
		panel_input_form.add(textFieldProjectName);
		textFieldProjectName.setColumns(10);
		
		JLabel lblNewLabel_19 = new JLabel("Kunde:");
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, lblNewLabel_19, 20, SpringLayout.SOUTH, lblNewLabel_18);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, lblNewLabel_18, 0, SpringLayout.WEST, lblNewLabel_19);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, lblNewLabel_19, 20, SpringLayout.WEST, panel_input_form);
		panel_input_form.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("Start Datum:");
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, lblNewLabel_20, 21, SpringLayout.SOUTH, lblNewLabel_19);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, lblNewLabel_20, 20, SpringLayout.WEST, panel_input_form);
		sl_panel_input_form.putConstraint(SpringLayout.EAST, lblNewLabel_20, -210, SpringLayout.EAST, panel_input_form);
		panel_input_form.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("End Datum:");
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, lblNewLabel_21, 21, SpringLayout.SOUTH, lblNewLabel_20);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, lblNewLabel_21, 20, SpringLayout.WEST, panel_input_form);
		sl_panel_input_form.putConstraint(SpringLayout.EAST, lblNewLabel_21, -210, SpringLayout.EAST, panel_input_form);
		panel_input_form.add(lblNewLabel_21);
		
		textFieldClient = new JTextField();
		sl_panel_input_form.putConstraint(SpringLayout.EAST, lblNewLabel_19, -24, SpringLayout.WEST, textFieldClient);
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, textFieldClient, 14, SpringLayout.SOUTH, textFieldProjectName);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, textFieldClient, 144, SpringLayout.WEST, panel_input_form);
		sl_panel_input_form.putConstraint(SpringLayout.EAST, textFieldClient, 0, SpringLayout.EAST, textFieldProjectName);
		panel_input_form.add(textFieldClient);
		textFieldClient.setColumns(10);
		final JTextField textFieldStartDate = new JTextField(20);
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, textFieldStartDate, -3, SpringLayout.NORTH, lblNewLabel_20);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, textFieldStartDate, 0, SpringLayout.WEST, textFieldProjectName);
		
		
		JButton btnSetStartDate = new JButton("...");
		sl_panel_input_form.putConstraint(SpringLayout.EAST, textFieldStartDate, -6, SpringLayout.WEST, btnSetStartDate);
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, btnSetStartDate, -4, SpringLayout.NORTH, lblNewLabel_20);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, btnSetStartDate, -33, SpringLayout.EAST, textFieldProjectName);
		sl_panel_input_form.putConstraint(SpringLayout.EAST, btnSetStartDate, 0, SpringLayout.EAST, textFieldProjectName);
		panel_input_form.add(textFieldStartDate);
		panel_input_form.add(btnSetStartDate);
		
		textFieldEndDate = new JTextField(20);
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, textFieldEndDate, -3, SpringLayout.NORTH, lblNewLabel_21);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, textFieldEndDate, 0, SpringLayout.WEST, textFieldProjectName);
		sl_panel_input_form.putConstraint(SpringLayout.EAST, textFieldEndDate, 0, SpringLayout.EAST, textFieldStartDate);
		panel_input_form.add(textFieldEndDate);
		
		JButton btnSetEndDate = new JButton("...");
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, btnSetEndDate, -4, SpringLayout.NORTH, lblNewLabel_21);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, btnSetEndDate, 0, SpringLayout.WEST, btnSetStartDate);
		sl_panel_input_form.putConstraint(SpringLayout.EAST, btnSetEndDate, -10, SpringLayout.EAST, panel_input_form);
		
		panel_input_form.add(btnSetEndDate);
		
		
		//Save Button
		JButton btnNewButton = new JButton("Speichern");
		sl_panel_input_form.putConstraint(SpringLayout.NORTH, btnNewButton, 50, SpringLayout.SOUTH, textFieldEndDate);
		sl_panel_input_form.putConstraint(SpringLayout.WEST, btnNewButton, 116, SpringLayout.WEST, panel_input_form);
		panel_input_form.add(btnNewButton);
		
		// Date Popup
		final JFrame popupFrame = new JFrame();
		btnSetStartDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				popupFrame.setLocation(getMousePosition());
				textFieldStartDate.setText(new DatePicker(popupFrame).setPickedDate());
				System.out.print(textFieldStartDate.getText());
			}
		});
		btnSetEndDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				textFieldEndDate.setText(new DatePicker(popupFrame).setPickedDate());
			}
		});
		btnSetStartDate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				textFieldFrom.setText(new DatePicker(popupFrame).setPickedDate());
				System.out.print(textFieldFrom.getText());
			}
		});
		btnSetStartDate_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				textFieldTo.setText(new DatePicker(popupFrame).setPickedDate());
			}
		});
		
		// third tab / new client
		JPanel panel_new_client = new JPanel();
		tabbedPane.addTab("Neuer Kunde", null, panel_new_client, null);
		
		// fourth tab / service
		JPanel panel_service = new JPanel();
		tabbedPane.addTab("Leistungen", null, panel_service, null);
		
		

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
