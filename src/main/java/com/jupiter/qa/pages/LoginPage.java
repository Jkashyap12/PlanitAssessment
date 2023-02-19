package com.jupiter.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jupiter.qa.base.TestBase;
// Placeholder class in order to develop Login tests if required
public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(id="loginUserName")
	WebElement username;
	@FindBy(id="loginPassword")
	WebElement password;
	@FindBy(xpath="//button[@type='submit']")
	WebElement loginBtn;

	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}

}
