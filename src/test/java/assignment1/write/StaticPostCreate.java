package assignment1.write;

import assignment1.BasePublicAPITest;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class StaticPostCreate extends BasePublicAPITest {
    @Test
    public void createPost(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", "I AM POST");
        jsonObject.put("author", "LIMA");

        given()
                .log().uri()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("title", equalTo("I AM POST"))
                .body("author", equalTo("LIMA"));
    }
}
