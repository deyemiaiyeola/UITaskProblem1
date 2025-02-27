package com.webform.automation.pages;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.webform.automation.config.TestConfig;
import org.testng.Assert;

public class WebFormSteps {
    private final WebDriver driver = TestConfig.getDriver();
    private final WebFormPage webFormPage = new WebFormPage(driver);
    private boolean submissionAttempted = false;

    @Given("I am on the web form page")
    public void iAmOnTheWebFormPage() {
        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
    }

    @When("I enter {string} in the text input field")
    public void iEnterInTheTextInputField(String text) {
        webFormPage.fillTextInput(text);
    }

    @When("I enter {string} in the password field")
    public void iEnterInThePasswordField(String password) {
        webFormPage.fillPasswordInput(password);
    }

    @When("I enter {string} in the textarea")
    public void iEnterInTheTextarea(String text) {
        webFormPage.fillTextArea(text);
    }

    @When("I select {string} from the dropdown menu")
    public void iSelectFromTheDropdownMenu(String option) {
        webFormPage.selectFromDropdown(option);
    }

    @When("I upload a file {string}")
    public void iUploadAFile(String fileName) {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testfiles/" + fileName;
        webFormPage.uploadFile(filePath);
    }

    @When("I check the checkbox")
    public void iCheckTheCheckbox() {
        webFormPage.toggleCheckbox();
    }

    @When("I select color {string}")
    public void iSelectColor(String color) {
        webFormPage.selectColor(color);
    }

    @When("I select date {string}")
    public void iSelectDate(String date) {
        webFormPage.selectDate(date);
    }

    @When("I set range value {string}")
    public void iSetRangeValue(String value) {
        webFormPage.setRangeValue(value);
    }

    @Then("I submit the form")
    public void iSubmitTheForm() {
        webFormPage.submitForm();
    }

    //This could have been a possible scenario if the form had any required fields. Since it does not, the test returns and error.
//    @When("I attempt to submit the form without filling any fields")
//    public void iAttemptToSubmitWithoutFillingFields() {
//        submissionAttempted = true;
//        webFormPage.submitForm();
//    }

    @When("I try to interact with the disabled field")
    public void iTryToInteractWithDisabledField() {
        try {
            webFormPage.interactWithDisabledField("test");
            // If we reach here, the interaction didn't throw an exception
            submissionAttempted = false;
        } catch (Exception e) {
            // Expected - element is disabled
            submissionAttempted = true;
        }
    }

    @When("I enter an invalid file {string}")
    public void iEnterAnInvalidFile(String invalidFilePath) {
        String filePath = System.getProperty("user.dir") + "/src/test/resources/testfiles/" + invalidFilePath;
        webFormPage.uploadFile(filePath);
    }

    @Then("I check if the form submission was successful")
    public void iCheckIfFormSubmissionWasSuccessful() {
        // Check if we're on the success page
        String currentUrl = driver.getCurrentUrl();
        boolean isOnSuccessPage = currentUrl.contains("submitted-form.html");

        if (submissionAttempted) {
            // If we attempted to submit and expected failure
            Assert.assertFalse(isOnSuccessPage, "Form was submitted when it should have failed");
        } else {
            // If we expected success
            Assert.assertTrue(isOnSuccessPage, "Form submission failed");
        }
    }

    @Then("the disabled field should remain unchanged")
    public void theDisabledFieldShouldRemainUnchanged() {
        WebElement disabledField = driver.findElement(By.name("my-disabled"));
        Assert.assertEquals(disabledField.getAttribute("value"), "", "Disabled field value changed");
    }

    @Then("I should see form validation errors")
    public void iShouldSeeFormValidationErrors() {
        boolean hasValidationError = webFormPage.hasValidationErrors();
        Assert.assertTrue(hasValidationError, "Expected validation errors not found");
    }
}
