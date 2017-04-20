package com.csci3130.group03.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;

/**
 * The tests for the RegistrationService class. These tests are intented to
 * validate the logic behind creating a new user, adding the user to the systems
 * and password validation. It also handles authentication.
 * 
 * @author Zhongchao Tan, Eric Desjardins
 * 
 * @since 2016-06-09
 *
 */

public class RegistrationServiceTest extends TestBenchTestCase {

	RegistrationService registrationService;

	String userName = "ScrabbleUser";
	String userPassword = "ziqwe";

	@Rule
	public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(this, true);

	@Before
	public void setUp() throws Exception {
		setDriver(new PhantomJSDriver()); // PhantomJS headless browser
		getDriver().get("http://localhost:8090/Group03/#!login");
		registrationService = new RegistrationService(false);
	}

	/**
	 * Test if passwords don't match in user registration form
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPasswordInputDoesntMatch() throws Exception {
		assertEquals(registrationService.passwordMatchValidation(userPassword, userPassword + "1"), false);
	}

	/**
	 * Test if passwords match in user registration form
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPasswordInputDoesMatch() throws Exception {
		assertEquals(registrationService.passwordMatchValidation(userPassword, userPassword), true);
	}
}
