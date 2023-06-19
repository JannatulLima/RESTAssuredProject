package assignment2.bearer.token.authentication;

import assignment2.BaseBearerTokenAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetArticles extends BaseBearerTokenAuthenticationTest {
    @Test
    public void getArticlesShouldSucceed(){
        given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("message", equalTo("Success"))
                .body("status", equalTo(200));
    }
}
