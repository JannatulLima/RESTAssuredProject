package assignment2.basic.authentication;

import assignment2.BaseBasicAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetUserDetails extends BaseBasicAuthenticationTest {
    @Test
    public void getUserDetailsShouldSucceed(){
        int userID = given()
                .auth().basic("admin", "12345")
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("data[0].user_id");

        given()
                .auth().basic("admin", "12345")
                .log().uri()
                .when()
                .get("/user/{userID}", userID)
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("status", equalTo(200));
    }
}
