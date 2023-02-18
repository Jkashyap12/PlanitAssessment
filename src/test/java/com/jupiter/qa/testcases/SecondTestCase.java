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

public class SecondTestCase extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;

	String sheetName = "contacts";


	public SecondTestCase(){
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
	
	@Test(priority=1,dataProvider = "getTestData",invocationCount=5)
	public void verifySuccessMessage(String Forename, String Email, String Message){
		String expectedMessage="Thanks "+ Forename+", we appreciate your feedback.";
		contactsPage = homePage.navigateToContactsPage();

		//Enter Mandatory fields
		contactsPage.inputForename(Forename);
		contactsPage.inputEmail(Email);
		contactsPage.inputMessage(Message);

		//Click on Submit Button
		contactsPage.clickOnSubmitButton();

		Assert.assertTrue(contactsPage.validateSuccessAlertMessage(expectedMessage),"Fail: Success alert is not correct");
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
