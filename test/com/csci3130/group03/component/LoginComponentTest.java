package com.csci3130.group03.component;

/**
 * Unit tests for the for the LoginComponent. The 
 * tests cover form validation and user registration.
 * 
 * @author Stanford Lockhart, Eric Desjardins
 * @since 2016-06-23
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.PasswordFieldElement;
import com.vaadin.testbench.elements.TableElement;
import com.vaadin.testbench.elements.TextFieldElement;

public class LoginComponentTest extends TestBenchTestCase {
	@Rule
	public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(this, true);

	@Before
	public void setUp() throws Exception {
		setDriver(new PhantomJSDriver());
		getDriver().get("http://localhost:8090/Group03/#!login");
	}

	/**
	 * Assert login page renders login label, username field, password field and
	 * login button
	 * 
	 * @throws Exception
	 */
	@Test
	public void testLoginFormDisplayContent() throws Exception {
		// Test that the login label renders
		LabelElement lblLoginForm = $(LabelElement.class).id("lblLoginForm");
		assertNotNull(lblLoginForm);

		// Test that username text field renders
		TextFieldElement txtUsernameInput = $(TextFieldElement.class).id("txtUsername");
		assertNotNull(txtUsernameInput);

		// Test that login password password field renders
		PasswordFieldElement pwdPasswordInput = $(PasswordFieldElement.class).id("pwdPassword");
		assertNotNull(pwdPasswordInput);

		// Test that login button renders
		ButtonElement btnLogin = $(ButtonElement.class).id("btnLogin");
		assertNotNull(btnLogin);
	}

	/**
	 * Test if the form will create a new user and pass.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCreateNewUser() throws Exception {
		// Get the user name registration field
		TextFieldElement txtRegisterUsername = $(TextFieldElement.class).id("txtRegisterUsername");
		txtRegisterUsername.setValue("scrabbleMaster");

		// Get the first password field
		PasswordFieldElement pwdRegisterPassword = $(PasswordFieldElement.class).id("pwdRegisterPassword");
		pwdRegisterPassword.setValue("12341234");

		// Get the confirm password field
		PasswordFieldElement pwdRegisterPasswordConfirm = $(PasswordFieldElement.class)
				.id("pwdRegisterPasswordConfirm");
		pwdRegisterPasswordConfirm.setValue("12341234");

		// Get the register button
		ButtonElement btnRegister = $(ButtonElement.class).id("btnRegister");
		btnRegister.click();

		// Test that the user created label displays
		LabelElement lblUserRegistrationPass = $(LabelElement.class).id("registrationErrors");
		assertNotNull(lblUserRegistrationPass);

	}

	/**
	 * Test the login form
	 * 
	 * Test case: user gets created and enters proper credentials and passes
	 * authentication
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserAuthenticationPass() throws Exception {
		// Get the user name registration field
		TextFieldElement txtRegisterUsername = $(TextFieldElement.class).id("txtRegisterUsername");
		txtRegisterUsername.setValue("scrabbleMaster");

		// Get the first password field
		PasswordFieldElement pwdRegisterPassword = $(PasswordFieldElement.class).id("pwdRegisterPassword");
		pwdRegisterPassword.setValue("greenforest");

		// Get the confirm password field
		PasswordFieldElement pwdRegisterPasswordConfirm = $(PasswordFieldElement.class)
				.id("pwdRegisterPasswordConfirm");
		pwdRegisterPasswordConfirm.setValue("greenforest");

		// Get the register button
		ButtonElement btnRegister = $(ButtonElement.class).id("btnRegister");
		btnRegister.click();

		// Enter login name
		TextFieldElement txtUsername = $(TextFieldElement.class).id("txtUsername");
		txtUsername.setValue("scrabbleMaster");

		// Enter login
		PasswordFieldElement pwdPassword = $(PasswordFieldElement.class).id("pwdPassword");
		pwdPassword.setValue("greenforest");

		// Enter password
		ButtonElement btnLogin = $(ButtonElement.class).id("btnLogin");
		btnLogin.click();

		// Assert redirected to gameview as logged in user
		assertEquals("scrabbleMaster", $(TableElement.class).first().getCaption());
	}

	/**
	 * Test the login form
	 * 
	 * Test case: user doesn't exist for login
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserAuthenticationFailUserDoesntExist() throws Exception {
		// Enter login name
		TextFieldElement txtUsername = $(TextFieldElement.class).id("txtUsername");
		txtUsername.setValue("scrabbleMaster");

		// Enter login
		PasswordFieldElement pwdPassword = $(PasswordFieldElement.class).id("pwdPassword");
		pwdPassword.setValue("greenforest");

		// Enter password
		ButtonElement btnLogin = $(ButtonElement.class).id("btnLogin");
		btnLogin.click();

		// Assert the login pass label doesn't work
		LabelElement lblUserRegistrationFail = $(LabelElement.class).id("loginErrors");
		assertTrue(lblUserRegistrationFail.getText().length() > 0);

	}

	/**
	 * Test the login form
	 * 
	 * Test case: user was created but login password is wrong
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUserAuthenticationFailWrongPassword() throws Exception {
		// Get the user name registration field
		TextFieldElement txtRegisterUsername = $(TextFieldElement.class).id("txtRegisterUsername");
		txtRegisterUsername.setValue("scrabbleMaster");

		// Get the first password field
		PasswordFieldElement pwdRegisterPassword = $(PasswordFieldElement.class).id("pwdRegisterPassword");
		pwdRegisterPassword.setValue("greenforest");

		// Get the confirm password field
		PasswordFieldElement pwdRegisterPasswordConfirm = $(PasswordFieldElement.class)
				.id("pwdRegisterPasswordConfirm");
		pwdRegisterPasswordConfirm.setValue("greenforest");

		// Get the register button
		ButtonElement btnRegister = $(ButtonElement.class).id("btnRegister");
		btnRegister.click();

		// Enter login name
		TextFieldElement txtUsername = $(TextFieldElement.class).id("txtUsername");
		txtUsername.setValue("scrabbleMaster");

		// Enter login
		PasswordFieldElement pwdPassword = $(PasswordFieldElement.class).id("pwdPassword");
		pwdPassword.setValue("wrongpassword");

		// Enter password
		ButtonElement btnLogin = $(ButtonElement.class).id("btnLogin");
		btnLogin.click();

		// Assert the login pass label works
		LabelElement lblLoginFail = $(LabelElement.class).id("loginErrors");
		assertNotNull(lblLoginFail);
	}
}
