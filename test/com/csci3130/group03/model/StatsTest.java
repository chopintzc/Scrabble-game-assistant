package com.csci3130.group03.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * Test for the stats class.
 * 
 * 
 * @author Stanford Lockhart & Eric Desjardins
 * @since 2016-06-23
 *
 */

public class StatsTest extends TestBenchTestCase {

	int win;
	int loss;

	Stats stats;

	/**
	 * Test the addWin method properly increments the victory count.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddWin() throws Exception {
		// Create new stats
		stats = new Stats();

		assertEquals(0, stats.getTotalWins());

		stats.addWin();

		// User name match
		assertEquals(1, stats.getTotalWins());
	}

	/**
	 * Test the addWin method properly increments the victory count.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddLoss() throws Exception {
		// Create new stats
		stats = new Stats();

		assertEquals(0, stats.getTotalLosses());

		stats.addLoss();

		// User name match
		assertEquals(1, stats.getTotalLosses());
	}

	/**
	 * Test adding a new word to the stats class. Inherently it should be the
	 * first word in the priority queue and the stats should be retrivable.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testAddNewWord() throws Exception {
		// Create new stats
		stats = new Stats();

		Word word1 = new Word("dogs");

		stats.playedUserWord(word1);

		assertEquals(word1.getWord(), stats.getHighestScoringWordsPlayed().get(0).getWord());
		assertEquals(word1.getScore(), stats.getHighestScoringWordsPlayed().get(0).getScore());
	}

	/**
	 * Track the total number of letters played by a player.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalLettersPlayed() throws Exception {
		// Create new stats
		stats = new Stats();

		Word word1 = new Word("xylophone");

		stats.playedUserWord(word1);

		assertEquals(word1.getWord().length(), stats.getTotalLettersPlayed());
	}

	/**
	 * Insert an initial low value word followed by a higher value word. In the
	 * end, the higher value word should be at the start of the queue.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInsertingHigherValueWord() throws Exception {
		// Create new stats
		stats = new Stats();

		Word word1 = new Word("dogs");

		stats.playedUserWord(word1);

		assertEquals(word1.getWord(), stats.getHighestScoringWordsPlayed().get(0).getWord());

		Word word2 = new Word("xylophone");

		stats.playedUserWord(word2);

		assertEquals(word2.getWord(), stats.getHighestScoringWordsPlayed().get(0).getWord());

	}

	/**
	 * Insert an initial high value word followed by a low value word. In the
	 * end, the higher value word should be at the start of the queue.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInsertingLowerValueWord() throws Exception {
		// Create new stats
		stats = new Stats();

		Word word1 = new Word("xylophone");

		stats.playedUserWord(word1);

		assertEquals(word1.getWord(), stats.getHighestScoringWordsPlayed().get(0).getWord());

		Word word2 = new Word("dogs");

		stats.playedUserWord(word2);

		assertEquals(word1.getWord(), stats.getHighestScoringWordsPlayed().get(0).getWord());

	}

	/**
	 * Test the letter frequency of a word. The test passes in the alphabet into
	 * the 'word1' Word object. The sum of all frequencies should be 0.99+ (near
	 * 1)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetLettersPlayedFrequency() throws Exception {
		// Create new stats
		stats = new Stats();

		Word word1 = new Word("abcdefghijklmnopqrstuvwxyz");

		stats.playedUserWord(word1);

		double[] test = stats.getLettersPlayedFrequency();

		double totalFrequency = 0;
		for (int i = 0; i < test.length; i++) {
			totalFrequency += test[i];
		}

		assertTrue(totalFrequency > 0.99);
	}

	/**
	 * Test a user playing a word and check if the lifetime points has been
	 * incremented properly.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGetTotalPoints() throws Exception {
		// Create new stats
		stats = new Stats();

		Word word1 = new Word("dogs");

		stats.playedUserWord(word1);

		assertEquals(word1.getScore(), stats.getTotalPoints());
	}

}
