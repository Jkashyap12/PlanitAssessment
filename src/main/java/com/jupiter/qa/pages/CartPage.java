package com.jupiter.qa.pages;

import com.jupiter.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.text.DecimalFormat;
import java.util.List;

public class CartPage extends TestBase{
    @FindBy(xpath = "//tr[@class='cart-item ng-scope']//td[text()=' %s']/..//input[@name ='quantity']")
    WebElement itemQuantity;
    @FindBy(xpath="//strong[@class='total ng-binding']")
    WebElement total;
    String itemQuantityLocator="//tr[@class='cart-item ng-scope']//td[text()=' %s']/..//input[@name ='quantity']";
    String priceLocator="//tr[@class='cart-item ng-scope']//td[text()=' %s']/..//td[2]";

    String subtotalLocator ="//tr[@class='cart-item ng-scope']//td[text()=' %s']/..//td[4]";

    private static final DecimalFormat df = new DecimalFormat("0.00");
    public CartPage() {
        PageFactory.initElements(driver, this);
    }

    public void enterQuantity(String itemName, String Quantity){
        String locator = itemQuantityLocator.replace("%s",itemName);
        WebElement itemQuantity = driver.findElement(By.xpath(locator));
        itemQuantity.clear();
        inputData(itemQuantity,Quantity);
        waitForElement(locator);
    }

    public Double getPrice(String itemName){
        return convertPriceToDouble(itemName,priceLocator);
    }

    public Double getSubTotal(String itemName){
        return convertPriceToDouble(itemName,subtotalLocator);
    }

    public Double getQuantity(String itemName){
        String locator = itemQuantityLocator.replace("%s",itemName);
        WebElement itemQuantity = driver.findElement(By.xpath(locator));
        Double value = Double.parseDouble(itemQuantity.getAttribute("value"));
        return value;
    }

    public Double getTotalValue(){
        String totalValue = getText(total).replace("Total: ","").trim();
        Double value=Double.parseDouble(totalValue);
        return value;
    }
    public Double convertPriceToDouble(String itemName,String locator){
        String newLocator = locator.replace("%s",itemName);
        WebElement Quantity = driver.findElement(By.xpath(newLocator));
        String price= getText(Quantity).replace("$","").trim();
        Double value= Double.parseDouble(price);
        return value;
    }

    public boolean verifySubtotalOfEachProduct(String itemName){
          Double expectedSubtotal= getPrice(itemName)*getQuantity(itemName);
          Double actualSubtotal= getSubTotal(itemName);
          if(expectedSubtotal.compareTo(actualSubtotal)==0){
              return true;
          }
          else{
              return false;
          }
    }

    public boolean verifyTotalValueWithSubtotal(List<String> itemName){
        Double expectedSum =0.0;
        for(String subItem :itemName) {
            expectedSum+=getSubTotal(subItem);
        }
        Double actualSum = getTotalValue();
        if((actualSum.compareTo(expectedSum))==0)
        {return true;
        }
        else{
            return false;
        }
    }
}
