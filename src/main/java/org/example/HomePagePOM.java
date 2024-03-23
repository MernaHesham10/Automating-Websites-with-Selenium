package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePagePOM {

    //Variables
    private WebDriver driver;
    private String googleURL = "https://www.google.com/";
    private String websiteURL = "https://www.levelset.com/";

    //Home Page Variables
    private String getPaidLocStr = "//nav/ul/li[8]/a";


    //Locators
    private By getPaidLocator = By.xpath(getPaidLocStr);


    public HomePagePOM(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage() {
        //Go to Google
        driver.get(googleURL);

        //Go to Website
        driver.navigate().to(websiteURL);
    }

    public DocumentPagePOM clickGetPaid() {
        WebElement getPaidWE = driver.findElement(getPaidLocator);
        getPaidWE.sendKeys(Keys.ENTER);

        //new WebDriverWait(driver, Duration.ofSeconds(30));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'right panel-heading--price')]")));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,\"mob-dropdown-btn\")]")));

        return new DocumentPagePOM(driver);
    }
}
