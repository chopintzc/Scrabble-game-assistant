package com.csci3130.group03.component;

/**
 * Login form component that is used for the 
 * user authentication and gain access to the app.
 * 
 * @author Stanford Lockhart & Eric Desjardins
 * @since 2016-06-23
 * 
 */

import javax.security.auth.login.LoginException;

import com.csci3130.group03.controller.NavigatorUI;
import com.csci3130.group03.model.User;
import com.csci3130.group03.service.RegistrationService;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

public class LoginComponent {

	FormLayout layout;
	RegistrationService registrationService;

	/**
	 * Login form includes a TextField for the username and PasswordField for
	 * the password. Each field is validated using the Bean validation which is
	 * modeled in the User model. The error label will generate any
	 * authentication issues generated from the RegistrationService class.
	 * 
	 */
	public LoginComponent() {
		registrationService = new RegistrationService();
		layout = new FormLayout();

		Label lblLoginForm = new Label("Enter login infomation");
		lblLoginForm.setId("lblLoginForm");
		layout.addComponent(lblLoginForm);

		User user = new User();
		BeanItem<User> item = new BeanItem<User>(user);

		TextField username = new TextField("Username", item.getItemProperty("username"));
		username.addValidator(new BeanValidator(User.class, "username"));
		username.setImmediate(true);
		username.setId("txtUsername");
		username.setNullRepresentation("");
		layout.addComponent(username);

		PasswordField password = new PasswordField("Password", item.getItemProperty("password"));
		password.addValidator(new BeanValidator(User.class, "password"));
		password.setImmediate(true);
		password.setId("pwdPassword");
		password.setNullRepresentation("");
		layout.addComponent(password);

		Button btnLogin = new Button("Login");
		btnLogin.setId("btnLogin");
		layout.addComponent(btnLogin);

		Label errors = new Label();
		errors.setId("loginErrors");
		layout.addComponent(errors);

		btnLogin.addClickListener(event -> {
			loginUser(username.getValue(), password.getValue(), errors);
		});
	}

	/**
	 * The built form.
	 * 
	 * @return the built form to be used on a page
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * Method to validate user input agains't the database using the
	 * RegistrationService class. When there's an error it will update error
	 * label
	 * 
	 * @param username
	 * @param password
	 * @param errors
	 */
	void loginUser(String username, String password, Label errors) {
		try {
			User user = registrationService.userIsAuthenticated(username, password);
			RegistrationService.setUSERSESSION(user);
			NavigatorUI.navigator.navigateTo("game");
		} catch (LoginException e) {
			errors.setValue(e.getMessage());
		}
	}
}
