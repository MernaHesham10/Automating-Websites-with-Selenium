package iti_intake44.pk1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.time.Duration;


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
    static By addToCartBtnLocator = By.xpath(btnXpath);
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
    By addToCartIcon = By.className("shopping_cart_link");

    //Session 3
    //Note: Task 2
    public static WebElement getAddToCartButton(WebDriver driver, String itemName) {
        //Xpath => //div[contains(@class, 'inventory_item') and .//div[contains(text(), '%s')]]
        //String btnXpath = String.format(addToCartBtnXpath, itemName);

        return driver.findElement(addToCartBtnLocator);
    }

    //Note: Task 2 Test Case
    @Test
    public void addItemFunction() {

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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String itemXpath = String.format("//div[contains(text(),'%s')]", itemName);
        String actualItemText = driver.findElement(By.xpath(itemXpath)).getText();

        // Check if the item div exists and its text contains the expected item name
        if (actualItemText != null) {
            System.out.println("Item Found in Cart Page");

        } else {
            System.out.println("Item cannot Found in Cart Page");
        }

        // Close the browser
        driver.quit();
    }

}
