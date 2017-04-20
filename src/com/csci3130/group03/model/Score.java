package com.csci3130.group03.model;

import java.util.ArrayList;

/**
 * 
 * Every User in the game has a single Score object, consisting of all of their played words
 * The User's score is then the sum of all of those words
 *
 */
public class Score {
	
	private User user;
	private ArrayList<Word> words;
	
	/**
	 * Every Score object has a user, and must have one to be valid
	 * @param user
	 */
	public Score(User user) {
		this.user = user;
		words = new ArrayList<Word>();
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return the words
	 */
	public ArrayList<Word> getWords() {
		return words;
	}

	/**
	 * @param word the word to add
	 */
	public void addWord(Word word) {
		this.words.add(word);
	}
	
	/**
	 * @return the user's score
	 */
	public int getScore() {
		int score = 0;
		for (Word w : words)
			score += w.getScore();
		return score;
	}
}
