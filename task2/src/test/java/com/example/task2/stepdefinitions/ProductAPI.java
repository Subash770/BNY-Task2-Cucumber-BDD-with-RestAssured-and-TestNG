package com.example.task2.stepdefinitions;

//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.restassured.http.ContentType;
//import io.restassured.response.ValidatableResponse;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.Matchers.equalTo;
//
//public class ProductAPI {
//    private ValidatableResponse validatableResponse;
//    private String endpoint = "https://reqres.in/api/users/2";
//
//    @Given("I send a request to the URL to get user details")
//    public void sendRequest(){
//        validatableResponse = given().contentType(ContentType.JSON)
//                .when().get(endpoint).then();
//
//        System.out.println("Response :"+validatableResponse.extract().asPrettyString());
//    }
//    @Then("the response will return status {int} and id {int} and email {string} and first name {string} and last name {string}")
//    public void verifyStatus(int expectedStatusCode, int expectedId, String expectedEmail, String expectedFirstName, String expectedLastName){
//
//        validatableResponse.assertThat().statusCode(expectedStatusCode).body("data.id",equalTo(expectedId)).and()
//                .body("data.email",equalTo(expectedEmail)).body("data.first_name",equalTo(expectedFirstName))
//                .body("data.last_name",equalTo(expectedLastName));
//
//      /*  validatableResponse.assertThat().statusCode(expectedStatusCode);
//
//        validatableResponse.assertThat().body("data.id",equalTo(expectedId));
//
//        validatableResponse.assertThat().body("data.email",equalTo(expectedEmail));
//
//        validatableResponse.assertThat().body("data.first_name",equalTo(expectedFirstName));
//
//        validatableResponse.assertThat().body("data.last_name",equalTo(expectedLastName)); */
//
//    }
//
//
//}
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class ProductAPI {

    private ValidatableResponse validatableResponse;
    private String baseUrl = "http://localhost:8080/products"; // Update if different

    // Step for creating a product
    @Given("I create a product with name {string}, price {double}, category {string}, and description {string}")
    public void createProduct(String name, double price, String category, String description) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", name);
        requestBody.put("price", price);
        requestBody.put("category", category);
        requestBody.put("description", description);

        validatableResponse = given().contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(baseUrl)
                .then();
    }

    // Step for verifying response status
    @Then("the response status should be {int}")
    public void verifyResponseStatus(int expectedStatusCode) {
        validatableResponse.assertThat().statusCode(expectedStatusCode);
    }

    // Step for verifying product details in response
    @And("the response should contain name {string}, price {double}, category {string}, and description {string}")
    public void verifyProductDetails(String name, double price, String category, String description) {
        validatableResponse.assertThat()
                .body("name", equalTo(name))
                .body("price", hasToString(String.valueOf(price)))
                .body("category", equalTo(category))
                .body("description", equalTo(description));
    }

    // Step for handling error message in response
    @And("the response should contain error message {string}")
    public void verifyErrorMessage(String errorMessage) {
        validatableResponse.assertThat()
                .body("error", equalTo(errorMessage));
        System.out.println("Response body: " + validatableResponse.extract().asString());

    }


    // Step for getting product by ID
    @Given("I send a request to get product with ID {int}")
    public void getProductById(int id) {
        validatableResponse = given().contentType(ContentType.JSON)
                .when().get(baseUrl + "/" + id)
                .then();
    }

    // Step for handling response message
    @And("the response should contain message {string}")
    public void verifyResponseMessage(String message) {
        validatableResponse.assertThat().body("message", equalTo(message));
    }

    // Step for handling a product not found case
    @Given("I send a request to get product with ID {int} that doesn't exist")
    public void getProductNotFound(int id) {
        validatableResponse = given().contentType(ContentType.JSON)
                .when().get(baseUrl + "/" + id)
                .then();
        System.out.println("Response body: " + validatableResponse.extract().asString());

    }

    @And("the response should contain error message for product not found {string}")
    public void verifyErrorMessageForNotFoundProduct(String errorMessage) {
        validatableResponse.assertThat()
                .body("error", equalTo(errorMessage));
    }

    // Step for deleting a product (updated)
    @Given("I send a delete request for product with ID {int}")
    public void deleteProduct(int id) {
        validatableResponse = given().contentType(ContentType.JSON)
                .when().delete(baseUrl + "/" + id)
                .then();
    }

    // Step for verifying message after deletion (successful case)
    @And("the response should contain message for successful deletion {string}")
    public void verifySuccessfulDeletionMessage(String message) {
        validatableResponse.assertThat().body("message", equalTo(message));
    }

    // Step for verifying message when product is not found (negative case)
    @And("the response should contain error message for deletion {string}")
    public void verifyDeletionErrorMessage(String errorMessage) {
        validatableResponse.assertThat().body("error", equalTo(errorMessage));
    }
}

