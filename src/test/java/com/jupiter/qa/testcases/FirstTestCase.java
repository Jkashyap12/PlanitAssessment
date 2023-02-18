/*
 * @author Naveen Khunteta
 * 
 */

package com.jupiter.qa.testcases;

import com.jupiter.qa.base.TestBase;
import com.jupiter.qa.pages.ContactsPage;
import com.jupiter.qa.pages.HomePage;
import com.jupiter.qa.pages.LoginPage;
import com.jupiter.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FirstTestCase extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	
	String sheetName = "contacts";
	public FirstTestCase(){
		super();
	}
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		homePage = new HomePage();
		TestUtil.runTimeInfo("error", "login successful");
		}
	
	@Test(priority=1,dataProvider = "getTestData")
	public void verifyErrorMessagesOnContactPage(String Forename, String Email, String Message){
		contactsPage = homePage.navigateToContactsPage();
		contactsPage.clickOnSubmitButton();
		Assert.assertTrue(contactsPage.validateForenameErrorMessages("Forename is required"),"Fail: Forename error validation didn't match");
		Assert.assertTrue(contactsPage.validateEmailErrorMessages("Email is required"),"Fail: Email error validation didn't match");
		Assert.assertTrue(contactsPage.validateMessageErrorMessages("Message is required"),"Fail: Message error validation didn't match");

		//Enter Mandatory fields
		contactsPage.inputForename(Forename);
		contactsPage.inputEmail(Email);
		contactsPage.inputMessage(Message);

		Assert.assertFalse(contactsPage.isForenameValidationPresent(),"Fail: Forename error validation is present");
		Assert.assertFalse(contactsPage.isEmailValidationPresent(),"Fail: Email error validation is present");
		Assert.assertFalse(contactsPage.isMessageValidationPresent(),"Fail: Message error validation is present");
	}

	@DataProvider
	public Object[][] getTestData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
}
