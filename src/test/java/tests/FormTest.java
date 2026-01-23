package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
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
        formPage.fillPersonalInfo("John", "Doe","John@mail.com","5554236811");
        formPage.selectGender("Male");
        //day,month,year
        formPage.fillDate("02","September","1945");
        formPage.fillSubjects(new String[]{"English","Math","Computer Science"});
        formPage.selectHobbies(new String[]{"Reading","Music"});
        formPage.uploadFile();
        formPage.addAddress(
                " Karnal H.O., Sadar Bazar "
        );
        formPage.addStateCity("Haryana","Karnal");
        formPage.Submit();
        Assert.assertEquals(formPage.getSubmittedValue("Student Name"),"John Doe");
        Assert.assertEquals(formPage.getSubmittedValue("Student Email"),"John@mail.com");
        Assert.assertEquals(formPage.getSubmittedValue("Gender"),"Male");
        Assert.assertEquals(formPage.getSubmittedValue("Mobile"),"5554236811");
        Assert.assertEquals(formPage.getSubmittedValue("Date of Birth"),"02 September,1945");
        Assert.assertEquals(formPage.getSubmittedValue("Subjects"),"English, Maths, Computer Science");
        Assert.assertEquals(formPage.getSubmittedValue("Hobbies"),"Reading, Music");
        Assert.assertEquals(formPage.getSubmittedValue("Picture"),"Rat.jpeg");
        Assert.assertEquals(formPage.getSubmittedValue("Address"),"Karnal H.O., Sadar Bazar");
        Assert.assertEquals(formPage.getSubmittedValue("State and City"),"Haryana Karnal");
    }

}