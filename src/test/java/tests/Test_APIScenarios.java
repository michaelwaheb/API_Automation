package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import utils.JsonLoader;
import utils.Utils;
import java.net.SocketTimeoutException;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class Test_APIScenarios extends BaseTest {
    private static String userId;
    private static Response createUserResponse;

    @Test
    @Story("Create a user")
    @Description("Test Description : Verify the details of user of id")
    public void CreateAUser() {
        try {
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
                            .extract()
                            .response();

            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            Utils.handleResponseError(response, 201, "User Creation failed");

            // Print response for verification
            Utils.printResponse("User created:", response);
            // Extract user ID from the response
            userId = response.path("id");
            createUserResponse = response;

        } catch (Exception e) {
            Utils.handleError(e, "Error occurred while creating user");
        }
    }
    @Test
    @Story("Retrieve a User based on ID")
    @Description("Test Description : Verify the details of user of id-3")
    public void RetrieveAUser() {
        try {
            // Send GET request to retrieve the user details
            Response getResponse =
                    given()
                            .when()
                            .get("/api/users/" + userId)
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();


            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            Utils.handleResponseError(createUserResponse, 200, "User Retrevial failed");

            // Print response for verification
            Utils.printResponse("User Data by id:", getResponse);

            // Validate that the response matches the create user response
            JSONObject createUserJson = new JSONObject(createUserResponse.asString());
            JSONObject retrievedUserJson = new JSONObject(getResponse.asString());
            assert retrievedUserJson.similar(createUserJson) : "Mismatch between created user and retrieved user data";
            System.out.println("The retrieved user details match the created user details.");


        } catch (Exception e) {
            Utils.handleError(e, "Error occurred while Retreving user");
        }
    }


    @Test
    @Story("Update a User based on ID")
    @Description("Test Description : Verify the details of user of id-3")

    public void updateAUser() {
        try {
            // Load user data from JSON file using JsonLoader class
            JsonNode updateduserJson = JsonLoader.loadUserData("src/test/java/Data/UserDataUpdated.json");
            // Send GET request to retrieve the user details
            Response updateResponse =
                    given()
                            .header("Content-Type", "application/json")
                            .body(updateduserJson.toString())
                            .when()
                            .put("/api/users/" + userId)
                            .then()
                            .statusCode(200)
                            .extract()
                            .response();

            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            Utils.handleResponseError(updateResponse, 200, "User Update failed");

            // Print response for verification
            System.out.println("User with ID" + userId + "updated Successfully with following details" + updateResponse.asString());
        }catch (Exception e) {
            Utils.handleError(e, "Error occurred while update user");
        }

    }

}

