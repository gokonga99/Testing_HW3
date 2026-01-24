package pages;

import base.BasePage;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.util.Arrays;


public class FormPage extends BasePage {


    private By firstNameInput = By.id("firstName");
    private By lastNameInput = By.id("lastName");
    private By emailInput = By.id("userEmail");
    private By phoneInput = By.id("userNumber");
    private String genderInput = ("//label[text()='%s']");
    private By dateInput = By.id("dateOfBirthInput");
    private By monthInput = By.cssSelector(".react-datepicker__month-select");
    private By yearInput = By.cssSelector(".react-datepicker__year-select");
    private String dayInput = ("//div[contains(@class,'react-datepicker__day') and text()='%s']");
    private By subjectInput = By.id("subjectsInput");
    private String hobbyInput = ("//label[text()='%s']");
    private By pictureInput = By.id("uploadPicture");
    private static final String PICTURE_PATH = new File("src/test/resources/Rat.jpeg").getAbsolutePath();
    private By adressInput = By.id("currentAddress");
    private By stateInput = By.cssSelector("#react-select-3-input");
    private By cityInput = By.cssSelector("#react-select-4-input");
    private By submitButton = By.id("submit");


    public FormPage(WebDriver driver) {
        super(driver);
    }
    @Step ("Fill out personal information| first and last name:{firstName}-{lastName};" +
            "email: {email}; phone number {phoneNumber}")
    @Attachment(value = "Personal information", type = "image/png")
    public FormPage fillPersonalInfo(
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {
        sendKeysToElement(firstNameInput, firstName);
        sendKeysToElement(lastNameInput, lastName);
        sendKeysToElement(emailInput, email);
        sendKeysToElement(phoneInput, phoneNumber);
        attachScreenshot("Personal information");
        return this;
    }
    @Step("Select gender: {gender}")

    public FormPage selectGender(String gender) {
        clickWhenReady(By.xpath(String.format(genderInput, gender)));
        return this;
    }
    @Step("Fill date of birth: {day}-{month}-{year}")

    public FormPage fillDate(String day, String month, String year) {

        scrollTo(dateInput);
        clickWhenReady(dateInput);
        new Select(waitForVisible(monthInput)).selectByVisibleText(month);
        new Select(waitForVisible(yearInput)).selectByVisibleText(year);
        clickWhenReady(By.xpath(String.format(dayInput, Integer.parseInt(day))));
        attachScreenshot("Date of birth");
        return this;
    }
    @Step("Fill subjects {subjectsStr}")
    public FormPage fillSubjects(String[] subjects) {
        String subjectsStr = Arrays.toString(subjects); // convert array to string
        for (String subject : subjects) {
            sendKeysToElement(subjectInput, subject);
            By optionLocator = By.xpath(
                    "//div[contains(@class,'subjects-auto-complete__option') and text()='" + subject + "']");
            clickWhenReady(optionLocator);
        }
        attachScreenshot("Subjects");
        return this;
    }

    @Step("Select hobbies {hobbiesStr} ")
    public FormPage selectHobbies(String[] hobbies) {
        String hobbiesStr = Arrays.toString(hobbies);
        for (String hobby : hobbies) {
            clickWhenReady(By.xpath(String.format(hobbyInput, hobby)));
        }
        attachScreenshot("Hobbies");
        return this;
    }
    @Step ("Uploads a File (PLACE HOLDER)")
    public FormPage uploadFile() {
        scrollTo(pictureInput);

        sendKeysToElement(pictureInput, PICTURE_PATH);
        return this;
    }
    @Step ("Add a specific address: {address}")
    public FormPage addAddress(String address) {

        sendKeysToElement(adressInput, address);
        attachScreenshot("Address");
        return this;
    }
    @Step ("Pick a State {state} and a city {city}")
    public FormPage addStateCity(String State, String city) {
        sendKeysWithEnter(stateInput, State);
        sendKeysWithEnter(cityInput, city);
        attachScreenshot("State, city");
        return this;
    }
    @Step("Submit your information")
    public FormPage Submit() {
        scrollTo(submitButton);
        clickWhenReady(submitButton);
        attachScreenshot("Form submission");
        return this;
    }


    public String getSubmittedValue(String label) {
            WebElement cell = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[text()='" + label + "']/following-sibling::td")
        ));
        return cell.getText();

    }

}
