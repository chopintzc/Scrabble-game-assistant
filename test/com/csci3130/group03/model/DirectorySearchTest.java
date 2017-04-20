package com.csci3130.group03.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import com.vaadin.testbench.TestBenchTestCase;

public class DirectorySearchTest extends TestBenchTestCase {
	
	@Test
	public void testWordLetterValidity() throws IOException {
		DirectorySearch dictionary = new DirectorySearch();
		ArrayList<Word> words = new ArrayList<Word>();
		
		String letters = "sea";
		
		words = dictionary.searchWord(letters, words);
		Boolean flag = true;
		
		for(int i=0; i<words.size()-1 && flag; i++) {
			String curr = words.get(i).getWord();
			for(int j=0; j<curr.length()-1 && flag; j++) {
				if(curr.charAt(j) != 'S' && curr.charAt(j) != 'E' && curr.charAt(j) != 'A') {
					flag = false;
				}
			}
		}
		
		assertEquals(true, flag);
	}

}
