Feature: Web Form Testing

  Scenario: Fill and submit the web form
    Given I am on the web form page
    When I enter "Test User" in the text input field
    And I enter "password123" in the password field
    And I enter "This is a test." in the textarea
    And I select "One" from the dropdown menu
    And I upload a file "testfile.txt"
    And I check the checkbox
    And I select color "#ff0000"
    And I select date "2023-10-10"
    And I set range value "5"
    Then I submit the form
