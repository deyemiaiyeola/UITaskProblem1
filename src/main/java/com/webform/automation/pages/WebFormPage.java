package com.webform.automation.pages;

import com.webform.automation.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class WebFormPage {
    private WebDriver driver;

    // Add these new methods to your existing WebFormPage class

    public void interactWithDisabledField(String text) {
        WebElement disabledInput = driver.findElement(By.name("my-disabled"));
        disabledInput.sendKeys(text);
    }

    public boolean hasValidationErrors() {
        // Check for HTML5 validation messages or custom validation indicators
        // This is a simplified example - you'll need to adjust based on how the form displays errors
        try {
            // Look for elements that indicate validation errors
            // This could be elements with specific classes, attributes, or text
            return !driver.findElements(By.cssSelector("input:invalid")).isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    // You might also need to add a method to check for success indicators
    public boolean isSubmittedSuccessfully() {
        return driver.getCurrentUrl().contains("submitted-form.html");
    }


    @Before
    public void setUp() {
        TestConfig.getDriver();
    }

    @FindBy(id = "my-text-id")
    private WebElement textInput;

    @FindBy(name = "my-password")
    private WebElement passwordInput;

    @FindBy(name = "my-textarea")
    private WebElement textArea;

    @FindBy(name = "my-select")
    private WebElement dropdown;

    @FindBy(name = "my-datalist")
    private WebElement datalist;

    @FindBy(name = "my-file")
    private WebElement fileInput;

    @FindBy(id = "my-check-1")
    private WebElement checkbox;

    @FindBy(name = "my-colors")
    private WebElement colorPicker;

    @FindBy(name = "my-date")
    private WebElement datePicker;

    @FindBy(name = "my-range")
    private WebElement rangeSlider;

    @FindBy(css = "button[type='submit']")
    private WebElement submitButton;

    public WebFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebFormPage() {
        this.driver = TestConfig.getDriver();
        PageFactory.initElements(driver, this);
    }


    public void fillTextInput(String text) {
        textInput.clear();
        textInput.sendKeys(text);
    }

    public void fillPasswordInput(String password) {
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void fillTextArea(String text) {
        textArea.clear();
        textArea.sendKeys(text);
    }

    public void selectFromDropdown(String option) {
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }

    public void selectFromDatalist(String option) {
        datalist.clear();
        datalist.sendKeys(option);
    }

    public void uploadFile(String filePath) {
        fileInput.sendKeys(filePath);
    }

    public void toggleCheckbox() {
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectColor(String color) {
        colorPicker.sendKeys(color);
    }

    public void selectDate(String date) {
        datePicker.clear();
        datePicker.sendKeys(date);
    }

    public void setRangeValue(String value) {
        rangeSlider.clear();
        rangeSlider.sendKeys(value);
    }

    public void submitForm() {
        submitButton.click();
    }

    @After
    public void tearDown() {
        TestConfig.closeBrowser();
    }
}
