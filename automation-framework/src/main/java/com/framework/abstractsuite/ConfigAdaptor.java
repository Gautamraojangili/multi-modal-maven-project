package com.framework.abstractsuite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;

public class ConfigAdaptor {
    private static Map<String, Object> configData;

   public ConfigAdaptor() {
        loadConfig();
    }

    private static void loadConfig() {
    	//File file = Paths.get("src", "main", "resources", "config.yaml").toFile();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        
        InputStream inputStream = ConfigAdaptor.class.getClassLoader().getResourceAsStream("config.yaml");

        try {
            configData = mapper.readValue(inputStream, Map.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.yaml", e);
        }
    }

    public static String getProperty(String key) {
        return configData.get(key).toString();
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static String getBaseUrl() {
        return getProperty("base_url");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("timeouts.implicit_wait"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("timeouts.explicit_wait"));
    }

    public static String getUsername() {
        return getProperty("credentials.username");
    }

    public static String getPassword() {
        return getProperty("credentials.password");
    }
}
