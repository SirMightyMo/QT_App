package main.java.view;

import java.awt.Color;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.controller.SessionController;
import javax.swing.JTextField;

public class SessionView implements IView {

private JPanel sessionPanel;
private JTextField textField;
private JTextField textField_1;
private JTextField textField_2;



	public SessionView(SessionController sessionController) {
		sessionPanel = new JPanel();
		sessionPanel.setName("dashboardMainPane");
		sessionPanel.setBounds(0, 0, 1490, 1060);
		sessionPanel.setBackground(new Color(47,48,52));
		sessionPanel.setLayout(null);
		
		JPanel sessionPanel1 = new JPanel();
		sessionPanel1.setBounds(10, 87, 1470, 944);
		sessionPanel1.setName("dashboardTimerPane");
		sessionPanel1.setBackground(new Color(31,32,33));
		sessionPanel.add(sessionPanel1);
		sessionPanel1.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Sitzungen");
		lblNewLabel.setBounds(10, 60, 134, 24);
		sessionPanel.add(lblNewLabel);
		lblNewLabel.setForeground(Color.WHITE);

	}
	public JPanel getSessionPanel() {
	return sessionPanel;
}

	public void setSessionPanel(JPanel sessionPanel) {
	this.sessionPanel = sessionPanel;
}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
