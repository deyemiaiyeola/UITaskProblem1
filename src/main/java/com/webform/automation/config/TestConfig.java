package com.webform.automation.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.time.Duration;

public class TestConfig {
    private static WebDriver driver;
    private static final String BROWSER = System.getProperty("browser", "chrome");
    private static final int IMPLICIT_WAIT = 10;

    public static WebDriver getDriver() {

        if("api".equals(System.getProperty("test.type"))){
            return null;
        }


        if (driver == null) {
//            initializeDriver();
            driver = new ChromeDriver();
        }
        return driver;
    }

    private static void initializeDriver() {
        switch (BROWSER.toLowerCase()) {
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                driver = new EdgeDriver(edgeOptions);
                break;
            case "chrome":
            default:
                ChromeOptions chromeOptions = new ChromeOptions();
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        // Configure timeout and window size
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT));
        driver.manage().window().maximize();
    }

    public static void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
