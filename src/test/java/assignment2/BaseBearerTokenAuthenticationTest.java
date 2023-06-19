package assignment2;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeMethod;

import static io.restassured.RestAssured.given;

public class BaseBearerTokenAuthenticationTest {
    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://apingweb.com";
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public String getBearerToken(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "superman@gmail.com");
        jsonObject.put("password", "123456");

        return given()
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");
    }

    public RequestSpecification requestSpecification (){
        return new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer " + getBearerToken())
                .addHeader("Content-Type", "application/json")
                .build();
    }
}
