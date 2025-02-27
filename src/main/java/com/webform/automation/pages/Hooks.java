package com.webform.automation.pages;

import com.webform.automation.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        //close the browser after the tests are done
        TestConfig.closeBrowser();
    }
}
