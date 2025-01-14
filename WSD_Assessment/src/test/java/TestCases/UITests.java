package TestCases;

import config.Config;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.CartPage;

import java.time.Duration;

public class UITests {
    private WebDriver driver;
    private WebDriverWait wait;
    private SearchPage searchPage;
    private CartPage cartPage;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", Config.DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get(Config.BASE_URL);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        searchPage = new SearchPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void testSearchFunctionality() {
        searchPage.searchProduct("Fusion");

  
        WebElement searchResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".search.results")));
        Assert.assertTrue(searchResult.getText().contains("Fusion"), "Product not found in search results.");
    }

    @Test
    public void testAddToCart() {
        searchPage.searchProduct("Orestes Fitness Short");

        
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Orestes Fitness Short")));
        productLink.click();

        
        WebElement colorOption = wait.until(ExpectedConditions.elementToBeClickable(By.id("option-label-color-93-item-49")));
        colorOption.click();

        
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        addToCartButton.click();

    
        String productInCart = cartPage.getProductNameFromCart();
        Assert.assertEquals(productInCart, "Orestes Fitness Short", "Product not found in cart.");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
