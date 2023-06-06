package assignment2.no.authentication;

import assignment2.BaseNoAuthenticationTest;
import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateUser extends BaseNoAuthenticationTest {
    @Test
    public void createUserShouldSucceed(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("age", 30);
        jsonObject.put("image", LoremIpsum.getInstance().getUrl());

        given()
                .log().uri()
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .when()
                .post("/user/create")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .body("status", equalTo(200));
    }
}
