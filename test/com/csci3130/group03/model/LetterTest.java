package com.csci3130.group03.model;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

/**
 * Test the score retrieval of the letter model.
 * 
 * @author Eric Desjardins
 *
 */
public class LetterTest {

	Letter letter;
	
	/**
	 * Retrieve a one point score
	 * 
	 */
	@Test
	public void testOnePointLetter() {
		letter = new Letter("A");
		assertEquals(letter.getValue(), 1);
	}
	
	/**
	 * Retrieve a two point score
	 * 
	 */
	@Test
	public void testTwoPointLetter() {
		letter = new Letter("D");
		assertEquals(letter.getValue(), 2);
	}
	
	/**
	 * Retrieve a three point score
	 * 
	 */
	@Test
	public void testThreePointLetter() {
		letter = new Letter("B");
		assertEquals(letter.getValue(), 3);
	}
	
	/**
	 * Retrieve a four point score
	 * 
	 */
	@Test
	public void testFourPointLetter() {
		letter = new Letter("F");
		assertEquals(letter.getValue(), 4);
	}
	
	/**
	 * Retrieve a ten point score
	 * 
	 */
	@Test
	public void testTenPointLetter() {
		letter = new Letter("Z");
		assertEquals(letter.getValue(), 10);
	}

}
