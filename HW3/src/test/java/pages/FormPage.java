package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FormPage {
    WebDriver driver;

    public FormPage(WebDriver driver) {
        this.driver = driver;

    }
    public void selectGender(String gender)
    {
        driver.findElement(
                By.xpath("//label[text()='" + gender + "']")
        ).click();
    }
}
