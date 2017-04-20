package com.csci3130.group03.model;

import java.util.ArrayList;

/**
 * 
 * The Word object holds an instance of a played word
 * When a word is input to the game, a Word object is created, consisting of Letter objects
 * The Score of the word depends entirely on the values of the letters and the modifiers on the word
 *
 */
public class Word {
	
	private ArrayList<Letter> letters;
	private ArrayList<Integer> modifiers;
	private int wordModifier;
	
	/**
	 * Use a formatted string to create a new word object
	 * Word strings have the following format:
	 * 		{word_modifier}[{letter}{letter_modifier}]+
	 * 		where the word_modifier in 1-3
	 * 		where the letter_modifier in 0-3, 0 being a blank tile
	 * @param word
	 */
	public Word(String word) {
		word = word.toUpperCase();
		int i = 0;
		wordModifier = 1;
		if (Character.isDigit(word.charAt(0))) {
			wordModifier = Math.min(Math.max(1, Integer.parseInt(word.charAt(0) + "")), 3);
			i++;
		}
		letters = new ArrayList<Letter>();
		modifiers = new ArrayList<Integer>();
		for (; i < word.length(); i++) {
			if (Character.isDigit(word.charAt(i))) {
				continue;
			}
			Letter letter = new Letter(word.charAt(i) + "");
			letters.add(letter);
			if (i+1 < word.length() && Character.isDigit(word.charAt(i+1))) {
				int mod = Integer.parseInt(word.charAt(i+1) + "");
				mod = Math.min(Math.max(0, mod), 3);
				modifiers.add(mod);
			} else {
				modifiers.add(1);
			}
		}
	}
	
	/**
	 * Returns the word without any of the modifiers
	 * @return the word as a string
	 */
	public String getWord() {
		StringBuilder str = new StringBuilder();
		for (Letter letter : letters) {
			str.append(letter.getLetter());
		}
		return str.toString();
	}
	
	/**
	 * Returns the word with all of the modifiers
	 * @return the word as a string
	 */
	public String getWordWithModifiers() {
		StringBuilder str = new StringBuilder();
		if (wordModifier != 1)
			str.append(wordModifier);
		for (int i = 0; i < letters.size(); i++) {
			str.append(letters.get(i).getLetter());
			if (modifiers.get(i) != 1) {
				str.append(modifiers.get(i));
			}
		}
		return str.toString();
	}
	
	/**
	 * Returns the score, built from letter score and modifiers
	 * @return the word score
	 */
	public int getScore() {
		int score = 0;
		for (int i = 0; i < letters.size(); i++) {
			score += (letters.get(i).getValue() * modifiers.get(i));
		}
		return score * wordModifier;
	}
	
}
