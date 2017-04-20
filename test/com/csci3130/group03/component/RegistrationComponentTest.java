package com.csci3130.group03.component;

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
import com.vaadin.testbench.elements.TextFieldElement;

/**
 * Unit tests for the RegistrationComponent. The test coverage includes form
 * validation and input validation.
 * 
 * @author Stanford Lockhart, Eric Desjardins
 * @since 2016-06-23
 *
 */
public class RegistrationComponentTest extends TestBenchTestCase {
	@Rule
	public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(this, true);

	@Before
	public void setUp() throws Exception {
		setDriver(new PhantomJSDriver());
		getDriver().get("http://localhost:8090/Group03/#!login");
	}

	/**
	 * Assert login page renders register label, username registration, password
	 * register input, password register confirmation and register button
	 *
	 * @throws Exeception
	 */
	@Test
	public void testRegisterFormDisplayContent() throws Exception {
		// Test that the register label renders
		LabelElement lblRegistration = $(LabelElement.class).id("registrationErrors");
		assertNotNull(lblRegistration);

		// Test that register username text field renders
		TextFieldElement txtRegisterUsername = $(TextFieldElement.class).id("txtRegisterUsername");
		assertNotNull(txtRegisterUsername);

		// Test that register password password field renders
		PasswordFieldElement pwdRegisterPassword = $(PasswordFieldElement.class).id("pwdRegisterPassword");
		assertNotNull(pwdRegisterPassword);

		// Test that register password confirm password field renders
		PasswordFieldElement pwdRegisterPasswordConfirm = $(PasswordFieldElement.class)
				.id("pwdRegisterPasswordConfirm");
		assertNotNull(pwdRegisterPasswordConfirm);

		// Test that register button renders
		ButtonElement btnRegister = $(ButtonElement.class).id("btnRegister");
		assertNotNull(btnRegister);
	}

	/**
	 * Test if the form fails registration based on username already in use. The
	 * form remains populated after validation, thus clicking the button will
	 * attempt to create a new user with the same data in the fields.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFailRegistrationUserNameAlreadyUsed() throws Exception {
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

		// Click again to resubmit
		btnRegister.click();

		// Test that fail registration loads
		LabelElement lblUserRegistrationFail = $(LabelElement.class).id("registrationErrors");
		assertNotNull(lblUserRegistrationFail);

	}

	/**
	 * Test if the form fails registration based on passwords not matching
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFailRegistrationPasswordsDontMatch() throws Exception {
		// Get the user name registration field
		TextFieldElement txtRegisterUsername = $(TextFieldElement.class).id("txtRegisterUsername");
		txtRegisterUsername.setValue("scrabbleMaster");

		// Get the first password field
		PasswordFieldElement pwdRegisterPassword = $(PasswordFieldElement.class).id("pwdRegisterPassword");
		pwdRegisterPassword.setValue("12341234");

		// Get the confirm password field
		PasswordFieldElement pwdRegisterPasswordConfirm = $(PasswordFieldElement.class)
				.id("pwdRegisterPasswordConfirm");
		pwdRegisterPasswordConfirm.setValue("12341234999");

		// Get the register button
		ButtonElement btnRegister = $(ButtonElement.class).id("btnRegister");
		btnRegister.click();

		// Test that fail registration loads
		LabelElement lblUserRegistrationFail = $(LabelElement.class).id("registrationErrors");
		assertNotNull(lblUserRegistrationFail);

	}

	/**
	 * Test if the form fails registration based on password too short
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFailRegistrationPasswordTooShort() throws Exception {
		// Get the user name registration field
		TextFieldElement txtRegisterUsername = $(TextFieldElement.class).id("txtRegisterUsername");
		txtRegisterUsername.setValue("scrabbleMaster");

		// Get the first password field
		PasswordFieldElement pwdRegisterPassword = $(PasswordFieldElement.class).id("pwdRegisterPassword");
		pwdRegisterPassword.setValue("1234");

		// Get the confirm password field
		PasswordFieldElement pwdRegisterPasswordConfirm = $(PasswordFieldElement.class)
				.id("pwdRegisterPasswordConfirm");
		pwdRegisterPasswordConfirm.setValue("1234");

		// Get the register button
		ButtonElement btnRegister = $(ButtonElement.class).id("btnRegister");
		btnRegister.click();

		// Test that fail registration loads
		LabelElement lblUserRegistrationFail = $(LabelElement.class).id("registrationErrors");
		assertTrue(lblUserRegistrationFail.getText().length() > 0);

	}

	/**
	 * Test if the form fails registration based on password too long
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFailRegistrationPasswordTooLong() throws Exception {
		// Get the user name registration field
		TextFieldElement txtRegisterUsername = $(TextFieldElement.class).id("txtRegisterUsername");
		txtRegisterUsername.setValue("scrabbleMaster");

		// Get the first password field
		PasswordFieldElement pwdRegisterPassword = $(PasswordFieldElement.class).id("pwdRegisterPassword");
		pwdRegisterPassword.setValue("12341234123412341234123412341234");

		// Get the confirm password field
		PasswordFieldElement pwdRegisterPasswordConfirm = $(PasswordFieldElement.class)
				.id("pwdRegisterPasswordConfirm");
		pwdRegisterPasswordConfirm.setValue("12341234123412341234123412341234");

		// Get the register button
		ButtonElement btnRegister = $(ButtonElement.class).id("btnRegister");
		btnRegister.click();

		// Test that the error is not empty
		LabelElement lblUserRegistrationFail = $(LabelElement.class).id("registrationErrors");
		assertTrue(lblUserRegistrationFail.getText().length() > 0);

	}
}
