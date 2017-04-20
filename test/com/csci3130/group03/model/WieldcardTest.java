package com.csci3130.group03.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.csci3130.group03.utility.WordsHandler;
import com.vaadin.testbench.TestBenchTestCase;

public class WieldcardTest extends TestBenchTestCase{
	/**
	 * Test that when a wildcard word is input, the blank can be replaced
	 * with proper letters
	 */
	@Test
	public void testwildcardLetterValidity() throws IOException {
		WordsHandler wordshandler = new WordsHandler();		
		
		String word = "s e a";
		String searchword;
		ArrayList<Integer> blank = new ArrayList<Integer>();
		blank.add(1);
		blank.add(3);
		
		int count = (int)Math.pow(26, blank.size());
		
		searchword = wordshandler.wildCard(word, blank, 31);
		
		assertEquals(searchword,"sfeba");
	}
	
	/**
	 * Test that when a wildcard word is input, the blank can be identified
	 * correctly
	 */
	@Test
	public void testwildcardBlankValidity() throws IOException {
		WordsHandler wordshandler = new WordsHandler();		
		ArrayList<Integer> blank;
		
		String word = "s e a";
		
		blank = wordshandler.searchBlank(word);
		
		assertEquals((int)blank.get(0), 1);
		assertEquals((int)blank.get(1), 3);
	}
}
