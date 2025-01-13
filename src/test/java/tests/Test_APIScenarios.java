package tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import utils.JsonLoader;
import utils.Utils;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class Test_APIScenarios extends BaseTest
{
    private static String userId;
    @Test
    @Story("Create a user")
    @Description("Test Description : Verify the details of user of id-3")
    public void CreateAUser()
    {
        // Load user data from JSON file using JsonLoader class
        JsonNode userJson = JsonLoader.loadUserData("src/test/java/Data/UserData.json");
// Send POST request
        Response response =
                given()
                        .header("Content-Type", "application/json")
                        .body(userJson.toString())
                        .when()
                        .post("/api/users")
                        .then()
                        .statusCode(201)
                        .log().ifError()
                        .extract()
                        .response();

        // Print response for verification
        System.out.println(response.asString());
        // Print response for verification
        Utils.printResponse(response);
        // Extract user ID from the response
        userId = response.path("id");
        System.out.println("Created User ID: " + userId);

    }

    @Test
    @Story("Retrieve a User based on ID")
    @Description("Test Description : Verify the details of user of id-3")
    public void RetrieveAUser()
    {
        // Send GET request to retrieve the user details
        Response getResponse = given()
                .log().all() // Log all details
                .when()
                .get("/api/users/" + userId)
                .then()
                .statusCode(200)
                .log().ifError() // Log only if there's an error
                .extract()
                .response();
    }
}

