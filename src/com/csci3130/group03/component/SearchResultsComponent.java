package com.csci3130.group03.component;

import com.csci3130.group03.model.Word;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
/**
 * SearchResultsComponent 
 * @author Luke Elwood
 *
 */
public class SearchResultsComponent {
private static Table searchTable;
/**
 * Creates table
 */
public SearchResultsComponent(){
	this.searchTable = new Table();
	this.searchTable.setCaption("Search Results");
	this.searchTable.addContainerProperty("Words", String.class, null);
	this.searchTable.addContainerProperty("Scores", Integer.class, null);
	this.searchTable.setColumnWidth("Words", 170);
	this.searchTable.setColumnWidth("Scores", 100);
	this.searchTable.setFooterVisible(true);
	this.searchTable.setHeight(10, Unit.PERCENTAGE);
}
/**
 * Adds a new word to the table 
 * @param word
 */
public static void addWord(Word word) {
	searchTable.addItem(new Object[] { word.getWord(), word.getScore() }, searchTable.getItemIds().size());	
}

public static void clear() {
	searchTable.removeAllItems();
}

/**
 * Return SearchResultsComponent
 * @return component 
 */
public Component getComponent() {
	VerticalLayout vert = new VerticalLayout();
	vert.addComponent(searchTable);
	return vert;
}
/**
 * @return searchTable
 */
public Table getTable(){
	return searchTable;
}
}
