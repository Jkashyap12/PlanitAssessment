/*
 * @author Naveen Khunteta
 * 
 */

package com.jupiter.qa.testcases;

import com.jupiter.qa.base.TestBase;
import com.jupiter.qa.pages.*;
import com.jupiter.qa.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jupiter.qa.util.TestUtil.getCellValue;

public class ThirdTestCase extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ShopPage shopPage;
	CartPage cartPage;

	String sheetName = "shop";


	public ThirdTestCase(){
		super();

	}
	@BeforeMethod
	public void setUp() throws InterruptedException {

		initialization();
		testUtil = new TestUtil();
		loginPage = new LoginPage();
		shopPage = new ShopPage();
		homePage = new HomePage();
		cartPage= new CartPage();
		TestUtil.runTimeInfo("error", "login successful");
		}

	@Test(priority=1)
	public void verifyTotalSumOfItems(){
		Double d1 =Double.parseDouble(getCellValue(sheetName,1,2));
		Double d2 =Double.parseDouble(getCellValue(sheetName,2,2));;
		Double d3=Double.parseDouble(getCellValue(sheetName,3,2));;

		List<String> itemName = new ArrayList<String>(Arrays.asList(getCellValue(sheetName,1,0),getCellValue(sheetName,2,0),getCellValue(sheetName,3,0)));
		shopPage = homePage.navigateToShopPage();
		shopPage.clickOnItem(getCellValue(sheetName,1,0));
		shopPage.clickOnItem(getCellValue(sheetName,2,0));
		shopPage.clickOnItem(getCellValue(sheetName,3,0));

		cartPage = shopPage.navigateToCartPage();
		cartPage.enterQuantity(getCellValue(sheetName,1,0),getCellValue(sheetName,1,1));
		cartPage.enterQuantity(getCellValue(sheetName,2,0),getCellValue(sheetName,2,1));
		cartPage.enterQuantity(getCellValue(sheetName,3,0),getCellValue(sheetName,3,1));

		//Verify that subtotal of each item is correct
		Assert.assertTrue(cartPage.verifySubtotalOfEachProduct(getCellValue(sheetName,1,0)),"Fail: Subtotal value for "+getCellValue(sheetName,1,0)+"didn't match");
		Assert.assertTrue(cartPage.verifySubtotalOfEachProduct(getCellValue(sheetName,2,0)),"Fail: Subtotal value for "+getCellValue(sheetName,2,0)+"didn't match");
		Assert.assertTrue(cartPage.verifySubtotalOfEachProduct(getCellValue(sheetName,3,0)),"Fail: Subtotal value for "+getCellValue(sheetName,3,0)+"didn't match");

		//Verify that price of each item is correct
		Assert.assertEquals((cartPage.getPrice(getCellValue(sheetName,1,0))),d1,"Fail: Price of "+getCellValue(sheetName,1,0)+"item is incorrect");
		Assert.assertEquals((cartPage.getPrice(getCellValue(sheetName,2,0))),d2,"Fail: Price of "+getCellValue(sheetName,2,0)+"item is incorrect");
		Assert.assertEquals((cartPage.getPrice(getCellValue(sheetName,3,0))),d3,"Fail: Price of "+getCellValue(sheetName,3,0)+"item is incorrect");

		//verify that total is equal to sum of each subtotal

		Assert.assertTrue(cartPage.verifyTotalValueWithSubtotal(itemName),"Fail:Sum is incorrect");


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
