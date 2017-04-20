package com.csci3130.group03.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.validation.ValidationException;

import com.csci3130.group03.controller.NavigatorUI;
import com.csci3130.group03.model.Stats;
import com.csci3130.group03.model.User;
import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;

/**
 * The registration service is used to create users once they register on the
 * login page. The class also performs form validation for existing user names,
 * matching password and password validation. Further, it handles authentication
 * for login.
 * 
 * @author Justin Tan, Eric Desjardins, Stanford Lockhart
 * @since 2016-06-23
 * @version 2.0
 */

public class RegistrationService {

	JPAContainer<User> users;

	static User USERSESSION;

	/**
	 * Create a persistence unit of users
	 */
	public RegistrationService() {
		users = JPAContainerFactory.make(User.class, NavigatorUI.PERSISTENCE_UNIT);
	}

	/**
	 * Create a persistence unit of users
	 * 
	 * @param createContainer
	 */
	public RegistrationService(boolean createContainer) {
		if (createContainer)
			users = JPAContainerFactory.make(User.class, NavigatorUI.PERSISTENCE_UNIT);
	}

	/**
	 * Access the user data of the current logged in user.
	 * 
	 * @return authenticated user for this session
	 */
	public static User getUSERSESSION() {
		return USERSESSION;
	}

	/**
	 * Assign a user to a given game session
	 * 
	 * @param user
	 */
	public static void setUSERSESSION(User user) {
		USERSESSION = user;
	}

	/**
	 * List of registered users to the app.
	 * 
	 * @return a List containing all the registered User objects
	 */
	protected List<User> getRegisteredUsers() {
		Collection<Object> ids = users.getItemIds();
		List<User> list = new ArrayList<User>();
		for (Object id : ids)
			list.add(users.getItem(id).getEntity());
		return list;
	}

	/**
	 * Method to validate that the two strings match in the registration form.
	 * 
	 * @param passwordInput
	 * @param passwordConfirm
	 * @return true if they match, false if not
	 */
	protected boolean passwordMatchValidation(String passwordInput, String passwordConfirm) {
		if (passwordInput.equals(passwordConfirm)) {
			return true;
		}

		return false;
	}

	/**
	 * Method that validates if a username is available in the 'database'. It
	 * will search each user object in the
	 * 
	 * @param userName
	 * @return
	 */
	protected boolean userNameAvailable(String userName) {
		for (User user : getRegisteredUsers()) {
			if (user.getUsername().equals(userName)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Method that integrates both password and username validations. If they
	 * pass, the new user will be added to the database.
	 * 
	 * @param username
	 * @param password
	 * @param confirm
	 * @return true if new user has been created, false if not
	 * @throws ValidationException
	 */
	public User createNewUser(String username, String password, String confirm) throws ValidationException {
		if (!passwordMatchValidation(password, confirm)) {
			throw new ValidationException("Passwords do not match");
		} else if (userNameAvailable(username)) {
			getRegisteredUsers();
			User newUser = users.getItem(users.addEntity(new User(username, password))).getEntity();
			return newUser;
		} else {
			throw new ValidationException("Passwords do not match");
		}
	}

	/**
	 * Method to authenticate users by taking in the username and password
	 * values from the login form and proceeds to check the userlist database.
	 * 
	 * @param username
	 * @param password
	 * @return true if a user has provided validated credentials, false if they
	 *         failed
	 * @throws LoginException
	 */
	public User userIsAuthenticated(String userName, String password) throws LoginException {
		for (User user : getRegisteredUsers()) {
			if (user.getUsername().equals(userName) && user.getPassword().equals(password)) {
				JPAContainer<Stats> jpaContainerStats = JPAContainerFactory.make(Stats.class, NavigatorUI.PERSISTENCE_UNIT);
				Stats stats = jpaContainerStats.getItem(jpaContainerStats.addEntity(new Stats())).getEntity();
				
				user.setStats(stats);
				
				return user;
			}
		}
		throw new LoginException("Username or password does not match");
	}

}
