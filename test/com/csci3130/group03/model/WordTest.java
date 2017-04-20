package com.csci3130.group03.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * Tests the Word class, ensuring that input and output are valid
 * 
 * The most important part is the formatted string parsing, and ensuring that
 * the correct score is returned
 *
 */
public class WordTest {

	/**
	 * Test that when a word is input that the same word is returned
	 */
	@Test
	public void testWordInput() {
		Word word = new Word("dogs");
		assertNotNull(word);
		assertEquals("dogs".toUpperCase(), word.getWord());
	}

	/**
	 * Test that when a word is input that the same word is returned without
	 * formatting
	 */
	@Test
	public void testFormattedWordInput() {
		Word word = new Word("2d2o2g2s2");
		assertNotNull(word);
		assertEquals("dogs".toUpperCase(), word.getWord());
	}

	/**
	 * Test that when a word is input that the proper score is returned
	 */
	@Test
	public void testNormalWordScore() {
		Word word = new Word("dogs");
		assertNotNull(word);
		assertEquals(6, word.getScore());
	}

	/**
	 * Test that when a formatted word is input that the proper score is
	 * returned
	 */
	@Test
	public void testDoubleWordScore() {
		Word word = new Word("2dogs");
		assertNotNull(word);
		assertEquals(12, word.getScore());
	}

	/**
	 * Test that when a formatted word is input that the proper score is
	 * returned
	 */
	@Test
	public void testTripleWordScore() {
		Word word = new Word("3dogs");
		assertNotNull(word);
		assertEquals(18, word.getScore());
	}

	/**
	 * Test that when a formatted word is input that the proper score is
	 * returned
	 */
	@Test
	public void testDoubleLetterScore() {
		Word word = new Word("d2ogs");
		assertNotNull(word);
		assertEquals(8, word.getScore());
	}

	/**
	 * Test that when a formatted word is input that the proper score is
	 * returned
	 */
	@Test
	public void testTripleLetterScore() {
		Word word = new Word("d3ogs");
		assertNotNull(word);
		assertEquals(10, word.getScore());
	}

	/**
	 * Test that when a formatted word is input that the proper score is
	 * returned
	 */
	@Test
	public void testBlankTile() {
		Word word = new Word("d0ogs");
		assertNotNull(word);
		assertEquals(4, word.getScore());
	}
}
