package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.FormPage;


public class FormTest {

    WebDriver driver;
    FormPage formPage;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.get("https://demoqa.com/automation-practice-form");
        formPage=new FormPage(driver);

    }

    @Test
    public void fillForm() {

        formPage.selectGender("Male");

    }
    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}