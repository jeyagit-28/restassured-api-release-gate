package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ObjectApiTest extends BaseTest {

    @Test
    public void createAndFetchObject() {
        String requestBody = """
        {
          "name": "Interview Demo Object",
          "data": {
            "year": 2024,
            "price": 1000
          }
        }
        """;

        // 1. Create Object and extract ID as String
        String id =
        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/objects")
        .then()
            .statusCode(200) // Positive check for creation
            .body("name", equalTo("Interview Demo Object"))
            .extract().path("id");

        // 2. Fetch Object using the String ID
        given()
        .when()
            .get("/objects/" + id)
        .then()
            .statusCode(200) // Positive check for retrieval
            .body("id", equalTo(id))
            .body("name", equalTo("Interview Demo Object"));
    }
}
