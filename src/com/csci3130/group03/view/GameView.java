package com.csci3130.group03.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csci3130.group03.component.ScoreComponent;
import com.csci3130.group03.component.SearchResultsComponent;
import com.csci3130.group03.controller.NavigatorUI;
import com.csci3130.group03.model.DirectorySearch;
import com.csci3130.group03.model.GameSave;
import com.csci3130.group03.model.Letter;
import com.csci3130.group03.model.Score;
import com.csci3130.group03.model.Search;
import com.csci3130.group03.model.Tray;
import com.csci3130.group03.model.User;
import com.csci3130.group03.model.Word;
import com.csci3130.group03.service.GameSaveService;
import com.csci3130.group03.service.RegistrationService;
import com.csci3130.group03.utility.WordsHandler;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * This is the page that the game will be played on
 */
@SuppressWarnings("serial")
public class GameView extends VerticalLayout implements View, Button.ClickListener {

	// global components for method access
	VerticalLayout content = new VerticalLayout();

	Button quitButton = new Button("Quit");
	Window quitMenu = new Window();
	VerticalLayout quitMenuContent = new VerticalLayout();
	Button save = new Button("Save");
	Button dontSave = new Button("Don't Save");

	Window loadSave = new Window();
	VerticalLayout loadSaveContent = new VerticalLayout();
	Button load = new Button("Load");
	Button dontLoad = new Button("Don't Load");

	SearchResultsComponent sW = new SearchResultsComponent();

	HorizontalLayout trayTable = new HorizontalLayout();
	HorizontalLayout buttonTable = new HorizontalLayout();
	Tray userTray = new Tray();
	TextField newLetterField = new TextField("Input Letter:");
	Button acceptButton = new Button("Enter");
	Button searchButton = new Button("Search");
	Button wildButton = new Button("WildCard");
	Window searchResults = new Window("");
	VerticalLayout searchContent = new VerticalLayout();

	ComboBox themeDropDown = new ComboBox("Theme");
	static String theme = "orange";

	DirectorySearch dictionarysearch;
	WordsHandler wordshandler;
	Search search = new Search();

	PopupView popup;
	PopupView wordnotify;
	HorizontalLayout popupContent;
	HorizontalLayout scoresDiv;
	Panel scoresPanel;
	Button newUser;
	Button viewStats;

	private ArrayList<Word> words = new ArrayList<Word>();
	int players = 1;
	String word, searchword;

	GameSaveService gameSaveService;
	List<ScoreComponent> scores;

	/**
	 * Creates GameView page
	 */
	@SuppressWarnings("deprecation")
	public GameView() {
		wordshandler = new WordsHandler();
		gameSaveService = new GameSaveService();
		scores = new ArrayList<ScoreComponent>();

		// setting id's for future referencing
		quitButton.setId("quit");
		quitMenu.setId("quitMenu");
		save.setId("save");
		save.addClickListener(event -> {
			saveGame();
			exitGame();
		});
		dontSave.setId("dontSave");
		dontSave.addClickListener(event -> {
			exitGame();
		});
		load.setId("load");
		load.addClickListener(event -> {
			loadGame(RegistrationService.getUSERSESSION());
		});
		dontLoad.setId("dontLoad");
		dontLoad.addClickListener(event -> {
			newGame(RegistrationService.getUSERSESSION());
		});
		trayTable.setId("tray");
		newLetterField.setId("acceptLetter");
		acceptButton.setId("acceptLetter");
		acceptButton.addClickListener(event -> {
			addLetterToTray();
		});
		searchButton.setId("searchButton");
		wildButton.setId("wildButton");
		themeDropDown.setId("ThemeDropDown");
		themeDropDown.setCaption("Choose Theme");
		themeDropDown.setNullSelectionAllowed(false);
		themeDropDown.addItem("Osage Orange Wood Grain");
		themeDropDown.addItem("Red Oak Wood Grain");
		themeDropDown.select("Osage Orange Wood Grain");
		themeDropDown.setWidth(300, UNITS_PIXELS);
		searchContent.addComponent(sW.getComponent());
		searchResults.setContent(searchContent);
		searchResults.setId("searchResults");

		// clicklistener for them combo box
		themeDropDown.addValueChangeListener(e -> {
			if (themeDropDown.getValue().equals("Osage Orange Wood Grain"))
				theme = "orange";
			else if (themeDropDown.getValue().equals("Red Oak Wood Grain"))
				theme = "oak";
		});

		// add clicklistener on buttons
		acceptButton.addClickListener(this);
		searchButton.addClickListener(this);
		wildButton.addClickListener(this);
		quitButton.addClickListener(this);

		// add components to layout
		addComponent(quitButton);

		addComponent(trayTable);
		setComponentAlignment(trayTable, Alignment.MIDDLE_CENTER);

		addComponent(newLetterField);

		addComponent(acceptButton);
		addComponent(searchButton);
		addComponent(wildButton);
		addComponent(themeDropDown);

		setComponentAlignment(newLetterField, Alignment.MIDDLE_CENTER);
		addComponent(buttonTable);
		setComponentAlignment(buttonTable, Alignment.MIDDLE_CENTER);


		buttonTable.addComponent(acceptButton);
		buttonTable.addComponent(searchButton);
		buttonTable.addComponent(wildButton);
		// setComponentAlignment(acceptButton, Alignment.MIDDLE_CENTER);
		// setComponentAlignment(searchButton, Alignment.MIDDLE_CENTER);

		// field settings
		newLetterField.setMaxLength(1);
		newLetterField
				.addValidator(new RegexpValidator("^[A-Z]|[a-z]$", true, "Invalid character. Please input a letter."));
		newLetterField.addValueChangeListener(valueChanged -> {
			acceptButton.focus();
		});
	}

