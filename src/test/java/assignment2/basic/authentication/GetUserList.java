package assignment2.basic.authentication;

import assignment2.BaseBasicAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetUserList extends BaseBasicAuthenticationTest {
    @Test
    public void getUserListShouldSucceed(){
        given()
                .log().uri()
                .auth().basic("admin", "12345")
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
