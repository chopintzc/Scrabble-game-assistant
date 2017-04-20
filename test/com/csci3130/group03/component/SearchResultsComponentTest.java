package com.csci3130.group03.component;

import static org.junit.Assert.*;

import org.junit.Test;

import com.csci3130.group03.model.Word;
import com.csci3130.group03.view.GameView;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;

public class SearchResultsComponentTest {

	
	
	
	/**
	 * Tests Search Results Component
	 * 
	 * @author Luke Elwood
	 * @throws Exception
	 */
	@Test 
	public void testSearchComponent(){
		SearchResultsComponent s = new SearchResultsComponent();
		s.addWord(new Word("test"));
		s.addWord(new Word("word"));
		Table t = s.getTable();
		boolean test = false, words = false, extra = false;
		for (Object i : t.getItemIds()) {
			// Item itm = table.getItem(i);
			String word = (String) t.getContainerProperty(i, "Words").getValue();
			if (word.equals("test".toUpperCase())) {
				test = true;
			} else if (word.equals("word".toUpperCase())) {
				words = true;
			} else
				extra = true;
		}
		assertTrue(test);
		assertTrue(words);
		assertFalse(extra);
	}
}
