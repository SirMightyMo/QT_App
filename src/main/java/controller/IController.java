package main.java.controller;

import java.awt.event.ActionListener;

import javax.swing.event.DocumentListener;

import main.java.model.IModel;
import main.java.view.IView;

public interface IController extends ActionListener, DocumentListener {

	/**
	 * This Interface is used to implement the Strategy Pattern (behavioral).
	 * Every view needs to have a controller assigned, that implements this
	 * interface. That way different controllers can be used for the same
	 * view classes.
	 */
	
	public IModel getModel();
	public IView getView();
	
}
