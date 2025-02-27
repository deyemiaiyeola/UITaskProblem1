@api
Feature: Oldest Person API Testing

  Scenario: Successfully retrieve the oldest person ever data
    #Given I want to query the oldest person ever
    When I send a GET request to the oldest person API
    Then the API response status code should be 200
    And the response should contain valid oldest person data
    And the oldest person should be "Jeanne Calment"
    And the oldest person age should be 122
    And the oldest person country should be "France"

  Scenario: Fail to retrieve data with incorrect endpoint
    #Given I want to query the oldest person ever
    When I send a GET request to an invalid oldest person API endpoint
    Then the API response status code should be 404
    And the response should contain an error