	ScoreComponent addNewScore(User user) {
		players++;
		ScoreComponent score = new ScoreComponent(user);
		scoresDiv.addComponent(score.getComponent());
		scores.add(score);

		if (players > 4) {
			newUser.setVisible(false);
		}

		return score;
	}

	/**
	 * Called automatically when view navigated to
	 * 
	 * @param event
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		scoresDiv = new HorizontalLayout();
		scoresPanel = new Panel();
		newUser = new Button("Add User");
		newUser.setId("newUser");
		if (saveGameExists(RegistrationService.getUSERSESSION())) {
			showLoadWindow();
		} else {
			newGame(RegistrationService.getUSERSESSION());
		}
		newUser.addClickListener((e) -> {
			popup.setPopupVisible(true);
		});

		viewStats = new Button("View Stats");
		viewStats.setId("viewStats");
		viewStats.addClickListener((e) -> {
			viewStats();
		});

		popupContent = new HorizontalLayout();
		TextField nameField = new TextField();
		nameField.setInputPrompt("Input Name");
		nameField.setId("nameField");
		Button createUser = new Button("Create");
		createUser.setId("createUser");
		createUser.addClickListener((e) -> {
			addNewScore(new User(nameField.getValue(), ""));
			nameField.setValue("");
			popup.setPopupVisible(false);
		});

		popupContent.addComponent(nameField);
		popupContent.addComponent(createUser);
		popup = new PopupView(null, popupContent);
		popup.setId("popup");
		addComponent(viewStats);
		addComponent(newUser);
		addComponent(popup);
		scoresPanel.setContent(scoresDiv);
		addComponent(scoresPanel);
	}

	private void viewStats() {
		Window subWindow = new Window("User Stats");
		VerticalLayout subContent = new VerticalLayout();
		subContent.setMargin(true);
		subWindow.setContent(subContent);

		subContent.addComponent(new Label("Win: " + RegistrationService.getUSERSESSION().getStats().getTotalWins()));
		subContent.addComponent(new Label("Loss: " + RegistrationService.getUSERSESSION().getStats().getTotalLosses()));
		subContent.addComponent(
				new Label("Lifetime points: " + RegistrationService.getUSERSESSION().getStats().getTotalPoints()));
		subContent.addComponent(new Label("Top 10 Highest Scoring Words:"));
		subContent.addComponent(new Label(RegistrationService.getUSERSESSION().getStats().printTopTenWords(),
				ContentMode.PREFORMATTED));

		subWindow.center();

		UI.getCurrent().addWindow(subWindow);
	}
	
	/**
	 * Called automatically when button clicked
	 * @param event
	 */
	@Override
    public void buttonClick(ClickEvent event) {
    	try{
    		UI.getCurrent().removeWindow(searchResults);

    	}catch(Exception e){}
		sW.clear();
        // Differentiate targets by event source
        if (event.getButton().getId().equals("searchButton")){
        	UI.getCurrent().addWindow(searchResults);
        	
        	if (userTray.getTray().size()>0){	
        		words = new ArrayList<Word>();
        		search = new Search();
        		word = wordshandler.getWord(userTray);
        		words = search.searchListern(word);
	        	words.forEach(w->SearchResultsComponent.addWord(w));	        	
        	}
        } else if (event.getButton().getId().length() == 1){
        	Button thisButton = event.getButton();
        	userTray.remove(thisButton.getCaption());
        	
        	String deleteLetter = event.getButton().getId();
        	for (int i=0; i<userTray.getTray().size(); i++){
        		if (userTray.getTray().get(i).getLetter().equals(deleteLetter)){
        			userTray.getTray().remove(i);
        		}
        	}

        	
        	trayTable.removeComponent(thisButton);
        	System.out.println(event.getButton().getCaption() + " got removed, son");

        	System.out.println(userTray.getTray().size());
        } else if (event.getButton().getId().equals("quit")) {
        	quitMenu.setWidth("200px");
        	quitMenu.setHeight("150px");
        	quitMenu.setContent(quitMenuContent);
    		quitMenuContent.addComponent(save);
    		quitMenuContent.addComponent(dontSave);
    		quitMenuContent.setComponentAlignment(save, Alignment.MIDDLE_CENTER);
    		quitMenuContent.setComponentAlignment(dontSave, Alignment.MIDDLE_CENTER);
    		quitMenu.center();
        	UI.getCurrent().addWindow(quitMenu);
        } else if (event.getButton().getId().equals("wildButton")){

        	UI.getCurrent().addWindow(searchResults);
        	
        	if (userTray.getTray().size()>0){	
        		words = new ArrayList<Word>();
        		word = wordshandler.getWord(userTray);
	        	word = word + " ";
	        	words = search.searchListern(word);
	        	words.forEach(w->SearchResultsComponent.addWord(w)); 
        	}   
        }
    }

