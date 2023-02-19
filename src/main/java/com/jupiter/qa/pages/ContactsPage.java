package com.jupiter.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.jupiter.qa.base.TestBase;

public class ContactsPage extends TestBase {

	@FindBy(xpath = "//a[contains(text(),'Submit')]")
	WebElement submitButton;
	@FindBy(css="span[id='forename-err']")
	WebElement ForenameValidation;
	@FindBy(css="span[id='email-err']")
	WebElement EmailValidation;
	@FindBy(css="span[id='message-err']")
	WebElement MessageValidation;
	@FindBy(xpath= "//input[@id='forename']")
	WebElement ForenameMandatoryField;
	@FindBy(xpath= "//input[@id='email']")
	WebElement EmailMandatoryField;
	@FindBy(xpath= "//textarea[@id='message']")
	WebElement MessageMandatoryField;
	@FindBy(xpath = "//div[@class='alert alert-success']")
	WebElement SuccessAlertMessage;
	@FindBy(xpath = "//div[@class='alert alert-success']/strong")
	WebElement SuccessAlertName;
	String alertMessageLocator ="//div[@class='alert alert-success']";
	String forenameError= "span[id='forename-err']";
	String emailError="span[id='email-err']";
	String messageError="span[id='message-err']";
	// Initializing the Page Objects:
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}

	public void clickOnSubmitButton(){
		click(submitButton);
	}

	public boolean validateForenameErrorMessages(String text){
		return getText(ForenameValidation).equals(text);
	}

	public boolean validateEmailErrorMessages(String text){
		return getText(EmailValidation).equals(text);
	}

	public boolean validateMessageErrorMessages(String text){
		return getText(MessageValidation).equals(text);
	}

	public void inputForename(String text){
		inputData(ForenameMandatoryField,text);
	}

	public void inputEmail(String text){
		inputData(EmailMandatoryField,text);
	}

	public void inputMessage(String text){
		inputData(MessageMandatoryField,text);
	}

	public boolean validateSuccessAlertMessage(String alertMessage){
		waitForElement(alertMessageLocator);
		String message =getText(SuccessAlertMessage);
		return message.equals(alertMessage);
	}

	public boolean isForenameValidationPresent(){
		return (driver.findElements(By.cssSelector(forenameError)).size()>0);
	}

	public boolean isEmailValidationPresent(){
		return (driver.findElements(By.cssSelector(emailError)).size()>0);
	}
	public boolean isMessageValidationPresent(){
		return (driver.findElements(By.cssSelector(messageError)).size()>0);
	}
}
