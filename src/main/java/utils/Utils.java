package utils;
import io.restassured.response.Response;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Utils {
    public static void printResponse(String Message,Response response)
    {
            System.out.println( Message + response.asString());
        }

    public static void handleError(Exception e, String customMessage) {
        System.err.println(customMessage);
        System.err.println("Error: " + e.getMessage());

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        System.err.println("Stack Trace: " + sw.toString());
    }

    public static void handleResponseError(Response response, int expectedStatusCode, String customMessage) {
        if (response.statusCode() != expectedStatusCode) {
            System.err.println(customMessage);
            System.err.println("Expected Status Code: " + expectedStatusCode);
            System.err.println("Actual Status Code: " + response.statusCode());
            System.err.println("Response Body: " + response.getBody().asString());
            assert false : customMessage;
        }
    }

        public static void handleNetworkFailure (Exception e, String customMessage) {
            System.err.println(customMessage);
            System.err.println("Network failure. Error: " + e.getMessage());

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.err.println("Stack Trace: " + sw.toString());
            assert false : customMessage;
        }


    }




