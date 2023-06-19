package assignment2.bearer.token.authentication;

import assignment2.BaseBearerTokenAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetArticleDetails extends BaseBearerTokenAuthenticationTest {
    @Test
    public void getArticleDetails(){
        int articleID = given()
                .spec(requestSpecification())
                .when()
                .get("/articles")
                .then()
                .extract().jsonPath().getInt("result[263].id");

        given()
                .spec(requestSpecification())
                .log().uri()
                .when()
                .get("/article/{articleID}", articleID)
                .then()
                .statusCode(200)
                .log().body()
                .body("success", equalTo(true))
                .body("status", equalTo(200));
    }
}
