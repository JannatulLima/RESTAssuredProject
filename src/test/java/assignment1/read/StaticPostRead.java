package assignment1.read;


import assignment1.BasePublicAPITest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class StaticPostRead extends BasePublicAPITest {
    @Test
    public void getPostShouldSucceed() {
        given()
                .log().uri()
                .when()
                .get("/posts/4")
                .then()
                .statusCode(200)
                .log().body()
                .body("id", notNullValue())
                .body("id", equalTo(4));
    }
}
