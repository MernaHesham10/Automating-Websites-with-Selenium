package iti_intake44.pk1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;
import java.util.List;


/**
 * This class created by
 *
 * @author Merna Hesham
 */

public class TestcaseDay3 {

    //Login Page Variables
    static String userName = "standard_user";
    static String userPassword = "secret_sauce";
    //Home Page Variables
    static String itemName = "Sauce Labs Backpack";
    static String addToCartBtnXpath = "//div[contains(@class, 'inventory_item') and .//div[contains(text(), '%s')]]//button";
    static String btnXpath = String.format(addToCartBtnXpath, itemName);

    //Variables
    WebDriver driver = new ChromeDriver();
    String URL = "https://www.google.com/";

    //Locators
    String websiteURL = "https://www.saucedemo.com/";
    //Login Page Locators
    By userNameLocator = By.id("user-name");
    By userPasswordLocator = By.id("password");
    By loginBtnLocator = By.id("login-button");

    //Home Page Locators
    By inventoryItemLocator = By.className("inventory_item");
    By addToCartIcon = By.className("shopping_cart_link");

    //Session 3
    //Note: Task 1 Test Case
    //Get The number of inventory items
    @Test
    public void testDemo() {
        //Max browser window
        driver.manage().window().maximize();

        //Go to google
        driver.get(URL);

        //Go to website
        driver.navigate().to(websiteURL);

        //Note: username
        driver.findElement(userNameLocator).clear();
        driver.findElement(userNameLocator).sendKeys(userName);

        //Note: user password
        driver.findElement(userPasswordLocator).clear();
        driver.findElement(userPasswordLocator).sendKeys(userPassword);

        driver.findElement(loginBtnLocator).sendKeys(Keys.ENTER);


        //Note:
        List<WebElement> inventoryItems = driver.findElements(inventoryItemLocator);

        // Get # product items
        int inventoryItemCounter = inventoryItems.size();

        System.out.println("Inventory Items Number: " + inventoryItemCounter);

        Assert.assertEquals(inventoryItems.size(), 6, "Inventory Items Number not Equal to 6");

        driver.quit();
    }


    //Note: Task 2
    public static WebElement getAddToCartButton(WebDriver driver, String itemName) {
        //Xpath => //div[contains(@class, 'inventory_item') and .//div[contains(text(), '%s')]]
        String btnXpath = String.format("//div[contains(@class, 'inventory_item') and .//div[contains(text(), '%s')]]//button", itemName);

        return driver.findElement(By.xpath(btnXpath));
    }



    //Note: Task 2 Test Case
    @Test
    public void addItemFunction(){

        //Max browser window
        driver.manage().window().maximize();

        //Go to Google
        driver.get(URL);

        //Go to Website
        driver.navigate().to(websiteURL);

        //Note: username
        driver.findElement(userNameLocator).clear();
        driver.findElement(userNameLocator).sendKeys(userName);

        //Note: user password
        driver.findElement(userPasswordLocator).clear();
        driver.findElement(userPasswordLocator).sendKeys(userPassword);

        //CLick login btn
        driver.findElement(loginBtnLocator).sendKeys(Keys.ENTER);

        //Note: Send Item Name
        // and retrieve btn that includes the text that i has sent to the function
        WebElement addBtn = getAddToCartButton(driver, itemName);
        addBtn.click();

        //Go to Cart Page
        driver.findElement(addToCartIcon).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String itemXpath = String.format("//div[contains(text(),'%s')]", itemName);
        //String actualItemText = driver.findElement(By.xpath(itemXpath)).getText();

        By actualItem = By.xpath(itemXpath);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(actualItem));
            System.out.println("Item Found in Cart Page");
        } catch (TimeoutException e) {
            System.out.println("Item cannot Found in Cart Page");
        }

        // Check if the item div exists and its text contains the expected item name
        /*if (actualItemText != null) {
            System.out.println("Item Found in Cart Page");

        } else {
            System.out.println("Item cannot Found in Cart Page");
        }*/

        // Close the browser
        driver.quit();
    }
}
