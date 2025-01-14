package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    private By findCartButton = By.xpath("//header/div[2]/div[1]/a[1]");
    private By productName = By.cssSelector(".product-item-name");
    private By findDetailsButton =By.xpath("//span[contains(text(),'See Details')]");
    
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductNameFromCart() {
        driver.findElement(findCartButton).click();
        return driver.findElement(productName).getText();
    }
    public void seeDetails() {
    	driver.findElement(findDetailsButton).click();
    }
}
