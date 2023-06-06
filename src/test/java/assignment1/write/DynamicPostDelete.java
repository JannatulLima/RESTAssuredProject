package assignment1.write;

import assignment1.BasePublicAPITest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicPostDelete extends BasePublicAPITest {
    @Test
    public void deletePost(){
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
                .delete("/posts/{postID}", postID)
                .then()
                .statusCode(200)
                .log().body();
    }
}
