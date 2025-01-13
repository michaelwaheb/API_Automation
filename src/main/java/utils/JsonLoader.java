package utils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;


public class JsonLoader
{
    public static JsonNode loadUserData(String filePath) {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode userJson = null;
            try {
                userJson = objectMapper.readTree(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return userJson;
        }
    }


