package com.csci3130.group03.controller;

import static org.junit.Assert.assertNotNull;

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
 * This class contains JUnit tests, which are run using Vaadin TestBench 4.
 *
 * To run this, first get an evaluation license from
 * https://vaadin.com/addon/vaadin-testbench and follow the instructions at
 * https://vaadin.com/directory/help/installing-cval-license to install it.
 *
 * Once the license is installed, you can run this class as a JUnit test.
 */
public class NavigatorUITest extends TestBenchTestCase {
	@Rule
	public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(this, true);

	@Before
	public void setUp() throws Exception {
		setDriver(new PhantomJSDriver()); // Firefox

		// To use Chrome, first install chromedriver.exe from
		// http://chromedriver.storage.googleapis.com/index.html
		// on your system path (e.g. C:\Windows\System32\)
		// setDriver(new ChromeDriver()); // Chrome

		// To use Internet Explorer, first install iedriverserver.exe from
		// http://selenium-release.storage.googleapis.com/index.html?path=2.43/
		// on your system path (e.g. C:\Windows\System32\)
		// setDriver(new InternetExplorerDriver()); // IE

		// To test headlessly (without a browser), first install phantomjs.exe
		// from http://phantomjs.org/download.html on your system path
		// (e.g. C:\Windows\System32\)
		// setDriver(new PhantomJSDriver()); // PhantomJS headless browser
	}

	/**
	 * Opens the URL where the application is deployed.
	 */
	private void openTestUrl() {
		getDriver().get("http://localhost:8090/Group03/#!login");
	}

	/**
	 * Test to confirm that the login page is the first view displayed by the
	 * application.
	 * 
	 * @throws Exeception
	 */
	@Test
	public void testFirstPageIsLoginPage() throws Exception {
		openTestUrl();

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

}