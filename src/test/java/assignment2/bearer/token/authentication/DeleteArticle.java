package assignment2.bearer.token.authentication;

import assignment2.BaseBearerTokenAuthenticationTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteArticle extends BaseBearerTokenAuthenticationTest {
    @Test
    public void deleteArticleShouldSucceed(){
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
                .delete("/article/delete/{articleID}", articleID)
                .then()
                .statusCode(200)
                .log().body();
    }
}
