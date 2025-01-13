package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader
{
    private static Properties properties = new Properties();

    static {
        try {
            String env = System.getProperty("env", "Staging");
            properties.load(new FileInputStream("src/main/java/config/" + env + ".properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }
}

