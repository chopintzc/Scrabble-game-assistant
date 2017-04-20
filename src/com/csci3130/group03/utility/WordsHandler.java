/**
 * a couple of methods to handle the arraylist of words
 * 
 * @author Zhongchao
 * @since 2016-06-11
 */

package com.csci3130.group03.utility;

import java.io.IOException;
import java.util.ArrayList;

import com.csci3130.group03.model.Tray;
import com.csci3130.group03.model.Word;

public class WordsHandler extends java.lang.Object{
	
	int max;
	int pointer;
	Word tmp;

	public WordsHandler() {		
		max = 0;
		pointer = 0;
	}
	
	/**
	 * remove duplicated words from ArrayList<Word>
	 */
	public ArrayList<Word> removeDuplicate(ArrayList<Word> words){
		for (int i=0; i<words.size()-1; i++){
			for (int j=i+1; j<words.size(); j++){
				if (words.get(j).getWord().equals(words.get(i).getWord())){
					words.remove(j);
					j--;
				}
			}        		
		}
		
		return words;
	}
	
	/**
	 * sort words stored in ArrayList<Word>
	 */
	public ArrayList<Word> sort(ArrayList<Word> words){

		for (int i=0; i<words.size()-1; i++){
			pointer = i;
			max = words.get(i).getScore();
			for (int j=i+1; j<words.size(); j++){
				if (words.get(j).getScore() > max){
					max = words.get(j).getScore();
					pointer = j;
				}
			}
			tmp = words.remove(pointer);
			words.add(i, tmp);
		}
		
		return words;
	}
	
	/**
	 * create label based on ArrayList<Word> words
	 */
	public String generateLabel(ArrayList<Word> words){
		String lbl = "";
		
		if (words.size() > 0) {
			lbl = words.get(0).getWord() + '\n' + words.get(0).getScore() + '\n';
			for (int i=1; i<words.size(); i++){
        		lbl = lbl + words.get(i).getWord() + '\n' + words.get(i).getScore() + '\n';
        	}
		}
		
		return lbl;
	}
	
	/**
	 * retrieve all letters from userTray
	 */
	public String getWord(Tray userTray){
		int numLetters = userTray.getTray().size();
    	String word = userTray.getTray().get(0).getLetter().toLowerCase();
    	for (int i=1; i<numLetters; i++){
    		String letter = userTray.getTray().get(i).getLetter();
    		if (letter.equals("")){
    			word = word + " ";
    		}
    		else {
    			word = word + userTray.getTray().get(i).getLetter().toLowerCase();
    		}
    	}
		
		return word;
	}
	
	/**
	 * search for all the blanks from the letters retrieved from
	 * userTray
	 */
	public ArrayList<Integer> searchBlank(String word){
		ArrayList<Integer> blank = new ArrayList<Integer>();
		int count = 0;
		for (int i=0; i<word.length(); i++){
			if (Character.isWhitespace(word.charAt(i))){
				blank.add(i);
				count++;
			}
		}
		
		return blank;
	}
	
	/**
	 * to handle wildcard situation, replace blanks with a letter from
	 * a to z
	 */
	public String wildCard(String word, ArrayList<Integer> blank, int num){
		String words = "";
		int index;
		int base;
		int pos1=0, pos2;
		for (int i = 0; i<blank.size(); i++){
			base = (int)Math.pow(26, i);
			pos2 = blank.get(i);
			index = (int)Math.floor(num / base);
			index = index % 26;
			words = words+word.substring(pos1,pos2)+((char)(97+index));
			pos1 = pos2+1;
		}
		if (pos1<word.length()){
			words = words + word.substring(pos1,word.length()); 
		}
		
		return words;
	}
	
}
