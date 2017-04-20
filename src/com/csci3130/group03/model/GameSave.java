package com.csci3130.group03.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Entity for persisting a single game save Saves the username attached as well
 * as the tray and words that have been played
 * 
 * @author lockhart & dunn
 *
 */
@Entity
public class GameSave {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String tray;

	@NotNull
	private String player1_username;
	private String player2_username;
	private String player3_username;
	private String player4_username;

	@NotNull
	private String player1_words;
	private String player2_words;
	private String player3_words;
	private String player4_words;

	/**
	 * Empty constructor
	 */
	public GameSave() {

	}

	/**
	 * Create a new save with the tray and all of the scores
	 * Only uses up to 4 of the passed scores, the rest are discarded
	 * @param tray
	 * @param scores
	 */
	public GameSave(List<Letter> tray, List<Score> scores) {
		this.tray = "";
		for (Letter l : tray) {
			this.tray += l.getLetter() + ",";
		}
		for (int i = 0; i < Math.min(4, scores.size()); i++) {
			String username = scores.get(i).getUser().getUsername();
			String words = "";
			for (Word w : scores.get(i).getWords()) {
				words += w.getWordWithModifiers() + ",";
			}
			switch (i) {
			case 0:
				this.player1_username = username;
				this.player1_words = words;
				break;
			case 1:
				this.player2_username = username;
				this.player2_words = words;
				break;
			case 2:
				this.player3_username = username;
				this.player3_words = words;
				break;
			case 3:
				this.player4_username = username;
				this.player4_words = words;
				break;
			}
		}
	}

	/**
	 * @return the tray
	 */
	public String getTray() {
		return tray;
	}
	
	/**
	 * @return the list of all usernames in the save
	 */
	public List<String> getUsernames() {
		ArrayList<String> usernames = new ArrayList<String>();
		if (player1_username != null)
			usernames.add(player1_username);
		if (player2_username != null)
			usernames.add(player2_username);
		if (player3_username != null)
			usernames.add(player3_username);
		if (player4_username != null)
			usernames.add(player4_username);
		return usernames;
	}

	/**
	 * @param tray
	 *            the tray to set
	 */
	public void setTray(String tray) {
		this.tray = tray;
	}

	/**
	 * @return the player1_username
	 */
	public String getPlayer1_username() {
		return player1_username;
	}

	/**
	 * @param player1_username
	 *            the player1_username to set
	 */
	public void setPlayer1_username(String player1_username) {
		this.player1_username = player1_username;
	}

	/**
	 * @return the player2_username
	 */
	public String getPlayer2_username() {
		return player2_username;
	}

	/**
	 * @param player2_username
	 *            the player2_username to set
	 */
	public void setPlayer2_username(String player2_username) {
		this.player2_username = player2_username;
	}

	/**
	 * @return the player3_username
	 */
	public String getPlayer3_username() {
		return player3_username;
	}

	/**
	 * @param player3_username
	 *            the player3_username to set
	 */
	public void setPlayer3_username(String player3_username) {
		this.player3_username = player3_username;
	}

	/**
	 * @return the player4_username
	 */
	public String getPlayer4_username() {
		return player4_username;
	}

	/**
	 * @param player4_username
	 *            the player4_username to set
	 */
	public void setPlayer4_username(String player4_username) {
		this.player4_username = player4_username;
	}

	/**
	 * @return the player1_words
	 */
	public String getPlayer1_words() {
		return player1_words;
	}

	/**
	 * @param player1_words
	 *            the player1_words to set
	 */
	public void setPlayer1_words(String player1_words) {
		this.player1_words = player1_words;
	}

	/**
	 * @return the player2_words
	 */
	public String getPlayer2_words() {
		return player2_words;
	}

	/**
	 * @param player2_words
	 *            the player2_words to set
	 */
	public void setPlayer2_words(String player2_words) {
		this.player2_words = player2_words;
	}

	/**
	 * @return the player3_words
	 */
	public String getPlayer3_words() {
		return player3_words;
	}

	/**
	 * @param player3_words
	 *            the player3_words to set
	 */
	public void setPlayer3_words(String player3_words) {
		this.player3_words = player3_words;
	}

	/**
	 * @return the player4_words
	 */
	public String getPlayer4_words() {
		return player4_words;
	}

	/**
	 * @param player4_words
	 *            the player4_words to set
	 */
	public void setPlayer4_words(String player4_words) {
		this.player4_words = player4_words;
	}
}