	boolean saveGameExists(User user) {
		return gameSaveService.getSavedGame(user) != null;
	}

	void saveGame() {
		List<Score> saveScores = new ArrayList<Score>();

		for (ScoreComponent score : scores) {
			saveScores.add(score.getScore());
		}

		gameSaveService.saveGame(userTray.getTray(), saveScores);
	}

	void loadGame(User user) {
		UI.getCurrent().removeWindow(loadSave);
		GameSave save = gameSaveService.getSavedGame(user);
		gameSaveService.removeSavedGame(user);

		for (String letter : save.getTray().split(",")) {
			if (!letter.equals("") && letter != null)
				addLetterToTray(letter);
		}

		if (save.getPlayer1_username() != null) {
			loadScores(save.getPlayer1_username(), save.getPlayer1_words().split(","));
		}
		
		if (save.getPlayer2_username() != null) {
			loadScores(save.getPlayer2_username(), save.getPlayer2_words().split(","));
		}

		if (save.getPlayer3_username() != null) {
			loadScores(save.getPlayer3_username(), save.getPlayer3_words().split(","));
		}

		if (save.getPlayer4_username() != null) {
			loadScores(save.getPlayer4_username(), save.getPlayer4_words().split(","));
		}
	}
	
	void loadScores(String username, String[] words) {
		ScoreComponent score = addNewScore(new User(username, ""));
		for (String word : words) {
			if (!word.equals("") && word != null)
				score.addWord(new Word(word));
		}
	}

	void newGame(User user) {
		UI.getCurrent().removeWindow(loadSave);
		addNewScore(user);
	}

	void exitGame() {
		UI.getCurrent().removeWindow(quitMenu);
		removeComponent(scoresPanel);
		removeComponent(newUser);
		removeComponent(viewStats);
		trayTable.removeAllComponents();
		NavigatorUI.navigator.navigateTo("login");
	}

	void addLetterToTray() {
		addLetterToTray(newLetterField.getValue().toUpperCase());
	}

	void addLetterToTray(String letterStr) {
		// if tray is full or is a number do not add a new letter
		if(!userTray.isFull() && newLetterField.isValid()) {
    		Letter letter = new Letter(letterStr);
    		userTray.addToTray(letterStr);
    		NativeButton tile = new NativeButton(letterStr);
    		tile.setStyleName("tiles-" + theme + " letterID-" + letterStr);
    		tile.setId(letterStr);
    		trayTable.addComponent(tile);
    		tile.addClickListener(this);
    		Page.getCurrent().getJavaScript().execute("var para = document.createElement('sub'); var node = document.createTextNode('"+letter.getValue()+"'); para.appendChild(node); var element = document.getElementsByClassName('letterID-"+ letterStr +"'); for (i = 0; i < element.length; i++) { element[i].appendChild(para);}");

    		newLetterField.clear();
    		newLetterField.focus();

    	}
	}
	
	void showLoadWindow() {
		loadSave.setWidth("200px");
		loadSave.setHeight("150px");
		loadSave.setContent(loadSaveContent);
		loadSaveContent.addComponent(load);
		loadSaveContent.addComponent(dontLoad);
		loadSaveContent.setComponentAlignment(load, Alignment.MIDDLE_CENTER);
		loadSaveContent.setComponentAlignment(dontLoad, Alignment.MIDDLE_CENTER);
		loadSave.center();
		UI.getCurrent().addWindow(loadSave);
	}
}
