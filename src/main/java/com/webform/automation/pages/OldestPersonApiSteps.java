package com.webform.automation.pages;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class OldestPersonApiSteps {

    private Response response;
    private final String BASE_URL = "https://whoistheoldest.com/api/oldest-person-ever";
    private final String INVALID_URL = "https://whoistheoldest.com/api/invalid-endpoint";
    private Scenario scenario;
    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        scenario.log("Starting API test: " + scenario.getName());

        // Configure RestAssured globally
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @After
    public void tearDown() {
        // Log test completion
        if (scenario != null) {
            scenario.log("API test completed: " + scenario.getName());

            // If you want to attach the response body to the report
            if (response != null) {
                scenario.log("Final response status code: " + response.getStatusCode());
                scenario.attach(response.asPrettyString(), "application/json", "API Response");
            }
        }

        // Reset any RestAssured configurations if needed
        RestAssured.reset();
    }

//        @Given("I want to query the oldest person ever")
//    public void iWantToQueryTheOldestPersonEver() {
//        // Setup if needed
//    }

    @When("I send a GET request to the oldest person API")
    public void iSendAGETRequestToTheOldestPersonAPI() {
        response = given()
                .when()
                .get(BASE_URL);
    }

    @When("I send a GET request to an invalid oldest person API endpoint")
    public void iSendAGETRequestToAnInvalidOldestPersonAPIEndpoint() {
        response = given()
                .when()
                .get(INVALID_URL);
    }

    @Then("the API response status code should be {int}")
    public void theAPIResponseStatusCodeShouldBe(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain valid oldest person data")
    public void theResponseShouldContainValidOldestPersonData() {
        response.then()
                .body("id", notNullValue())
                .body("name", not(emptyOrNullString()))
                .body("age", notNullValue())
                .body("country", not(emptyOrNullString()))
                .body("updated_at", not(emptyOrNullString()))
                .body("birthdate", not(emptyOrNullString()));
    }

    @Then("the oldest person should be {string}")
    public void theOldestPersonShouldBe(String name) {
        response.then().body("name", equalTo(name));
    }

    @Then("the oldest person age should be {int}")
    public void theOldestPersonAgeShouldBe(int age) {
        response.then().body("age", equalTo(age));
    }

    @Then("the oldest person country should be {string}")
    public void theOldestPersonCountryShouldBe(String country) {
        response.then().body("country", equalTo(country));
    }

    @Then("the response should contain an error")
    public void theResponseShouldContainAnError() {
        // For 404 responses, we usually check if the response indicates an error
        // This could be a specific error message or status indicator
        // The actual validation depends on how errors are formatted in the API
        response.then().body(not(emptyOrNullString()));
        // If the API returns a specific error format, you can validate it more specifically:
        // response.then().body("error", not(emptyOrNullString()));
    }
}
