package com.webform.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/oldest_person_api.feature",
        glue = "com.webform.automation.pages",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/api-cucumber.html",
                "json:target/cucumber-reports/api-cucumber.json"
        }
        // other options
)
@Test(groups={"api"})
public class ApiTestRunner extends AbstractTestNGCucumberTests {

        @BeforeClass
        public static void setup() {
                System.setProperty("test.type","api");
        }
}
