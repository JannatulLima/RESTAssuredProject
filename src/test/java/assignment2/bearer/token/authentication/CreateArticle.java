package assignment2.bearer.token.authentication;

import assignment2.BaseBearerTokenAuthenticationTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateArticle extends BaseBearerTokenAuthenticationTest {
    @Test
    public void createArticleShouldSucceed(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(1));
        jsonObject.put("body", LoremIpsum.getInstance().getParagraphs(1,1));
        jsonObject.put("picture", LoremIpsum.getInstance().getUrl());

       given()
               .spec(requestSpecification())
               .log().uri()
               .body(jsonObject)
               .when()
               .post("/article/create")
               .then()
               .statusCode(200)
               .log().body()
               .body("success", equalTo(true))
               .body("message", equalTo("Article has been created"))
               .body("status", equalTo(200));
    }
}
