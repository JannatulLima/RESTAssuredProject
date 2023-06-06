package assignment2;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseBasicAuthenticationTest {
    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = "https://apingweb.com";
        RestAssured.basePath = "/api/auth";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
