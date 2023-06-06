package assignment2.basic.authentication;

import assignment2.BaseBasicAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser extends BaseBasicAuthenticationTest {
    @Test
    public void deleteUserShouldSucceed(){
        int userID = given()
                .auth().basic("admin", "12345")
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("data[0].user_id");

        given()
                .log().uri()
                .auth().basic("admin", "12345")
                .when()
                .delete("/user/delete/{userID}", userID)
                .then()
                .log().body();
    }
}
