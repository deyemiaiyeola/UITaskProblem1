package com.webform.automation.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.Test;

@CucumberOptions(
        features = "src/test/resources/features/web_form.feature",
        glue = "com.webform.automation.pages",
        plugin = {
                "pretty",
                "html:target/cucumber-reports/ui-cucumber.html",
                "json:target/cucumber-reports/ui-cucumber.json"
        }
)
@Test(groups={"ui"})
public class UiTestRunner extends AbstractTestNGCucumberTests {
}
