package assignment2;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class BaseNoAuthenticationTest {
    @BeforeMethod
    public void setUp(){
        RestAssured.baseURI = "https://apingweb.com";
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
