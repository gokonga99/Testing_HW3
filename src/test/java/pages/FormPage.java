package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage {
    WebDriver driver;
    WebDriverWait wait;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    private void sendKeysToElement(By locator, String value) {
      WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
      el.clear();
      el.sendKeys(value);
    }
    private void sendKeysWithEnter(By locator, String value) {
        WebElement el=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(value);
        el.sendKeys(Keys.ENTER);
    }
    private void scrolling(By locator) {
        WebElement el = driver.findElement(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }
    private void clickWhenReady(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    public void fillPersonalInfo(
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {
        sendKeysToElement(By.id("firstName"),firstName);
        sendKeysToElement(By.id("lastName"),lastName);
        sendKeysToElement(By.id("userEmail"),email);
        sendKeysToElement(By.id("userNumber"),phoneNumber);
    }

    public void selectGender(String gender) {

       clickWhenReady(By.xpath("//label[text()='" + gender + "']"));

    }

    public void fillDate(String day, String month, String year) {
        scrolling(By.id("dateOfBirthInput"));
        clickWhenReady(By.id("dateOfBirthInput"));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".react-datepicker__month-select")));
        new Select(driver.findElement(By.cssSelector(".react-datepicker__month-select"))).selectByVisibleText(month);
        new Select(driver.findElement(By.cssSelector(".react-datepicker__year-select"))).selectByVisibleText(year);
        driver.findElement(By.xpath(
                        "//div[contains(@class,'react-datepicker__day') and text()='" + Integer.parseInt(day) + "']"))
                .click();

//        alternative
//        ((JavascriptExecutor) driver)
//                .executeScript("document.getElementById('dateOfBirthInput').value='" + day + " " + month + " " + year + "'");
//        WebElement dateOfBirth = driver.findElement(By.id("dateOfBirthInput"));
//        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",dateOfBirth);
    }
    public void fillSubjects(String [] subjects) {
        for (String subject : subjects){
            sendKeysWithEnter(By.id("subjectsInput"), subject);
        }
    }
    public void selectHobbies(String[] hobbies) {
        for(String hobby : hobbies){
        clickWhenReady(By.xpath("//label[text()='"+hobby+"']"));
        }
    }
    public void uploadFile(){
        WebElement Picture=driver.findElement(By.id("uploadPicture"));
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", Picture);

        Picture.sendKeys("/home/gokonga/IdeaProjects/Testing_HW3/src/test/java/resources/Rat.jpeg");
    }

    public void addAddress(String address){

      sendKeysToElement(By.id("currentAddress"), address);
    }

    public void addStateCity(String State,String city){
        sendKeysWithEnter(By.cssSelector("#react-select-3-input"), State);
        sendKeysWithEnter(By.cssSelector("#react-select-4-input"), city);
    }
    public void Submit(){
        scrolling(By.id("submit"));
        clickWhenReady(By.id("submit"));
    }


    public String getSubmittedValue(String label) {
        WebElement cell=wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[text()='" + label + "']/following-sibling::td")
        ));
        return cell.getText();

    }
}
