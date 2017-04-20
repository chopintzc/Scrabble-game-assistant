package com.csci3130.group03.model;

import java.util.ArrayList;

public class Tray {
	
	ArrayList<Letter> tray;

	public Tray(ArrayList<Letter> tray) {
		this.tray = tray;
	}

	public Tray() {
		tray = new ArrayList<Letter>(7);
	}

	public ArrayList<Letter> getTray() {
		return tray;
	}

	public void setTray(ArrayList<Letter> tray) {
		this.tray = tray;
	}
	
	public void addToTray(String e) {
		tray.add(new Letter(e));
	}
	
	public void remove(int index) {
		tray.remove(index);
	}
	
	public void remove(String letter) {
		int i = 0;
		boolean flag = true;
		while(flag && i < tray.size()) {
			if(tray.get(i).getLetter().equals(letter)) {
				tray.remove(i);
				flag = false;
				i = tray.size();
			}
			i++;
		}
	}
	
	public boolean isFull() {
		return tray.size() >= 7;
	}
}
