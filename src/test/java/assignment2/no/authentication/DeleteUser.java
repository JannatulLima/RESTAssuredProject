package assignment2.no.authentication;

import assignment2.BaseNoAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteUser extends BaseNoAuthenticationTest {
    @Test
    public void deleteUserShouldSucceed(){
        int userID = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("data[0].user_id");

        given()
                .log().uri()
                .when()
                .delete("/user/delete/{userID}", userID)
                .then()
                .statusCode(200)
                .log().body();
    }
}
