package com.example.task2.stepdefinitions;

import com.example.task2.utils.ExcelUtils;
import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import java.util.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductAPI {

    private ValidatableResponse response;
    private final String baseUrl = "http://localhost:8080/products";

    @Given("I run create product tests from {string}")
    public void i_run_create_product_tests_from_excel(String sheetName) {
        List<Map<String, String>> testDataList = ExcelUtils.getTestData(sheetName);

        for (Map<String, String> data : testDataList) {
            Map<String, Object> body = new HashMap<>();
            body.put("name", data.get("name"));
            body.put("price", Double.parseDouble(data.get("price")));
            body.put("category", data.get("category"));
            body.put("description", data.get("description"));

            response = given().contentType(ContentType.JSON)
                    .body(body)
                    .when().post(baseUrl)
                    .then().log().all();

            if (sheetName.equals("CreateProductSuccess")) {
                response.statusCode(200)
                        .body("name", equalTo(data.get("name")))
                        .body("price", hasToString(data.get("price")))
                        .body("category", equalTo(data.get("category")))
                        .body("description", equalTo(data.get("description")));
            } else if (sheetName.equals("CreateProductFailure")) {
                response.statusCode((int) Double.parseDouble(data.get("status")))
                        .body("error", equalTo(data.get("error")));
            }
        }
    }

    @Given("I run get product by id tests from {string}")
    public void i_run_get_product_by_id_tests(String sheetName) {
        List<Map<String, String>> testDataList = ExcelUtils.getTestData(sheetName);

        for (Map<String, String> data : testDataList) {
            int id = (int) Double.parseDouble(data.get("id"));
            response = given().contentType(ContentType.JSON)
                    .when().get(baseUrl + "/" + id)
                    .then().log().all();

            response.statusCode((int) Double.parseDouble(data.get("status")));

            if (response.extract().statusCode() == 200) {
                response.body("name", equalTo(data.get("name")))
                        .body("price", hasToString(data.get("price")))
                        .body("category", equalTo(data.get("category")))
                        .body("description", equalTo(data.get("description")));
            } else {
                response.body("error", equalTo(data.get("error")));
            }
        }
    }

    @Given("I run delete product tests from {string}")
    public void i_run_delete_product_tests(String sheetName) {
        List<Map<String, String>> testDataList = ExcelUtils.getTestData(sheetName);

        for (Map<String, String> data : testDataList) {
            int id = (int) Double.parseDouble(data.get("id"));
            response = given().contentType(ContentType.JSON)
                    .when().delete(baseUrl + "/" + id)
                    .then().log().all();

            int expectedStatus = (int) Double.parseDouble(data.get("status"));
            response.statusCode(expectedStatus);

            if (expectedStatus == 200) {
                response.body("message", equalTo(data.get("message")));
            } else {
                response.body("error", equalTo(data.get("error")));
            }
        }
    }
}
