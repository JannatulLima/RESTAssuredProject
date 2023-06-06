package assignment1.write;

import assignment1.BasePublicAPITest;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class StaticPostUpdate extends BasePublicAPITest {
    @Test
    public void updatePost(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "I AM UPDATED POST");

        given()
                .log().uri()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .when()
                .patch("/posts/7")
                .then()
                .statusCode(200)
                .log().body()
                .body("id", notNullValue())
                .body("id", equalTo(7))
                .body("title", equalTo("I AM UPDATED POST"));
    }
}
