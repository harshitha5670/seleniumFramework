package utils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Hooks {
    WebDriver driver;

    @Before
    public void setup() {
        DriverFactory.initializeBrowser("chrome");
        driver = DriverFactory.getDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(Contants.url);
    }
    @After
    public void tearDown() {
        driver.quit();

    }
}
