package assignment2.basic.authentication;

import assignment2.BaseBasicAuthenticationTest;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ReplaceUser extends BaseBasicAuthenticationTest {
    @Test
    public void replaceUserShouldSucceed(){
        int userID = given()
                .auth().basic("admin", "12345")
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("data[0].user_id");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("age", 30);
        jsonObject.put("image", LoremIpsum.getInstance().getUrl());

        given()
                .auth().basic("admin", "12345")
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .log().uri()
                .when()
                .put("/user/edit/{userID}", userID)
                .then()
                .log().body();
    }
}
