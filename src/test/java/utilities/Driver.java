package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This class represents singleton WebDriver object, use this class's get Driver method everywhere in this
 * framework when you need a driver object
 */

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        return driver;
    }

    public static WebDriver quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        return driver;

    }

}
