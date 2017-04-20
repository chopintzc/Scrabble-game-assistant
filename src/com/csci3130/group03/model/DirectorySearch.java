/**
 * search the directory for the valid match of letter combinations
 * 
 * @author Zhongchao
 * @since 2016-06-15
 */

package com.csci3130.group03.model;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashSet;

public final class DirectorySearch extends java.lang.Object {
	Scanner in;
	HashSet<String> dictionary;
	
	/**
	 * constructor, initialize the dictionary
	 * @throws IOException
	 */
	public DirectorySearch() throws IOException {

		URL url = new URL("https://raw.githubusercontent.com/jonbcard/scrabble-bot/master/src/dictionary.txt");
		in = new Scanner(url.openStream());
		
		dictionary = new HashSet<String>();

		while (in.hasNextLine()) {
			dictionary.add(in.nextLine().toLowerCase());
		}

		in.close();

	}
	
	/**
	 * initial call of the recursive permutation function call empty string is
	 * the initial input
	 * 
	 * @param str
	 *            String input to permutate method
	 * @param words
	 *            Arraylist of positive search result
	 * @throws IOException
	 */
	public ArrayList<Word> permutation(String str, ArrayList<Word> words) throws IOException {
		permutation("", str, words);
		return words;
	}

	/**
	 * recursive permutation call
	 * 
	 * @param prefix
	 *            prefix of string
	 * @param str
	 *            remaining string not in the prefix
	 * @param words
	 *            Arraylist of positive search result
	 * @throws IOException
	 */
	private void permutation(String prefix, String str, ArrayList<Word> words) throws IOException {
		int n = str.length();
		if (n == 0) {			
		} else {
			for (int i = 0; i < n; i++) {
				permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), words);
				if (dictionary.contains(prefix + str.charAt(i))) {
					words.add(new Word(prefix + str.charAt(i)));
				}
			}
		}
	}

	/**
	 * pick up all the possible combinations of letters from the letters
	 * retrieved from the userTray 
	 * @param str
	 *            String input to searchWord method
	 * @param words
	 *            Arraylist of positive search result
	 * @return return words which is Arraylist of positive search result
	 * @throws IOException
	 */
	public ArrayList<Word> searchWord(String str, ArrayList<Word> words) throws IOException {
		int cnt;
		for (int i = 1; i < Math.pow(2, str.length()); i++) {
			String string = "";
			cnt = i;
			for (int j = 1; j <= str.length(); j++) {
				if ((cnt % 2) == 1) {
					string = string + str.charAt(str.length() - j);
				}
				cnt = (int) Math.floor(cnt / 2);
			}
			permutation(string, words);
		}

		return words;
	}
}
