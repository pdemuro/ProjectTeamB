package com.reply.teambproject.controller;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class CategoryControllerTest {
    @Test
    void getCategories() {

        given()
                .when()
                .get("/categories")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

    }
}