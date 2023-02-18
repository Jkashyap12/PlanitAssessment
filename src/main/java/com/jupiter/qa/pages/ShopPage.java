package com.jupiter.qa.pages;

import com.jupiter.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopPage extends TestBase{

    @FindBy(xpath = "//li//h4[text()='%s']/..//p/a")
    WebElement item ;

    String itemLocator="//li//h4[text()='%s']/..//p/a";

    @FindBy(xpath = "//li[@id='nav-cart']//a[contains(text(),'Cart')]")
    WebElement cartLink;

    public ShopPage() {
        PageFactory.initElements(driver, this);
    }

    public  void clickOnItem(String itemName) {
        String actualItem = itemLocator.replace("%s", itemName);
        WebElement item = driver.findElement(By.xpath(actualItem));
            click(item);

    }
    public CartPage navigateToCartPage(){
        cartLink.click();
        return new CartPage();
    }

}
