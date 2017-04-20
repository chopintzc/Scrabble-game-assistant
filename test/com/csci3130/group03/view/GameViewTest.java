package com.csci3130.group03.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import com.vaadin.testbench.ElementQuery;
import com.vaadin.testbench.ScreenshotOnFailureRule;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.HorizontalLayoutElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.PasswordFieldElement;
import com.vaadin.testbench.elements.TableElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

/**
 * This class contains JUnit tests, which are run using Vaadin TestBench 4.
 *
 * To run this, first get an evaluation license from
 * https://vaadin.com/addon/vaadin-testbench and follow the instructions at
 * https://vaadin.com/directory/help/installing-cval-license to install it.
 *
 * Once the license is installed, you can run this class as a JUnit test.
 */
public class GameViewTest extends TestBenchTestCase {
	@Rule
	public ScreenshotOnFailureRule screenshotOnFailureRule = new ScreenshotOnFailureRule(this, true);

	@Before
	public void setUp() throws Exception {
		setDriver(new PhantomJSDriver());
		getDriver().get("http://localhost:8090/Group03/");

		TextFieldElement txtRegisterUsername = $(TextFieldElement.class).id("txtRegisterUsername");
		txtRegisterUsername.setValue("scrabbleMaster");
		PasswordFieldElement pwdRegisterPassword = $(PasswordFieldElement.class).id("pwdRegisterPassword");
		pwdRegisterPassword.setValue("greenforest");
		PasswordFieldElement pwdRegisterPasswordConfirm = $(PasswordFieldElement.class)
				.id("pwdRegisterPasswordConfirm");
		pwdRegisterPasswordConfirm.setValue("greenforest");
		ButtonElement btnRegister = $(ButtonElement.class).id("btnRegister");
		btnRegister.click();

		TextFieldElement txtUsername = $(TextFieldElement.class).id("txtUsername");
		txtUsername.setValue("scrabbleMaster");
		PasswordFieldElement pwdPassword = $(PasswordFieldElement.class).id("pwdPassword");
		pwdPassword.setValue("greenforest");
		ButtonElement btnLogin = $(ButtonElement.class).id("btnLogin");
		btnLogin.click();
	}

	/**
	 * Tests for search results in new window
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSearchWindow() throws Exception {
	    TextFieldElement newLetterField = $(TextFieldElement.class).id("acceptLetter");
	    ButtonElement accept = $(ButtonElement.class).id("acceptLetter");
	    ButtonElement search = $(ButtonElement.class).id("searchButton");
	    newLetterField.sendKeys("a");
	    accept.click();
	    search.click();
	    WindowElement win = $(WindowElement.class).id("searchResults");

	    assertTrue(win.isDisplayed());
	    win.close();
	    search.click();
	    assertTrue(win.isDisplayed());
	}

	/**
	 * Tests whether or not a tile gets added
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTileAdd() throws Exception {
		$(TextFieldElement.class).id("acceptLetter").sendKeys("f");

		ButtonElement acceptLetter = $(ButtonElement.class).id("acceptLetter");
		acceptLetter.click();

		ElementQuery<ButtonElement> tile = $(ButtonElement.class).caption("f");

		assertNotNull(tile);
	}

	/**
	 * Tests for theme changes
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTheme() {
		assertEquals($(ComboBoxElement.class).all().size(), 1);
		assertTrue(GameView.theme.equals("orange"));
	}

	/**
	 * Tests whether or not a tile gets removed
	 * 
	 * @throws Exception
	 */
	@Test
	public void testPageNav() {
		assertNotNull($(ButtonElement.class).id("newUser"));
	}

