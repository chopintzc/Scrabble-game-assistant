package com.csci3130.group03.model;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * Runs the tests for the User model
 * 
 * @author Eric Desjardins, Zhongchao Tan
 * @since 2016-06-01
 */
public class UserTest extends TestBenchTestCase {
	
	User newUser;
	
	Stats stats;

	String userName;
	String newUserName;
	String userPassword;
	String newUserPassword;

	boolean authenticationStatus;

	/**
	 * Create a valid user instance.
	 * 
	 * @result A user instance has been created and will persist the current
	 *         life cycle of the application
	 */
	@Test
	public void testCreateUserInstance() throws Exception {
		// Create new user
		userName = "Eric";
		userPassword = "moltenCrag";
		
		newUser = new User(userName, userPassword);

		// User name match
		assertEquals(userName, newUser.getUsername());

		// User password match
		assertEquals(userPassword, newUser.getPassword());
	}

	/**
	 * Access the user name of a given user
	 * 
	 * @result The user name will be returned and match the expected result
	 */
	@Test
	public void testGetUserName() throws Exception {
		userName = "ScabbleMaster500";
		userPassword = "zwkoq";
		newUser = new User(userName, userPassword);

		// User name match
		assertEquals(userName, newUser.getUsername());
	}

	/**
	 * Access the user password of a given user
	 * 
	 * @result The user password will be returned and match the expected result
	 */
	@Test
	public void testGetUserPassword() throws Exception {
		userName = "ScrabbleUser";
		userPassword = "ziqwe";
		newUser = new User(userName, userPassword);

		// User name match
		assertEquals(userPassword, newUser.getPassword());
	}

	/**
	 * Access the user name and set it to a new value
	 * 
	 * @result The user instance will be set to a new user name
	 */
	@Test
	public void testSetUserName() throws Exception {
		userName = "ScabbleMaster5000";
		newUserName = "ScabbleLord5000";
		userPassword = "zwkoq";
		newUser = new User(userName, userPassword);

		// Set new user name
		newUser.setUsername(newUserName);

		// User name match
		assertEquals(newUserName, newUser.getUsername());

	}

	/**
	 * Access the user password and set it to a new value
	 * 
	 * @result The user password will be set to a new password
	 */
	@Test
	public void testSetUserPassword() throws Exception {
		userName = "ScabbleMaster5000";
		userPassword = "zwkoq";
		newUserPassword = "newZwkoq";
		newUser = new User(userName, userPassword);

		// Set new password
		newUser.setPassword(newUserPassword);

		// Password match
		assertEquals(newUserPassword, newUser.getPassword());

	}

	/**
	 * Verify that a user isn't authenticated thus they can't access the
	 * application
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserNotAuthenticated() throws Exception {
		userName = "ScabbleMaster5000";
		userPassword = "zwkoq";
		authenticationStatus = false;

		newUser = new User(userName, userPassword);

		// Verify if user is not authenticated
		assertEquals(newUser.getAuthenticationStatus(), false);

	}

	/**
	 * Verify that a user is authenticated thus they can access the application
	 * 
	 * @throws Exeception
	 */
	@Test
	public void testUserIsAuthenticated() throws Exception {
		userName = "ScabbleMaster5000";
		userPassword = "zwkoq";
		authenticationStatus = true;

		newUser = new User(userName, userPassword);
		
		newUser.setAuthenticationStatus(authenticationStatus);

		// Verify if user is authenticated
		assertEquals(newUser.getAuthenticationStatus(), true);
	}

	/**
	 * Simulate the scenario of a user logging in. That is, the user is not
	 * authenticated and becomes authenticated
	 * 
	 * @throws Exeception
	 */
	@Test
	public void testUserLogsOn() throws Exception {
		userName = "ScabbleMaster5000";
		userPassword = "zwkoq";
		authenticationStatus = false;

		newUser = new User(userName, userPassword);

		newUser.setAuthenticationStatus(true);

		// Verify if user is authenticated
		assertEquals(newUser.getAuthenticationStatus(), true);
	}
	
	/**
	 * Simulate the scenario of a user logging off. That is, the user is
	 * authenticated and becomes not authenticated
	 * 
	 * @throws Exeception
	 */
	@Test
	public void testUserLogsOff() throws Exception {
		userName = "ScabbleMaster5000";
		userPassword = "zwkoq";
		authenticationStatus = true;

		newUser = new User(userName, userPassword);

		newUser.setAuthenticationStatus(false);

		// Verify if user is authenticated
		assertEquals(newUser.getAuthenticationStatus(), false);
	}
	
	/**
	 * Test a user winning a game.
	 * 
	 * @throws Exeception
	 */
	@Test
	public void testUserAddWin() throws Exception {
		userName = "ScabbleMaster5000";
		userPassword = "zwkoq";
		authenticationStatus = true;

		newUser = new User(userName, userPassword);
		
		stats = new Stats();
		
		newUser.setStats(stats);
		
		newUser.getStats().addWin();
		
		// Verify if user was assigned a win
		assertEquals(1, newUser.getStats().getTotalWins());
	}
	
	/**
	 * Test a user losing a game.
	 * 
	 * @throws Exeception
	 */
	@Test
	public void testUserAddLoss() throws Exception {
		userName = "ScabbleMaster5000";
		userPassword = "zwkoq";
		authenticationStatus = true;

		newUser = new User(userName, userPassword);
		
		stats = new Stats();
		
		newUser.setStats(stats);
		
		newUser.getStats().addLoss();
		
		// Verify if user was assigned a win
		assertEquals(1, newUser.getStats().getTotalLosses());
	}

}
