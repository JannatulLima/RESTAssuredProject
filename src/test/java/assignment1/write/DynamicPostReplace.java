package assignment1.write;

import assignment1.BasePublicAPITest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class DynamicPostReplace extends BasePublicAPITest {
    @Test
    public void replacePost(){
        int postID = given()
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body()
                .extract().jsonPath().getInt("[0].id");

        String postTitle = LoremIpsum.getInstance().getWords(2);
        String author = LoremIpsum.getInstance().getName();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", postTitle);
        jsonObject.put("author", author);

        given()
                .log().uri()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .when()
                .put("/posts/{postID}", postID)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", notNullValue())
                .body("id", equalTo(postID));
    }
}
