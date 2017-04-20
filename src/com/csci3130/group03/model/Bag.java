package com.csci3130.group03.model;

import java.util.ArrayList;

public class Bag {

	private ArrayList<Letter> letters;

	public Bag() {
		this.letters = new ArrayList<Letter>();
		for(int i=0; i<100; i++) {
			if(i < 9 && i >= 0)
				letters.add(new Letter("a"));
			else if(i < 11 && i >= 9)
				letters.add(new Letter("b"));
			else if(i < 13 && i >= 11)
				letters.add(new Letter("c"));
			else if(i < 17 && i >= 13)
				letters.add(new Letter("d"));
			else if(i < 29 && i >= 17)
				letters.add(new Letter("e"));
			else if(i < 31 && i >= 29)
				letters.add(new Letter("f"));
			else if(i < 34 && i >= 31)
				letters.add(new Letter("g"));
			else if(i < 36 && i >= 34)
				letters.add(new Letter("h"));
			else if(i < 45 && i >= 36)
				letters.add(new Letter("i"));
			else if(i < 46 && i >= 45)
				letters.add(new Letter("j"));
			else if(i < 47 && i >= 46)
				letters.add(new Letter("k"));
			else if(i < 51 && i >= 47)
				letters.add(new Letter("l"));
			else if(i < 53 && i >= 51)
				letters.add(new Letter("m"));
			else if(i < 59 && i >= 53)
				letters.add(new Letter("n"));
			else if(i < 67 && i >= 59)
				letters.add(new Letter("o"));
			else if(i < 69 && i >= 67)
				letters.add(new Letter("p"));
			else if(i < 70 && i >= 69)
				letters.add(new Letter("q"));
			else if(i < 76 && i >= 70)
				letters.add(new Letter("r"));
			else if(i < 80 && i >= 75)
				letters.add(new Letter("s"));
			else if(i < 86 && i >= 80)
				letters.add(new Letter("t"));
			else if(i < 90 && i >= 86)
				letters.add(new Letter("u"));
			else if(i < 92 && i >= 90)
				letters.add(new Letter("v"));
			else if(i < 94 && i >= 92)
				letters.add(new Letter("w"));
			else if(i < 95 && i >= 94)
				letters.add(new Letter("x"));
			else if(i < 97 && i >= 95)
				letters.add(new Letter("y"));
			else if(i < 98 && i >= 97)
				letters.add(new Letter("z"));
			else if(i < 100 && i >= 98) 
				letters.add(new Letter(" "));
			
		}
	}
	
	public Bag(ArrayList<Letter> letters) {
		this.letters = new ArrayList<Letter>();
	}

	public ArrayList<Letter> getLetters() {
		return letters;
	}

	public void setLetters(ArrayList<Letter> letters) {
		this.letters = letters;
	}
	
	/* custom methods */	

	public int letterCheck(Letter letter) {
		String check = letter.getLetter();
		Letter curr;
		for(int i=0; i<100; i++) {
			curr = letters.get(i);
			if(check.equals(curr.getLetter()))
				return i;
			else
				curr = letters.get(i);
		}
		return -1;
	}
	
	public void removeLetter(Letter letter) {
		int index = letterCheck(letter);
		if (index != -1) {
			letters.remove(index); 
		}
	}
	
	public String enumerate() {
		String result = "";
		for(int i=0; i<letters.size(); i++) {
			result += letters.get(i) + ", ";
		}
		return result;
	}
	
	
}
