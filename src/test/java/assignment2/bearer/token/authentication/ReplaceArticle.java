package assignment2.bearer.token.authentication;

import assignment2.BaseBearerTokenAuthenticationTest;
import com.thedeanda.lorem.LoremIpsum;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class ReplaceArticle extends BaseBearerTokenAuthenticationTest {
    @Test
    public void replaceArticleShouldSucceed(){
        int articleID = given()
                .spec(requestSpecification())
                .when()
                .get("/articles")
                .then()
                .extract().jsonPath().getInt("result[263].id");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(1));
        jsonObject.put("body", LoremIpsum.getInstance().getParagraphs(1,1));
        jsonObject.put("picture", LoremIpsum.getInstance().getUrl());

        given()
                .spec(requestSpecification())
                .log().uri()
                .body(jsonObject)
                .when()
                .put("/article/edit/{articleID}", articleID)
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("message", equalTo("Article has been updated"))
                .body("status", equalTo(200));
    }
}
