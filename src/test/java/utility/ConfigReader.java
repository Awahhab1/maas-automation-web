package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/config.properties");
            properties = new Properties();
            properties.load(fis);
            logger.info("Loaded config.properties successfully.");
        } catch (IOException e) {
            logger.error("Error loading config.properties: " + e.getMessage(), e);
            throw new RuntimeException("Failed to load config.properties file.");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}
