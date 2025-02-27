package com.webform.automation.pages;

import com.webform.automation.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setUp() {
        // Initialize the driver before each scenario if needed
        TestConfig.getDriver();
    }

    @After
    public void tearDown() {
        // Close the browser after each scenario
        TestConfig.closeBrowser();
    }
}
