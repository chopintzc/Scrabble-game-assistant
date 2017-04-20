package com.csci3130.group03.view;

import com.csci3130.group03.component.LoginComponent;
import com.csci3130.group03.component.RegistrationComponent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * Test cases for the LoginView view file. The LoginView contains two forms:
 * registration and login.
 * 
 * @author Eric Desjardins, Justin Tan
 * @since 2016-06-03
 *
 */
@SuppressWarnings("serial")
public class LoginView extends VerticalLayout implements View {

	public LoginView() {
		setSizeFull();

		HorizontalLayout layout = new HorizontalLayout();

		layout.addComponent(new RegistrationComponent().getLayout());
		layout.addComponent(new LoginComponent().getLayout());
		// Add components to the LoginView for display
		addComponent(layout);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		
	}
}
