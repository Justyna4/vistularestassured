package org.vistula.restassured.information;

import org.json.JSONObject;
import org.junit.Test;
import org.vistula.restassured.RestAssuredTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class InformationControllerHomeworkTest extends RestAssuredTest {
    @Test
    public void shouldGetAll() {
        given().get("/information")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", is(5));
    }

    //metoda put
    @Test
    public void shouldUpdateInfo() {

        JSONObject update = new JSONObject();
        String myName = "Justyna Erbel";
        String nation = "German";
        int mySalary = 600000;

        update.put("id", 2);
        update.put("name", myName);
        update.put("nationality", nation);
        update.put("salary", mySalary);

        given().header("Content-Type", "application/json")
                .body(update.toString())
                .post("/information")
                .then()
                .log().all()
                .statusCode(201)

                .body("name", equalTo(myName))
                .body("nationality", equalTo(nation))
                .body("salary", equalTo(mySalary))
                .body("id", greaterThan(0));
    }

    //metoda patch
    @Test
    public void shouldUpdateNationInfo() {

        JSONObject update = new JSONObject();
        String nation = "Italian";

        update.put("id", 4);
        update.put("nationality", nation);

        given().header("Content-Type", "application/json")
                .body(update.toString())
                .post("/information")
                .then()
                .log().all()
                .statusCode(204)

                .body("nationality", equalTo(nation))
                .body("id", greaterThan(0));
    }


}
