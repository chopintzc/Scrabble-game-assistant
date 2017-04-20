package com.csci3130.group03.controller;

import javax.servlet.annotation.WebServlet;

import com.csci3130.group03.view.GameView;
import com.csci3130.group03.view.LoginView;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

/**
 * Creates controller
 *
 */
@SuppressWarnings("serial")
@Theme("group03")
public class NavigatorUI extends UI {
	
	public static final String PERSISTENCE_UNIT = "csci3130";
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = NavigatorUI.class)
	public static class Servlet extends VaadinServlet {
	}

	/**
	 * controller, allows for navigation can call navigateTo() from this object
	 */
	public static Navigator navigator;

	/**
	 * This method initializes a Navigator controller Registers Views
	 *
	 * @param request
	 */
	@Override
	protected void init(VaadinRequest request) {

		getPage().setTitle("Group03");

		// Create a navigator to control the views
		navigator = new Navigator(this, this);

		// Create and register the views
		navigator.addView("", new LoginView());
		navigator.addView("login", new LoginView());
		navigator.addView("game", new GameView());
	}
}