	/**
	 * Test the addition of score panels to the page
	 * 
	 * Whenever a new user is entered, a new panel should be created
	 * 
	 * The page starts with a single panel, and up to four can be created
	 */
	@Test
	public void testScoreCreation() {
		int numTables = $(TableElement.class).all().size();
		assertEquals(1, numTables);

		ButtonElement btnNew = $(ButtonElement.class).id("newUser");
		ButtonElement btnSave;// = $(ButtonElement.class).id("createUser");
		TextFieldElement text;// = $(TextFieldElement.class).id("nameField");

		for (int i = 2; i <= 4; i++) {
			btnNew.click();
			btnSave = $(ButtonElement.class).id("createUser");
			text = $(TextFieldElement.class).id("nameField");
			text.setValue("test_kid");
			btnSave.click();

			numTables = $(TableElement.class).all().size();

			assertEquals(i, numTables);
		}
	}

	@Test
	public void tileRemove() throws Exception {
		HorizontalLayoutElement tray = $(HorizontalLayoutElement.class).id("tray");
		$(TextFieldElement.class).id("acceptLetter").sendKeys("f");

		ButtonElement acceptLetter = $(ButtonElement.class).id("acceptLetter");
		acceptLetter.click();

		ButtonElement tile = $(ButtonElement.class).first();
		tile.click();
		int numTiles = tray.$(ButtonElement.class).all().size();

		assertEquals(numTiles, tray.$(ButtonElement.class).all().size());
	}

	@Test
	public void maxTiles() throws Exception {
		TextFieldElement field = $(TextFieldElement.class).id("acceptLetter");
		ButtonElement accept = $(ButtonElement.class).id("acceptLetter");
		HorizontalLayoutElement tray = $(HorizontalLayoutElement.class).id("tray");

		String[] input = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j" };

		for (int i = 1; i < 10; i++) {
			field.setValue("");
			field.sendKeys(input[i]);
			accept.click();
		}

		assertEquals(7, tray.$(ButtonElement.class).all().size());
	}

	@Test
	public void letterFieldValidation() throws Exception {
		HorizontalLayoutElement tray = $(HorizontalLayoutElement.class).id("tray");
		TextFieldElement newLetterField = $(TextFieldElement.class).id("acceptLetter");
		ButtonElement accept = $(ButtonElement.class).id("acceptLetter");

		int numTiles = tray.$(ButtonElement.class).all().size();

		newLetterField.sendKeys("$");
		accept.click();

		int newNumTiles = tray.$(ButtonElement.class).all().size();

		assertEquals(numTiles, newNumTiles);
	}

	/**
	 * Test to validate the stats button appears
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStatsButtonIsDisplayed() throws Exception {

		ButtonElement stats = $(ButtonElement.class).id("viewStats");

		assertTrue(stats.isDisplayed());
	}

	@Test
	public void testQuitButtonIsDisplayed() throws Exception {
		ButtonElement quit = $(ButtonElement.class).id("quit");
		assertTrue(quit.isDisplayed());
	}

	@Test
	public void testQuitPopUpIsDisplayed() throws Exception {
		ButtonElement quit = $(ButtonElement.class).id("quit");
		quit.click();

		WindowElement quitMenu = $(WindowElement.class).id("quitMenu");
		assertTrue(quitMenu.isDisplayed());
	}

	@Test
	public void testWildcardButtonIsDisplayed() throws Exception {
		ButtonElement wildbutton = $(ButtonElement.class).id("wildButton");
		assertTrue(wildbutton.isDisplayed());
	}
	
	@Test
	public void testSaveDontSave() throws Exception {
		ButtonElement quit = $(ButtonElement.class).id("quit");
		quit.click();

		ButtonElement save = $(ButtonElement.class).id("save");
		ButtonElement dontSave = $(ButtonElement.class).id("dontSave");

		boolean displayedCorrectly = false;

		if (save.isDisplayed() && dontSave.isDisplayed())
			displayedCorrectly = true;
		else
			displayedCorrectly = false;

		assertTrue(displayedCorrectly);

	}

	@Test
	public void testGameExited() throws Exception {
		ButtonElement quit = $(ButtonElement.class).id("quit");
		quit.click();

		ButtonElement dontSave = $(ButtonElement.class).id("dontSave");
		
		dontSave.click();
		
		LabelElement lblLoginForm = $(LabelElement.class).id("lblLoginForm");
		assertNotNull(lblLoginForm);

	}

}