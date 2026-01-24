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
import pages.FormPage;


@Epic("QA Automation")
@Feature("Form Filling")  // for FormTest
public class FormTest {

    private WebDriver driver;
    private FormPage formPage;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options); // independent driver per test
        driver.get("https://demoqa.com/automation-practice-form");
        formPage = new FormPage(driver);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Fill out the form and submit, then validate all submitted values")
    @Severity(SeverityLevel.CRITICAL)

    public void fillForm() {
        formPage.fillPersonalInfo("John", "Doe", "John@mail.com", "5554236811")
                .selectGender("Male")
                .fillDate("02", "September", "1945")
                .fillSubjects(new String[]{"English", "Maths", "Computer Science"})
                .selectHobbies(new String[]{"Reading", "Music"})
                .uploadFile()
                .addAddress(" Karnal H.O., Sadar Bazar ")
                .addStateCity("Haryana", "Karnal")
                .Submit();

        Assert.assertEquals(formPage.getSubmittedValue("Student Name"), "John Doe");
        Assert.assertEquals(formPage.getSubmittedValue("Student Email"), "John@mail.com");
        Assert.assertEquals(formPage.getSubmittedValue("Gender"), "Male");
        Assert.assertEquals(formPage.getSubmittedValue("Mobile"), "5554236811");
        Assert.assertEquals(formPage.getSubmittedValue("Date of Birth"), "02 September,1945");
        Assert.assertEquals(formPage.getSubmittedValue("Subjects"), "English, Maths, Computer Science");
        Assert.assertEquals(formPage.getSubmittedValue("Hobbies"), "Reading, Music");
        Assert.assertEquals(formPage.getSubmittedValue("Picture"), "Rat.jpeg");
        Assert.assertEquals(formPage.getSubmittedValue("Address"), "Karnal H.O., Sadar Bazar");
        Assert.assertEquals(formPage.getSubmittedValue("State and City"), "Haryana Karnal");
    }

}