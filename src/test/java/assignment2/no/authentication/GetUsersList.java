package assignment2.no.authentication;

import assignment2.BaseNoAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetUsersList extends BaseNoAuthenticationTest {
    @Test
    public void getUserListShouldSucceed(){
        given()
                .log().uri()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .body("status", equalTo(200));
    }
}
