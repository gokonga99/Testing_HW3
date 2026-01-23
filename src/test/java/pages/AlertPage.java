package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertPage {
    WebDriver driver;
    WebDriverWait wait;
    public AlertPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private void clickWhenReady(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    private void scrollTo(By locator) {
        WebElement el = driver.findElement(locator);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }
    public void smipleAlert(){
        clickWhenReady(By.id("alertButton"));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert =driver.switchTo().alert();
        alert.accept();
    }
    public void timedAlert(){
        clickWhenReady(By.id("timerAlertButton"));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert =driver.switchTo().alert();
        alert.accept();

    }
    public void confirmAlert(boolean confirmCancel){
        clickWhenReady(By.id("confirmButton"));
        Alert alert =driver.switchTo().alert();

        if(confirmCancel){
            alert.accept();
        }else{
            alert.dismiss();
        }
    }
    public void promptAlert(String name,boolean confirmCancel){
        scrollTo(By.id("promtButton"));
        clickWhenReady(By.id("promtButton"));
        Alert alert =driver.switchTo().alert();
        if(confirmCancel){
            alert.sendKeys(name);
            alert.accept();
        }
        else {
            alert.dismiss();
        }
    }
    public String getAlertText(String label){
       WebElement call=wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id(label)
       ));
       return call.getText();

    }
}
