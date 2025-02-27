package com.webform.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/oldest_person_api.feature",
        glue = "com.webform.automation.stepdefs.api"
        // other options
)
public class ApiTestRunner extends AbstractTestNGCucumberTests {
}
