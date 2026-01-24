package tests;

import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.AlertPage;

@Epic("QA Automation")
@Feature("Alerts Handling")
public class AlertTest {
    private WebDriver driver;
    private AlertPage alertPage;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options); // independent driver per test
        driver.get("https://demoqa.com/alerts");
        alertPage = new AlertPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


    @Test
    @Description("Trigger and close a simple alert")
    @Severity(SeverityLevel.CRITICAL)

    public void simpleAlertTest() {

        alertPage.simpleAlert();
    }


    @Test
    @Description("Trigger and close a timed alert")
    @Severity(SeverityLevel.CRITICAL)

    public void timedAlertTest() {
        alertPage.timedAlert();
    }


    @Test
    @Description("Trigger and close a confirmation alert")
    @Severity(SeverityLevel.CRITICAL)

    public void confirmAlertTest() {
        alertPage.confirmAlert(false);
        Assert.assertEquals(alertPage.getAlertText("confirmResult"), "You selected Cancel");
    }


    @Test
    @Description("Trigger, enter your name and close a confirmation alert")
    @Severity(SeverityLevel.CRITICAL)

    public void promptAlertTest() {
        alertPage.promptAlert("John", true);
        Assert.assertEquals(alertPage.getAlertText("promptResult"), "You entered John");
    }

}
