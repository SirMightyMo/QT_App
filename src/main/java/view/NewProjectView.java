package main.java.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import main.java.controller.DatePicker;
import main.java.controller.NewProjectController;
import main.java.model.NewProjectModel;
import main.java.model.StaticActions;

@SuppressWarnings("deprecation")
public class NewProjectView implements IView {
	private JPanel contentPanel; // Container
	private JTextField txtStartTime;
	private JTextField txtEndTime;
	private JTextField textFieldProjectname;
	private JComboBox<String> dropDownClient;
	private JLabel lblErrorMessage;
	private JButton btnSave;
	private boolean errorVisible;
	private boolean buttonsHighlighted;

	public NewProjectView(NewProjectController newProjectController) {
		//setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // top, left, bottom, right
		contentPanel.setBounds(0, 0, 365, 245);
		contentPanel.setBackground(new Color(31, 32, 33));
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

		JPanel projectPanel = new JPanel();
		projectPanel.setName("projectPanel");
		contentPanel.add(projectPanel);
		projectPanel.setBackground(new Color(31, 32, 33));
		projectPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblProject = new JLabel("Neues Projekt anlegen");
		lblProject.setName("lblProject");
		lblProject.setHorizontalAlignment(SwingConstants.CENTER);
		projectPanel.add(lblProject);
		lblProject.setName("timerLabel");
				
						JPanel panel = new JPanel();
						panel.setBackground(new Color(31, 32, 33));
						contentPanel.add(panel);
						panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
						
								JLabel lblComment = new JLabel("Projektname:");
								panel.add(lblComment);
								lblComment.setHorizontalAlignment(SwingConstants.CENTER);
								lblComment.setLabelFor(textFieldProjectname);
								
										textFieldProjectname = new JTextField();
										textFieldProjectname.setName("textFieldProjectname");
										panel.add(textFieldProjectname);
										textFieldProjectname.setAlignmentX(Component.LEFT_ALIGNMENT);
										textFieldProjectname.setPreferredSize(new Dimension(300, 22));
										textFieldProjectname.setToolTipText("");
										textFieldProjectname.setHorizontalAlignment(SwingConstants.LEFT);
										textFieldProjectname.setColumns(20);
		
				JPanel clientPanel = new JPanel();
				clientPanel.setBackground(new Color(31, 32, 33));
				contentPanel.add(clientPanel);
				clientPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
				
				JLabel lblClient = new JLabel("Kunde:");
				clientPanel.add(lblClient);
				lblClient.setHorizontalAlignment(SwingConstants.CENTER);
				lblClient.setLabelFor(dropDownClient);
				
						dropDownClient = new JComboBox<String>();
						dropDownClient.setMaximumRowCount(5);
						dropDownClient.setName("dropDownClient");
						clientPanel.add(dropDownClient);
						dropDownClient.setAlignmentX(Component.LEFT_ALIGNMENT);
						dropDownClient.setPreferredSize(new Dimension(250, 20));

		JPanel manualEntryPanel = new JPanel();
		manualEntryPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(manualEntryPanel);
		manualEntryPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblFrom = new JLabel("Start:");
		manualEntryPanel.add(lblFrom);

		txtStartTime = new JTextField();
		txtStartTime.setEnabled(true);
		txtStartTime.setEditable(false);
		lblFrom.setLabelFor(txtStartTime);
		txtStartTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtStartTime.setText("...");
		txtStartTime.getDocument().addDocumentListener(newProjectController);
		manualEntryPanel.add(txtStartTime);
		txtStartTime.setColumns(10);
		// add MouseListener: When clicking into field, DatePicker is opened
		txtStartTime.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final JFrame popupFrame = new JFrame();
				popupFrame.setName("popupFrame");
				txtStartTime.setText(new DatePicker(popupFrame).setPickedDate().replace("-", "."));
				if (txtStartTime.getText().equals("")) {
					txtStartTime.setText("...");
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		JLabel lblTo = new JLabel("Ende:");
		manualEntryPanel.add(lblTo);

		txtEndTime = new JTextField();
		txtEndTime.setEnabled(true);
		txtEndTime.setEditable(false);
		lblTo.setLabelFor(txtEndTime);
		txtEndTime.setHorizontalAlignment(SwingConstants.CENTER);
		txtEndTime.setText("...");
		txtEndTime.getDocument().addDocumentListener(newProjectController);
		manualEntryPanel.add(txtEndTime);
		txtEndTime.setColumns(10);
		// add MouseListener: When clicking into field, DatePicker is opened
		txtEndTime.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				final JFrame popupFrame = new JFrame();
				popupFrame.setName("popupFrame");
				txtEndTime.setText(new DatePicker(popupFrame).setPickedDate().replace("-", "."));
				if (txtEndTime.getText().equals("")) {
					txtEndTime.setText("...");
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {	
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
		});

		JPanel confirmButtonPanel = new JPanel();
		confirmButtonPanel.setBackground(new Color(31, 32, 33));
		contentPanel.add(confirmButtonPanel);

		//Save Button
		btnSave = new JButton("Sichern");
		btnSave.setName("btnSave");
		btnSave.addActionListener(newProjectController);
		btnSave.setActionCommand(StaticActions.ACTION_NPROJECT_SAVE);
		confirmButtonPanel.add(btnSave);
		
				JButton btnLoadProjects = new JButton("\u21BB");
				confirmButtonPanel.add(btnLoadProjects);
				btnLoadProjects.setFont(new Font("Lucida Sans Unicode", Font.BOLD, 14));
				btnLoadProjects.setName("btnLoadProjects");
				btnLoadProjects.addActionListener(newProjectController);
				btnLoadProjects.setActionCommand(StaticActions.ACTION_NPROJECT_RESET);

	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JComboBox<String> getDropDownClient() {
		return dropDownClient;
	}

	public void setDropDownClient(JComboBox<String> dropDownClient) {
		this.dropDownClient = dropDownClient;
	}

	public JTextField getTxtStartTime() {
		return txtStartTime;
	}

	public JTextField getTxtEndTime() {
		return txtEndTime;
	}

	public JTextField getTextFieldProjectname() {
		return textFieldProjectname;
	}

	public JLabel getLblErrorMessage() {
		return lblErrorMessage;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public boolean isErrorVisible() {
		return errorVisible;
	}

	public void setErrorVisible(boolean errorVisible) {
		this.errorVisible = errorVisible;
	}

	public boolean isButtonsHighlighted() {
		return buttonsHighlighted;
	}

	public void setButtonsHighlighted(boolean buttonsHighlighted) {
		this.buttonsHighlighted = buttonsHighlighted;
	}

	@Override
	public void update(Observable o, Object arg) {

		if (arg instanceof NewProjectModel) {
			ArrayList<String> clients = new ArrayList<>();
			((NewProjectModel) arg).getClientList().forEach(client -> {
				clients.add(client.get(1).toString());
			});
			this.dropDownClient.setModel(new DefaultComboBoxModel(clients.toArray()));
		}

	}
}

