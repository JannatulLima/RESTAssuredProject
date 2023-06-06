package assignment1.write;

import assignment1.BasePublicAPITest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class StaticPostDelete extends BasePublicAPITest {
    @Test
    public void deletePost(){
        given()
                .log().uri()
                .when()
                .delete("/posts/7")
                .then()
                .statusCode(200)
                .log().body();
    }
}
