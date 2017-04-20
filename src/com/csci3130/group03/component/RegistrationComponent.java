package com.csci3130.group03.component;

/**
 * Registration form for users to create a new account 
 * for the application. Once registered, users can use the 
 * login form to start using the app.
 * 
 * @author Stanford Lockhart & Eric Desjardins
 * @since 2016-06-23
 * 
 */

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

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

public class RegistrationComponent {

	FormLayout layout;
	RegistrationService registrationService;

	/**
	 * The registration form containing one username TextField component and two
	 * password field components. An errors label is used to display any errors
	 * reported from the RegistrationService class.
	 * 
	 */
	public RegistrationComponent() {
		registrationService = new RegistrationService();
		layout = new FormLayout();
		layout.addComponent(new Label("Please register before playing"));

		User user = new User();
		BeanItem<User> item = new BeanItem<User>(user);

		TextField username = new TextField("Username", item.getItemProperty("username"));
		username.addValidator(new BeanValidator(User.class, "username"));
		username.setImmediate(true);
		username.setId("txtRegisterUsername");
		username.setNullRepresentation("");
		layout.addComponent(username);

		PasswordField password = new PasswordField("Password", item.getItemProperty("password"));
		password.addValidator(new BeanValidator(User.class, "password"));
		password.setImmediate(true);
		password.setId("pwdRegisterPassword");
		password.setNullRepresentation("");
		layout.addComponent(password);

		PasswordField confirm = new PasswordField("Confirm Password");
		confirm.setImmediate(true);
		confirm.setId("pwdRegisterPasswordConfirm");
		confirm.setNullRepresentation("");
		layout.addComponent(confirm);

		Button btnRegister = new Button("Register");
		btnRegister.setId("btnRegister");
		layout.addComponent(btnRegister);

		Label errors = new Label();
		errors.setId("registrationErrors");
		layout.addComponent(errors);

		btnRegister.addClickListener(event -> {
			registerUser(username.getValue(), password.getValue(), confirm.getValue(), errors);
		});
	}

	/**
	 * The registration form layout.
	 * 
	 * @return the registration form layout
	 */
	public Layout getLayout() {
		return layout;
	}

	/**
	 * Method used to run validation based on the User model class. The
	 * RegistrationService class will perform the validation and throw any
	 * errors encountered during the validation of the user's input.
	 * 
	 * @param username
	 * @param password
	 * @param confirm
	 * @param errors
	 */
	void registerUser(String username, String password, String confirm, Label errors) {
		try {
			registrationService.createNewUser(username, password, confirm);
		} catch (ConstraintViolationException e) {
			String error = "";
			for (ConstraintViolation<?> cv : e.getConstraintViolations())
				error += cv.getMessage() + "\n";
			errors.setValue(error);
		} catch (ValidationException e) {
			errors.setValue(e.getMessage());
		}
	}
}
