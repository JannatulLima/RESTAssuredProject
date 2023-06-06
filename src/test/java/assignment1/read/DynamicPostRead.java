package assignment1.read;

import assignment1.BasePublicAPITest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class DynamicPostRead extends BasePublicAPITest {
    @Test
    public void getPostShouldSucceed() {
        int postID = given()
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        given()
                .log().uri()
                .when()
                .get("/posts/{postID}", postID)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(postID));
    }
}
