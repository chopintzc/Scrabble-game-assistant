/**
 * 
 * @author Zhongchao
 * @since 2016-06-08
 */

package com.csci3130.group03.model;

import java.io.IOException;
import java.util.ArrayList;

import com.csci3130.group03.utility.WordsHandler;

public class Search {
	WordsHandler wordshandler;
	ArrayList<Word> words;
	String searchword;
	DirectorySearch dictionarysearch;
	
	public Search(){
		wordshandler = new WordsHandler();
		words = new ArrayList<Word>();
		try{
			dictionarysearch = new DirectorySearch();
		}catch (Exception e){}
	}
	
	/**
	 * respond to the click of search button in the GameView
	 * @param word 
	 * 				input string
	 * @return words
	 * 				an ArrayList<Word> of search result
	 */	
	public ArrayList<Word> searchListern(String word){

	 	ArrayList<Integer> blank = wordshandler.searchBlank(word);
		if (blank.size() > 0) {
			int count = (int)Math.pow(26, blank.size());
			for (int i=0; i<count; i++){
				searchword = wordshandler.wildCard(word, blank, i);
				try {
					//words = dictionarysearch.searchWord(searchword, words);
					words = dictionarysearch.permutation(searchword, words);
				}catch(Exception e){}
			}
		}
		else {
			try {
				//words = dictionarysearch.searchWord(word, words);
				words = dictionarysearch.permutation(word, words);
			}catch(Exception e){}
		}	        		
    	        	
    	words = wordshandler.removeDuplicate(words);
    	words = wordshandler.sort(words);
    	
		return words;
	}
}
