package com.api.tests;

import static org.hamcrest.Matchers.equalTo;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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

        int id =
        given()
            .contentType("application/json")
            .body(requestBody)
        .when()
            .post("/objects")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/object-schema.json"))
            .extract().path("id");

        given()
        .when()
            .get("/objects/" + id)
        .then()
            .statusCode(200)
            .body("id", equalTo(id));
    }
}
