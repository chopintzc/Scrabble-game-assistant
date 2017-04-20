package com.csci3130.group03.component;

import com.csci3130.group03.model.Score;
import com.csci3130.group03.model.User;
import com.csci3130.group03.model.Word;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Component used to track scores of players playing a game of Scrabble.
 * 
 * @author Standford Lockhart & Luke Elwood
 * @since 2016-06-23
 *
 */
public class ScoreComponent {

	private Score score;

	private Table wordsTable;
	private TextField input;
	private Button submit;
//	private Button viewStats;

	/**
	 * Create a new component object that can then be used to retrieve and place
	 * on the page
	 * 
	 * @param user
	 */
	public ScoreComponent(User user) {
		this.score = new Score(user);

		this.wordsTable = new Table();
		this.wordsTable.setCaption(user.getUsername());
		this.wordsTable.addContainerProperty("Words", String.class, null);
		this.wordsTable.addContainerProperty("Scores", Integer.class, null);
		this.wordsTable.setColumnWidth("Words", 170);
		this.wordsTable.setColumnWidth("Scores", 100);
		this.wordsTable.setFooterVisible(true);
		this.wordsTable.setHeight(10, Unit.PERCENTAGE);
		updateScoreFooter();
		// Footer for score
		this.input = new TextField();
		this.submit = new Button("Submit");
		this.submit.addClickListener((event) -> {
			addWord(new Word(input.getValue()));
			if (user.getStats() != null)
				user.getStats().playedUserWord(new Word(input.getValue()));
			input.setValue("");
		});
	}

	private void updateScoreFooter() {
		wordsTable.setColumnFooter("Scores", "Score: " + score.getScore());
	}

	/**
	 * @return the score
	 */
	public Score getScore() {
		return score;
	}

	/**
	 * @param word
	 *            the word to add
	 */
	public void addWord(Word word) {
		this.score.addWord(word);
		wordsTable.addItem(new Object[] { word.getWord(), word.getScore() }, wordsTable.getItemIds().size());
		updateScoreFooter();
	}

	/**
	 * @return the wordsTable
	 */
	public Table getWordsTable() {
		return wordsTable;
	}

	/**
	 * Builds the component and returns it so that it can be placed in a layout
	 * 
	 * @return the component
	 */
	public Component getComponent() {
		VerticalLayout vert = new VerticalLayout();
		HorizontalLayout hori = new HorizontalLayout();
		hori.addComponent(input);
		hori.addComponent(submit);
		vert.addComponent(wordsTable);
		vert.addComponent(hori);
		return vert;
	}

}
