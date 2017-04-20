package com.csci3130.group03.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.csci3130.group03.utility.WordScoreComparator;

/**
 * Model for tracking user statistics related to the game.
 * 
 * 
 * @author Stanford Lockhart & Eric Desjardins
 * @since 2016-06-23
 *
 */

@Entity
public class Stats {

	/**
	 * 
	 * int wins int losses Word best word frequency would percentage based
	 * 
	 * Tasks: Convert Letters to JPA Convert Word to JPA
	 * 
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Transient
	private Comparator<Word> comparator;

	private int totalWins;
	private int totalLosses;
	private int totalLettersPlayed;
	private int totalPoints;
	// private PriorityQueue<Word> highestScoringWordsPlayed = new
	// PriorityQueue<Word>(10, comparator);
	private ArrayList<Word> highestScoringWordsPlayed = new ArrayList<Word>();
	private double[] lettersPlayedCount = new double[26];
	private double[] lettersPlayedFrequency = new double[26];

	/**
	 * Create a new Stats table
	 * 
	 */
	public Stats() {
		comparator = new WordScoreComparator();
	}

	/**
	 * 
	 * @return the total number of wins for a user
	 */
	public int getTotalWins() {
		return totalWins;
	}

	/**
	 * Increment the number of wins for a given user
	 * 
	 */
	public void addWin() {
		this.totalWins++;
	}

	/**
	 * 
	 * @return the total lifetime losses for a user
	 */
	public int getTotalLosses() {
		return totalLosses;
	}

	/**
	 * Increment the number of losses for a given user
	 * 
	 */
	public void addLoss() {
		this.totalLosses++;
	}

	/**
	 * 
	 * @return the lifetime score of a user
	 */
	public int getTotalPoints() {
		return totalPoints;
	}

	/**
	 * 
	 * @return the lifetime number of letters played by a user
	 */
	public int getTotalLettersPlayed() {
		return totalLettersPlayed;
	}

	/**
	 * A priority queue containing the sorted Word score (high-low).
	 * 
	 * @return the PriorityQueue<Word> sorted by scores (high-low)
	 */
	public ArrayList<Word> getHighestScoringWordsPlayed() {

		ArrayList<Word> sortedListOfWords = (ArrayList<Word>) this.highestScoringWordsPlayed.clone();

		sortedListOfWords.sort(new WordScoreComparator());
		
		return sortedListOfWords;
	}

	/**
	 * An array containing the frequency that a player played a particular
	 * letter (times letter player / total letters played)
	 * 
	 * @return double[] containing the frequency of letters played
	 */
	public double[] getLettersPlayedFrequency() {
		// Avoid dividing by 0
		if (totalLettersPlayed > 0) {
			for (int i = 0; i < lettersPlayedCount.length; i++) {
				lettersPlayedFrequency[i] = lettersPlayedCount[i] / totalLettersPlayed;
			}

			return lettersPlayedFrequency;
		}

		return lettersPlayedFrequency;
	}

	/**
	 * Primary method used for the class. To be used every time a user plays a
	 * word. The method will update the frequency of letters played, lifetime
	 * points and track highest scoring word. The method takes in a Word object.
	 * 
	 * @param word
	 */
	public void playedUserWord(Word word) {
		int charASCIIValue;

		totalLettersPlayed += word.getWord().length();

		for (char c : word.getWord().toCharArray()) {
			charASCIIValue = normalizeASCIILetter(c);
			if (charASCIIValue != 0) {
				lettersPlayedCount[charASCIIValue - 65] += 1;
			}
		}

		this.totalPoints += word.getScore();
		highestScoringWordsPlayed.add(word);

	}

	private int normalizeASCIILetter(int c) {
		if (c >= (int) 'A' && c <= (int) 'Z') {
			return c;
		}

		if (c >= (int) 'a' && c <= (int) 'z') {
			return c - 32;
		}

		return 0;
	}

	public String printTopTenWords() {

		String topWordsBoard = "";
		Word queuedWord;
		int position = 1;

		if (getHighestScoringWordsPlayed().size() > 0) {

			for (Word word : getHighestScoringWordsPlayed()) {
				queuedWord = word;
				topWordsBoard += position + ". Word: " + queuedWord.getWord() + " Score:" + queuedWord.getScore() + "\n";
				position++;
			}
			return topWordsBoard;

		}

		return "No words on record!\n";
	}

	public String toString() {
		return "Win: " + getTotalWins() + " Loss: " + getTotalLosses() + "\nLifetime points: " + getTotalPoints() + "\n"
				+ printTopTenWords();
	}

}
