package assignment2.no.authentication;

import assignment2.BaseNoAuthenticationTest;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ReplaceUser extends BaseNoAuthenticationTest {
    @Test
    public void replaceUserShouldSucceed(){
        int userID = given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("data[0].user_id");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("age", 40);
        jsonObject.put("image", LoremIpsum.getInstance().getUrl());

        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .when()
                .put("user/edit/{userID}", userID)
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .body("status", equalTo(200));
    }
}
