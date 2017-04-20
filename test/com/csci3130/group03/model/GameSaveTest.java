package com.csci3130.group03.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GameSaveTest {

	GameSave save;
	
	@Before
	public void init() {
		User user1 = new User("one", "one");
		User user2 = new User("two", "two");
		User user3 = new User("three", "three");
		User user4 = new User("four", "four");
		
		Word word = new Word("D2OG");
		
		ArrayList<Letter> tray = new ArrayList<Letter>();
		tray.add(new Letter("A"));
		tray.add(new Letter("B"));
		tray.add(new Letter("C"));
		
		ArrayList<Score> scores = new ArrayList<Score>();
		Score score1 = new Score(user1);
		score1.addWord(word);
		Score score2 = new Score(user2);
		score2.addWord(word);
		Score score3 = new Score(user3);
		score3.addWord(word);
		Score score4 = new Score(user4);
		score4.addWord(word);
		scores.add(score1);
		scores.add(score2);
		scores.add(score3);
		scores.add(score4);
		
		save = new GameSave(tray, scores);
	}
	
	@Test
	public void testTray() {
		String tray = save.getTray();
		assertEquals(tray, "A,B,C,");
	}
	
	@Test
	public void testUsernames() {
		String player1 = save.getPlayer1_username();
		String player2 = save.getPlayer2_username();
		String player3 = save.getPlayer3_username();
		String player4 = save.getPlayer4_username();
		assertEquals(player1, "one");
		assertEquals(player2, "two");
		assertEquals(player3, "three");
		assertEquals(player4, "four");
	}
	
	@Test
	public void testWords() {
		String player1 = save.getPlayer1_words();
		String player2 = save.getPlayer2_words();
		String player3 = save.getPlayer3_words();
		String player4 = save.getPlayer4_words();
		assertEquals(player1, "D2OG,");
		assertEquals(player2, "D2OG,");
		assertEquals(player3, "D2OG,");
		assertEquals(player4, "D2OG,");
	}
}
