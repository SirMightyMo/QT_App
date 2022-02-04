package main.java.controller;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import main.java.model.HourEntry;
import main.java.model.IModel;
import main.java.model.NewProjectModel;
import main.java.model.Service;
import main.java.model.StaticActions;
import main.java.model.TimerModel;
import main.java.model.User;
import main.java.view.IView;
import main.java.view.NewProjectView;
import main.java.view.TimerView;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NewProjectController implements IController {

	private NewProjectModel newProjectModel;
	private NewProjectView newProjectView;
	private HourEntry hourEntry;

	// Constructor
	@SuppressWarnings("deprecation")
	public NewProjectController() {
		this.newProjectModel = new NewProjectModel();
		this.newProjectView = new NewProjectView(this);
		this.newProjectModel.addObserver(this.newProjectView);
	}
	
	public NewProjectModel getNewProjectModel() {
		return newProjectModel;
	}

	public void setNewProjectModel(NewProjectModel newProjectModel) {
		this.newProjectModel = newProjectModel;
	}
	
	public NewProjectView getNewProjectView() {
		return newProjectView;
	}

	public void setNewProjectView(NewProjectView newProjectView) {
		this.newProjectView = newProjectView;
	}
	
	// ActionListener method
	@Override
	public void actionPerformed(ActionEvent e) {
		String event = e.getActionCommand();
		System.out.println("ACTION: " + event.toString()); // For debugging

		
	}

	@Override
	public IModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IView getView() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}
