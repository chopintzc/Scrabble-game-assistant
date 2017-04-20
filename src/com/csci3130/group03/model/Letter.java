package com.csci3130.group03.model;

public class Letter {

	private String letter;
	private int value;

	public Letter() {
	}

	public Letter(String letter) {
		letter = letter.toUpperCase();
		this.letter = letter;
		if(letter.equals("A") || letter.equals("E") || letter.equals("I") || letter.equals("O") || letter.equals("U") || letter.equals("L") || letter.equals("S") || letter.equals("N") || letter.equals("R") || letter.equals("T") || letter.equals("A"))
			this.value = 1;
		else if(letter.equals("G") || letter.equals("D"))
			this.value = 2;
		else if(letter.equals("B") || letter.equals("C") || letter.equals("M") || letter.equals("P"))
			this.value = 3;
		else if(letter.equals("F") || letter.equals("H") || letter.equals("V") || letter.equals("W") || letter.equals("Y"))
			this.value = 4;
		else if(letter.equals("K"))
			this.value = 5;
		else if(letter.equals("J") || letter.equals("X"))
			this.value = 8;
		else if(letter.equals("Q") || letter.equals("Z"))
			this.value = 10;
		else if(letter.equals("") || letter.equals(" "))
			this.value = 0;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter.toUpperCase();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return letter;
	}
	
}