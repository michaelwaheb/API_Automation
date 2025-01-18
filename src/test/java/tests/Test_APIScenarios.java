package tests;

import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import utils.ErrorHandler;
import utils.JsonLoader;
import utils.Utils;
import java.net.SocketTimeoutException;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class Test_APIScenarios extends BaseTest {
    private  String userId;
    private  Response createUserResponse;

    @Test
    @Story("Create a user")
    public void CreateAUser() {
        // Load user data from JSON file using JsonLoader class
        JsonNode userJson = JsonLoader.loadUserData("src/test/java/Data/UserData.json");

        // Send POST request using ErrorHandler to execute and validate
        Response response = ErrorHandler.executeWithValidation(() ->
                        given()
                                .header("Content-Type", "application/json")
                                .body(userJson.toString())
                                .when()
                                .post("/api/users")
                                .then()
                                .statusCode(200)
                                .extract()
                                .response(),
                200, "Error during user creation");

        // Check if response is valid
        if (response != null) {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            // Print response for verification
            Utils.printResponse("User created:", response);
            // Extract user ID from the response
            userId = response.path("id");
            createUserResponse = response;
        } else {
            System.err.println("Error creating user. Please check the logs for details.");
        }
    }

    @Test
    @Story("Retrieve a User based on ID")
    public void RetrieveAUser() {
        // Send GET request to retrieve the user details using ErrorHandler to execute and validate
        Response getResponse = ErrorHandler.executeWithValidation(() ->
                        given()
                                .when()
                                .get("/api/users/" + userId)
                                .then()
                                .statusCode(200)
                                .extract()
                                .response(),
                200, "Error during retrieving user");

        // Check if response is valid
        if (getResponse != null) {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            // Print response for verification
            Utils.printResponse("User Data by id:", getResponse);

            // Validate that the response matches the create user response
            JSONObject createUserJson = new JSONObject(createUserResponse.asString());
            JSONObject retrievedUserJson = new JSONObject(getResponse.asString());
            assert retrievedUserJson.similar(createUserJson) : "Mismatch between created user and retrieved user data";
            System.out.println("The retrieved user details match the created user details.");
        } else {
            System.err.println("Error retrieving user data. Please check the logs for details.");
        }
    }



    @Test
    @Story("Update a User based on ID")
    public void updateAUser() {
        // Load user data from JSON file using JsonLoader class
        JsonNode updatedUserJson = JsonLoader.loadUserData("src/test/java/Data/UserDataUpdated.json");

        // Send PUT request to update the user details using ErrorHandler to execute and validate
        Response updateResponse = ErrorHandler.executeWithValidation(() ->
                        given()
                                .header("Content-Type", "application/json")
                                .body(updatedUserJson.toString())
                                .when()
                                .put("/api/users/" + userId)
                                .then()
                                .statusCode(200)
                                .extract()
                                .response(),
                200, "Error during updating user");

        // Check if response is valid
        if (updateResponse != null) {
            RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
            // Print response for verification
            System.out.println("User with ID " + userId + " updated successfully with the following details: " + updateResponse.asString());
        } else {
            System.err.println("Error updating user. Please check the logs for details.");
        }
    }

}

