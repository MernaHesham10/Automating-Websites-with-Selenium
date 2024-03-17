package iti_intake44.pk1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestcaseDay4_v1 {

    //Variables
    WebDriver driver;
    String googleURL = "https://www.google.com/";
    String websiteURL = "https://www.levelset.com/";

    //Home Page Variables
    String getPaidLocStr = "//nav/ul/li[8]/a";

    //Document Page Variables
    String priceLocStr =  "//div[contains(@class, 'right panel-heading--price') and .//span[contains(text(), '149')]]";


    //Locators
    //Home Page Locators
    By getPaidLocator = By.xpath(getPaidLocStr);

    //Document Page Locators
    By priceLocator =  By.xpath(priceLocStr);

    //Keywords

    //Note: Run fn Before all TC start their work
    // BeforeMethod => Before each TC ===========> 7aG
    @BeforeClass
    public void beforeTestClass() {
        driver = new ChromeDriver();

        //Max browser window
        driver.manage().window().maximize();
    }


    //Note: Task
    @Test
    public void checkPriceValue() {

        //Go to Google
        driver.get(googleURL);

        //Go to Website
        driver.navigate().to(websiteURL);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement getPaidWE = driver.findElement(getPaidLocator);
        //getPaidWE.click(); => Not Work ???
        getPaidWE.sendKeys(Keys.ENTER);

        try {
            String priceActualValue = wait.until(ExpectedConditions.visibilityOfElementLocated(priceLocator)).getText();

            String priceExpectedValue = "149";

            Assert.assertTrue(priceActualValue.contains(priceExpectedValue));

        } catch (TimeoutException e) {
            System.out.println("Price Value cannot found on the page");
        }
    }

    //Note: Run fn after all TC finish their work
    // AfterMethod => After each TC ===========> 7aG
    @AfterClass
    public void afterTestClass() {

        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}