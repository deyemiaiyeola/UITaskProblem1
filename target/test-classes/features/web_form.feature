@ui
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

#Failure scenarios -- just for the fun of it
Scenario: Attempt to interact with disabled field
    Given I am on the web form page
    When I try to interact with the disabled field
    Then the disabled field should remain unchanged

#Does not apply to this specific form since there are not required fields
  #Scenario: Submit form without required fields
    #Given I am on the web form page
    #When I attempt to submit the form without filling any fields
    #Then I check if the form submission was successful
    #And I should see form validation errors

  Scenario: Upload invalid file type
    Given I am on the web form page
    # This assumes you have an invalid file in your testfiles directory
    When I enter "Test User" in the text input field
    And I upload a file "invalid.exe"
    And I submit the form
    Then I check if the form submission was successful