package main.java.view;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

import main.java.controller.TimerHourController;
import main.java.model.StaticActions;
import main.java.model.TimerModel;

import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Color;

import javax.swing.ImageIcon;

public class TimerView extends JFrame implements Observer{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane; // Container
	private JLabel durationLabel = new JLabel("00:00:00");
	
	/**
	 * Create Frame
	 */
	public TimerView(TimerHourController timerHourController) {
		setFont(new Font("Open Sans ExtraBold", Font.PLAIN, 12));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(TimerView.class.getResource("/main/resources/img/icons/qtproject_placeholder.gif")));
		setTitle("Quality Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 273, 115); // x, y, width, height
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // top, left, bottom, right
		setContentPane(contentPane);
		SpringLayout springLayoutContentPane = new SpringLayout();
		springLayoutContentPane.putConstraint(SpringLayout.EAST, durationLabel, -33, SpringLayout.EAST, contentPane);
		contentPane.setLayout(springLayoutContentPane);
		
		// Pane for buttons
		JPanel buttonPane = new JPanel();
		springLayoutContentPane.putConstraint(SpringLayout.WEST, buttonPane, 0, SpringLayout.WEST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, buttonPane, 0, SpringLayout.EAST, contentPane);
		contentPane.add(buttonPane);
		
		// Button start
		JButton btnStart = new JButton("");
		btnStart.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/play.gif")));
		btnStart.addActionListener(timerHourController);
		btnStart.setActionCommand(StaticActions.ACTION_TIMER_START); // Defined in Class StaticActions
		buttonPane.add(btnStart);
		
		// Button pause
		JButton btnPause = new JButton("");
		btnPause.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/pause.gif")));
		btnPause.addActionListener(timerHourController);
		btnPause.setActionCommand(StaticActions.ACTION_TIMER_PAUSE);
		buttonPane.add(btnPause);
		
		// Button stop
		JButton btnStop = new JButton("");
		btnStop.setIcon(new ImageIcon(TimerView.class.getResource("/main/resources/img/icons/stop.gif")));
		btnStop.addActionListener(timerHourController);
		btnStop.setActionCommand(StaticActions.ACTION_TIMER_STOP);
		buttonPane.add(btnStop);
		
		// Timer Label
		JLabel timerLabel = new JLabel("Dauer: ");
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, timerLabel, 0, SpringLayout.SOUTH, buttonPane);
		springLayoutContentPane.putConstraint(SpringLayout.NORTH, durationLabel, 0, SpringLayout.NORTH, timerLabel);
		springLayoutContentPane.putConstraint(SpringLayout.WEST, timerLabel, 33, SpringLayout.WEST, contentPane);
		springLayoutContentPane.putConstraint(SpringLayout.EAST, timerLabel, 91, SpringLayout.WEST, buttonPane);
		timerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(timerLabel);
		
		// Duration Label
		durationLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(durationLabel);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof TimerModel) {
			this.durationLabel.setText(((TimerModel) arg).timerToString());
			//System.out.println("View updated");
		}
		
	}

}
