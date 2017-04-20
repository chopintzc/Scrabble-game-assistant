package com.csci3130.group03.service;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.csci3130.group03.model.GameSave;
import com.csci3130.group03.model.Letter;
import com.csci3130.group03.model.Score;
import com.csci3130.group03.model.User;
import com.csci3130.group03.model.Word;

public class GameSaveServiceTest {

	GameSaveService service;
	String username;
	User user;
	List<Letter> tray;
	List<Score> scores;
	String word;
	
	@Before
	public void init() {
		service = new GameSaveService();
		username = "testuser";
		user = new User(username, "testpassword");
		tray = new ArrayList<Letter>();
		tray.add(new Letter("A"));
		tray.add(new Letter("B"));
		scores = new ArrayList<Score>();
		Score score = new Score(user);
		word = "DOG";
		score.addWord(new Word(word));
		scores.add(score);
	}
	
	@Test
	public void testSaveGame() {
		service.saveGame(tray, scores);
		GameSave res = service.getSavedGame(user);
		assertTrue(res.getTray().contains("A"));
		assertTrue(res.getTray().contains("B"));
		assertTrue(res.getPlayer1_username().equals(username));
		assertTrue(res.getPlayer1_words().contains(word));
	}
}
