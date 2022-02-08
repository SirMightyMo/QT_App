package main.java.view;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;

import main.java.controller.AccountController;

public class AccountView implements IView {

	
	private JPanel accountPanel;
	

	/**
	 * Create the panel.
	 */
	public AccountView(AccountController accountController) {

		accountPanel = new JPanel();
		accountPanel.setName("dashboardMainPane");
		accountPanel.setBounds(0, 0, 1490, 1060);
		accountPanel.setBackground(new Color(47,48,52));
		accountPanel.setLayout(null);
		
		JPanel accountPanel1 = new JPanel();
		accountPanel1.setName("dashboardTimerPane");
		accountPanel1.setBackground(new Color(31,32,33));
		accountPanel1.setBounds(10, 87, 1470, 962);
		accountPanel.add(accountPanel1);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 626, 65);
		panel.setBackground(new Color(47,48,52));
		accountPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Account Einstellungen");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 606, 43);
		panel.add(lblNewLabel);
		
	}
	
public JPanel getAccountPanel() {
		return accountPanel;
	}

	public void setAccountPanel(JPanel accountPanel) {
		this.accountPanel = accountPanel;
	}
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
