package assignment2.no.authentication;

import assignment2.BaseNoAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetUserDetails extends BaseNoAuthenticationTest {
    @Test
    public void getUserDetailsShouldSucceed(){
        int userID = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("data[0].user_id");

        given()
                .log().uri()
                .when()
                .get("/user/{userID}", userID)
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .body("status", equalTo(200));
    }
}
