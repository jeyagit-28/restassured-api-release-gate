package com.api.tests;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class NegativeApiTest extends BaseTest {

    @Test
    public void testObjectNotFound() {
        // Simple negative check: searching for an ID that doesn't exist
        String nonExistentId = "999999999";
        
        given()
        .when()
            .get("/objects/" + nonExistentId)
        .then()
            .statusCode(404); // Expecting Not Found
    }
}
