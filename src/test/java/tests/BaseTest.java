package tests;

import config.ConfigLoader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseTest
{
    @BeforeClass
    public void setup() {
        // Set the base URL
        RestAssured.baseURI = ConfigLoader.getBaseUrl();
    }
}
