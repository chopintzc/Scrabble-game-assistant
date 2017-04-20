package com.csci3130.group03.utility;

/**
 * Utility class to implement a custom comparator for word Objects.
 * 
 * @author Stanford Lockhart, Eric Desjardins
 *
 */

import java.util.Comparator;

import com.csci3130.group03.model.Word;

public class WordScoreComparator implements Comparator<Word> {

	/**
	 * Score comparator for the Word class
	 * 
	 * @return 1 if the Word score is smaller, -1 if Word score is greater and 0 if equal
	 * @Override the PriorityQueue comparator
	 */
	@Override
	public int compare(Word o1, Word o2) {
		return o2.getScore() - o1.getScore();
	}

}