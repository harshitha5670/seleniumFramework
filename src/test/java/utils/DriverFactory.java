package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;

public class DriverFactory {
static  WebDriver driver= null;

    public static void initializeBrowser(String browserName) {
        if(browserName.equalsIgnoreCase("chrome")) {
            String path = System.getProperty("user.dir") +
                    File.separator+ "drivers" + File.separator + "chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                    File.separator + "drivers" + File.separator + "geckodriver");
            driver = new FirefoxDriver();
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }


}
