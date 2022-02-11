package main.java.controller;

import java.awt.event.ActionEvent;

import javax.swing.event.DocumentEvent;

import main.java.model.AccountModel;
import main.java.model.IModel;
import main.java.model.TimerModel;
import main.java.model.User;
import main.java.view.AccountView;
import main.java.view.IView;
import main.java.view.TimerView;

public class AccountController implements IController {
	
	private AccountView accountView;
	private AccountModel accountModel;

	@SuppressWarnings("deprecation")
	public AccountController() {
		this.accountModel = new AccountModel();
		this.accountView = new AccountView(this);
		this.accountModel.addObserver(this.accountView);
	}
	public AccountView getAccountView() {
		return accountView;
	}

	public void setAccountView(AccountView accountView) {
		this.accountView = accountView;
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
	
}
