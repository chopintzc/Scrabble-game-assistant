package com.csci3130.group03.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

/**
 * 
 * Tests the Score class, ensuring that input and output are valid
 * 
 * The most important part is that when the words are entered, the resulting
 * user score is the same as the sum of those words
 *
 */
public class ScoreTest {

	/**
	 * Test that when we create a Score object, the user is retrievable and
	 * valid
	 */
	@Test
	public void testUserRetrieval() {
		Score score = new Score(new User("test", "test"));
		assertNotNull(score);
		assertNotNull(score.getUser());
		assertEquals("test", score.getUser().getUsername());
	}

	/**
	 * Test that when words are added, that the right amount of words are added
	 * and they are in the correct order
	 */
	@Test
	public void testWordRetrieval() {
		Word word1 = new Word("dogs");
		Word word2 = new Word("cats");
		Score score = new Score(new User("test", "test"));
		score.addWord(word1);
		score.addWord(word2);
		assertNotNull(score);
		assertNotNull(score.getWords());
		assertEquals(2, score.getWords().size());
		assertEquals("dogs".toUpperCase(), score.getWords().get(0).getWord());
	}

	/**
	 * Test that when words are added, the resulting Score object's score should
	 * be the same as the sum of the input words
	 */
	@Test
	public void testScoreRetrieval() {
		Word word1 = new Word("dogs");
		Word word2 = new Word("cats");
		Score score = new Score(new User("test", "test"));
		int expectedScore = word1.getScore() + word2.getScore();
		score.addWord(word1);
		score.addWord(word2);
		assertNotNull(score);
		assertEquals(expectedScore, score.getScore());
	}
}
