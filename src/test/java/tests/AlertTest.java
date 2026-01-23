    package tests;

    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.chrome.ChromeOptions;
    import org.testng.Assert;
    import org.testng.annotations.BeforeClass;
    import org.testng.annotations.Test;
    import pages.AlertPage;

    public class AlertTest {
        WebDriver driver;
        AlertPage alertPage;
        @BeforeClass
        public void setUp()
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.get("https://demoqa.com/alerts");
            alertPage = new AlertPage(driver);
        }
        @Test
        public void simpleAlertTest()
        {
            alertPage.smipleAlert();
        }
        @Test
        public void timedAlertTest()
        {
            alertPage.timedAlert();
        }
        @Test
        public void confirmAlertTest()
        {
            alertPage.confirmAlert(false);
            Assert.assertEquals(alertPage.getAlertText("confirmResult"),"You selected Cancel");
        }
        @Test
        public void promptAlertTest()
        {
            alertPage.promptAlert("John",true);
            Assert.assertEquals(alertPage.getAlertText("promptResult"),"You entered John");
        }

    }
