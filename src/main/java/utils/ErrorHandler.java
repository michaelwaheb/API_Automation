package utils;

import io.restassured.response.Response;

public class ErrorHandler {

    // Functional interface for API call
    @FunctionalInterface
    public interface ApiCall {
        Response call() throws Exception; // Abstract method
    }

    // Method to execute the API call and validate the response
    public static Response executeWithValidation(ApiCall apiCall, int expectedStatusCode, String errorMessage) {
        Response response = null;

        try {
            // Execute the API call
            response = apiCall.call();

            // Validate the status code
            if (response.statusCode() != expectedStatusCode) {
                System.err.println(errorMessage + " - Expected status: " + expectedStatusCode + ", but got: " + response.statusCode());
                return null;
            }

            // Additional response validation (if needed)
            if (response.getBody().asString().isEmpty()) {
                System.err.println(errorMessage + " - Response body is empty.");
                return null;
            }

        } catch (Exception e) {
            System.err.println(errorMessage + " - Exception: " + e.getMessage());
        }

        return response;
    }
}

