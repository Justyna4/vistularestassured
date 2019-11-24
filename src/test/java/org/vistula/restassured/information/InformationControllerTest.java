package org.vistula.restassured.information;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.junit.Test;
import org.vistula.restassured.RestAssuredTest;

import java.util.concurrent.ThreadLocalRandom;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class InformationControllerTest extends RestAssuredTest {

    @Test
    public void shouldGetAll() {
        given().get("/information")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(2));
    }

    @Test
    public void shouldCreateNewInfo() {

        JSONObject requestParams = new JSONObject();
        String myName = RandomStringUtils.randomAlphabetic(7);
        String nation = "Polish";
        int mySalary = 100000;

        requestParams.put("id", 6);
        requestParams.put("name", myName);
        requestParams.put("nationality", nation);
        requestParams.put("salary", mySalary);

       given().header("Content-Type", "application/json")
                .body(requestParams.toString())
                .post("/information")
                .then()
                .log().all()
                .statusCode(201)

                .body("name", equalTo(myName))
                .body("nationality", equalTo(nation))
                .body("salary", equalTo(mySalary))
                .body("id", greaterThan(0));
    }

    @Test
    public void shouldDeleteFirstInfo() {
        given().delete("/information/1")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void shouldDeleteTenthInfo() {
        given().delete("/information/10")
                .then()
                .log().all()
                .statusCode(204);
    }


}
