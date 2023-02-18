package com.jupiter.qa.pages;

import com.jupiter.qa.base.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'Home')]")
	WebElement homeLink;
	
	@FindBy(xpath = "//a[contains(text(),'Start Shopping')]")
	WebElement startShoppingButton;

	@FindBy(xpath = "//li[@id='nav-shop']//a[contains(text(),'Shop')]")
	WebElement shopLink;

	@FindBy(xpath = "//li[@id='nav-contact']//a[contains(text(),'Contact')]")
	WebElement contactLink;

	// Initializing the Page Objects:
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}

	public ContactsPage navigateToContactsPage(){
		contactLink.click();
		return new ContactsPage();
	}
	
	public ShopPage navigateToShopPage(){
		shopLink.click();
		return new ShopPage();
	}


}
