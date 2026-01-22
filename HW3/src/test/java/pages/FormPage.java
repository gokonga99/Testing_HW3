package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FormPage {
    WebDriver driver;
    WebDriverWait wait;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    public void fillPersonalInfo(
            String firstName,
            String lastName,
            String email,
            String phoneNumber
    ) {
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("userNumber")).sendKeys(phoneNumber);
    }

    public void selectGender(String gender) {

        driver.findElement(
                By.xpath("//label[text()='" + gender + "']")
        ).click();
    }

    public void fillDate(String day, String month, String year) {
       WebElement dateOfBirth=driver.findElement(By.id("dateOfBirthInput"));
        wait.until(ExpectedConditions.elementToBeClickable(dateOfBirth));
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", dateOfBirth);
        dateOfBirth.click();
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
        WebElement subjectsInput= driver.findElement(By.id("subjectsInput"));
        for (String subject : subjects){
            subjectsInput.sendKeys(subject);
            subjectsInput.sendKeys(Keys.ENTER);
        }
    }
    public void selectHobbies(String[] hobbies) {
        for(String hobby : hobbies){
            driver.findElement(By.xpath("//label[text()='"+hobby+"']")).click();
        }
    }
    public void uploadFile(){
        WebElement Picture=driver.findElement(By.id("uploadPicture"));
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", Picture);

        Picture.sendKeys("/home/gokonga/IdeaProjects/Testing_HW3/HW3/src/test/java/resources/Rat.jpeg");
    }

    public void addAddress(String address){

      driver.findElement(By.id("currentAddress")).sendKeys(address);
    }

    public void addStateCity(String State,String city){
        WebElement stateElement=driver.findElement(By.cssSelector("#react-select-3-input"));
        stateElement.sendKeys(State);
        stateElement.sendKeys(Keys.ENTER);
        WebElement cityElement=driver.findElement(By.cssSelector("#react-select-4-input"));
        cityElement.sendKeys(city);
        cityElement.sendKeys(Keys.ENTER);
    }
    public void Submit(){
        WebElement submit=driver.findElement(By.id("submit"));
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", submit);
        submit.click();
    }


    public String getSubmittedValue(String label) {
        WebElement cell=wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//td[text()='" + label + "']/following-sibling::td")
        ));
        return cell.getText();

    }
}

// my sake ((JavascriptExecutor) driver)
//    .executeScript("document.getElementById('dateOfBirthInput').value='21 Jan 2026'");