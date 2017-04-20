package com.csci3130.group03.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.csci3130.group03.model.User;
import com.csci3130.group03.model.Word;
import com.vaadin.ui.Table;

/**
 * 
 * Tests the ScoreComponent class, and ensures that all of the functions that
 * are part of this object are working correctly. The input field should input a
 * word and display it in the table, and the score footer should always be the
 * correct total score.
 * 
 * @author Stanford Lockhart, Luke Elwood
 *
 */
public class ScoreComponentTest {

	/**
	 * Test that when we create a new score component with a user, that user is
	 * displayed at the top of the table
	 */
	@Test
	public void testCorrectUser() {
		ScoreComponent comp = new ScoreComponent(new User("test", "test"));
		assertNotNull(comp);
		assertEquals("test", comp.getScore().getUser().getUsername());
		assertEquals("test", comp.getWordsTable().getCaption());
	}

	/**
	 * Test that when new words are entered into the score component, they
	 * appear in the table
	 */
	@Test
	public void testCorrectWords() {
		ScoreComponent comp = new ScoreComponent(new User("test", "test"));
		comp.addWord(new Word("dogs"));
		comp.addWord(new Word("cats"));
		Table table = comp.getWordsTable();
		boolean inDogs = false, inCats = false;
		for (Object i : table.getItemIds()) {
			// Item itm = table.getItem(i);
			String word = (String) table.getContainerProperty(i, "Words").getValue();
			if (word.equals("dogs".toUpperCase())) {
				inDogs = true;
			} else if (word.equals("cats".toUpperCase())) {
				inCats = true;
			}
		}
		assertTrue(inDogs);
		assertTrue(inCats);
	}

	/**
	 * Test that when new words are updated, the scores footer reflects the
	 * proper new score
	 */
	@Test
	public void testCorrectScore() {
		ScoreComponent comp = new ScoreComponent(new User("test", "test"));
		comp.addWord(new Word("dogs"));
		comp.addWord(new Word("cats"));
		Table table = comp.getWordsTable();
		int expectedScore = comp.getScore().getScore();
		String foot = table.getColumnFooter("Scores");
		assertTrue(foot.contains(expectedScore + ""));
	}
}
