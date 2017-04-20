/**
 * 
 * @author Zhongchao Tan
 * @since 2016-06-13
 */

package com.csci3130.group03.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import com.csci3130.group03.utility.WordsHandler;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * This class contains JUnit tests, which are run using Vaadin TestBench 4.
 *
 * To run this, first get an evaluation license from
 * https://vaadin.com/addon/vaadin-testbench and follow the instructions at
 * https://vaadin.com/directory/help/installing-cval-license to install it.
 *
 * Once the license is installed, you can run this class as a JUnit test.
 */
public class WordsHandlerTest extends TestBenchTestCase {
	
	WordsHandler wordsHandler = new WordsHandler();


	/**
	 * Tests word rank by score
	 * 
	 * @throws Exception
	 */
	@Test
	public void testWordRank() {
		ArrayList<Word> words = new ArrayList<Word>();
		String[] wordStrings = {"horse", "oh", "forever", "a", "zebras"};
		
		for(int i =0; i<wordStrings.length; i++)
			words.add(new Word(wordStrings[i]));
		
		wordsHandler.sort(words);
		
		int correct = 0;
		
		if(words.get(0).getWord().equalsIgnoreCase("zebras"))
			correct++;
		if(words.get(1).getWord().equalsIgnoreCase("forever"))
			correct++;
		if(words.get(2).getWord().equalsIgnoreCase("horse"))
			correct++;
		if(words.get(3).getWord().equalsIgnoreCase("oh"))
			correct++;
		if(words.get(4).getWord().equalsIgnoreCase("a"))
			correct++;
		
		for(int i=0; i<5; i++)
			System.out.println(""+words.get(i).getWord());
			
		assertEquals(5, correct);
	}

}