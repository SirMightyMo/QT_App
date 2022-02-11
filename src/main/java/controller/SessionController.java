package main.java.controller;

import java.awt.event.ActionEvent;

import javax.swing.event.DocumentEvent;

import main.java.model.IModel;
import main.java.model.SessionModel;
import main.java.view.IView;
import main.java.view.SessionView;

public class SessionController implements IController{
	

	private SessionView sessionView;
	private SessionModel sessionModel;
	
	public SessionController() {
		this.sessionModel = new SessionModel();
		this.sessionView = new SessionView(this);
		this.sessionModel.addObserver(this.sessionView);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
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
	public SessionView getSessionView() {
		return sessionView;
	}

	public void setSessionView(SessionView sessionView) {
		this.sessionView = sessionView;
	}

	
}
