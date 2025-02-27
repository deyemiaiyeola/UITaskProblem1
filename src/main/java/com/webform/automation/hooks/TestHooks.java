package com.webform.automation.hooks;


import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class TestHooks {
    private WebDriver driver;

    @Before("@ui")
    public void setUpUiTest() {
        // Initialize WebDriver only for UI tests
        // driver = new ChromeDriver();
    }

    @Before("@api")
    public void setUpApiTest() {
        // API test setup without WebDriver
    }
}