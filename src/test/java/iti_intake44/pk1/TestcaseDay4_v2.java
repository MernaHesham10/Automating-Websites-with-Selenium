package iti_intake44.pk1;

import org.example.DocumentPagePOM;
import org.example.HomePagePOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestcaseDay4_v2 {

    //Variables
    private WebDriver driver;
    private HomePagePOM homePage;
    private DocumentPagePOM documentPage;

    //Note: Run fn Before all TC start their work
    // BeforeMethod => Before each TC ===========> 7aG

    @BeforeMethod
    public void beforeTestcase() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        new WebDriverWait(driver, Duration.ofSeconds(30));

        homePage = new HomePagePOM(driver);
        documentPage = new DocumentPagePOM(driver);
    }

    @Test
    public void checkPriceValue() {
        homePage.goToHomePage();
        homePage.clickGetPaid();

        /*try {
            String price = documentPage.getPrice();

            String priceExpectedValue = "149";

            Assert.assertTrue(price.contains(priceExpectedValue));

        } catch (TimeoutException e) {
            System.out.println("Price Value cannot found on the page");
        }*/

        String price = documentPage.getPrice();

        String priceExpectedValue = "149";

        Assert.assertTrue(price.contains(priceExpectedValue), "Price Value cannot found on the page");

    }

    //Note: Run fn after all TC finish their work
    // AfterMethod => After each TC ===========> 7aG
    @AfterMethod
    public void afterTestcase() {
        if (driver != null) {
            driver.quit();
        }
    }
}
