package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AlertPage extends BasePage {
    private By simpleButton = By.id("alertButton");
    private By timedButton = By.id("timerAlertButton");
    private By confirmButton = By.id("confirmButton");
    private By promptButton = By.id("promtButton");

    public AlertPage(WebDriver driver) {
        super(driver);
    }
    @Step("Click simple alert button and accept")
    public AlertPage simpleAlert() {
        clickWhenReady(simpleButton);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        attachScreenshot("simpleAlert");
        return this;
    }
    @Step("Click timed alert button and accept after delay")
    public AlertPage timedAlert() {
        clickWhenReady(timedButton);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        attachScreenshot("timedAlert");
        return this;
    }
    @Step("Click confirm alert button and accept or dismiss :{confirmCancel}")
    public AlertPage confirmAlert(boolean confirmCancel) {
        clickWhenReady(confirmButton);
        Alert alert = driver.switchTo().alert();
        wait.until(ExpectedConditions.alertIsPresent());
        if (confirmCancel) {
            alert.accept();
        } else {
            alert.dismiss();
        }
        attachScreenshot("confirmAlert");
        return this;
    }
    @Step("Get alert result text for '{resultId}'")
    public AlertPage promptAlert(String name, boolean confirmCancel) {
        scrollTo(promptButton);
        clickWhenReady(promptButton);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        if (confirmCancel) {
            alert.sendKeys(name);
            alert.accept();
        } else {
            alert.dismiss();
        }
        attachScreenshot("promptAlert");
        return this;
    }


    public String getAlertText(String label) {
        WebElement call = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id(label)
        ));
        return call.getText();

    }
}
