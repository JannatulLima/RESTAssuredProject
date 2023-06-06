package assignment1.write;

import assignment1.BasePublicAPITest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class DynamicPostCreate extends BasePublicAPITest {
    @Test
    public void createPost(){
        String postTitle = LoremIpsum.getInstance().getWords(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", postTitle );
        jsonObject.put("author", author);

        given()
                .log().uri()
                .header("Content-Type", "application/json")
                .body(jsonObject)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("id", notNullValue());
    }
}
