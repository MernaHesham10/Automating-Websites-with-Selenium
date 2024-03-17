package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DocumentPagePOM {

    //Variables
    private WebDriver driver;

    private String priceLocStr =  "//div[contains(@class, 'right panel-heading--price') and .//span[contains(text(), '149')]]";

    //Locators
    private By priceLocator =  By.xpath(priceLocStr);


    public DocumentPagePOM(WebDriver driver) {
        this.driver = driver;
    }

    public String getPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // can see price
        wait.until(ExpectedConditions.visibilityOfElementLocated(priceLocator));

        WebElement priceElement = driver.findElement(priceLocator);

        String price = priceElement.getText();


        return price;
    }
}
