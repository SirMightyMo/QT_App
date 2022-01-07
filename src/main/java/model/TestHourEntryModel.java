package main.java.model;

import java.util.Observable;

@SuppressWarnings("deprecation")
public class TestHourEntryModel extends Observable {
	
	String data[] = null;
	

	public TestHourEntryModel() {
		
	}
	
	public void setData(String[] stringArray) {
		
		this.data = stringArray;
		setChanged();
		notifyObservers(this);	
	}

	public String[] getData() {
		return data;
	}
	
}
