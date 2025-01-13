package utils;
import io.restassured.response.Response;

public class Utils {
    public static void printResponse(Response response)
    {
            System.out.println(response.asString());
        }
    }


