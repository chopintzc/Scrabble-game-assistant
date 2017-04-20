package com.csci3130.group03.service;

import java.util.List;

import com.csci3130.group03.controller.NavigatorUI;
import com.csci3130.group03.model.GameSave;
import com.csci3130.group03.model.Letter;
import com.csci3130.group03.model.Score;
import com.csci3130.group03.model.User;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

public class GameSaveService {

	JPAContainer<GameSave> saves;
	
	public GameSaveService() {
		saves = JPAContainerFactory.make(GameSave.class, NavigatorUI.PERSISTENCE_UNIT);
	}
	
	public void saveGame(List<Letter> tray, List<Score> scores) {
		GameSave save = new GameSave(tray, scores);
		saves.addEntity(save);
	}
	
	public GameSave getSavedGame(User user) {
		for (Object id : saves.getItemIds()) {
			GameSave save = saves.getItem(id).getEntity();
			if (save.getUsernames().contains(user.getUsername()))
				return save;
		}
		return null;
	}
	
	public void removeSavedGame(User user) {
		for (Object id : saves.getItemIds()) {
			GameSave save = saves.getItem(id).getEntity();
			if (save.getUsernames().contains(user.getUsername())) {
				saves.removeItem(id);
				return;
			}
		}
		return;
	}
}
