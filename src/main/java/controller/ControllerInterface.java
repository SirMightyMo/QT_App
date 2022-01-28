package main.java.controller;

import java.awt.event.ActionListener;

import javax.swing.event.DocumentListener;

public interface ControllerInterface extends ActionListener, DocumentListener {

	/*
	 * This Interface is used to implement the Strategy Pattern (behavioral).
	 * Every view needs to have a controller assigned, that implements this
	 * interface. That way different controllers can be used for the same
	 * view classes.
	 */
	
}
