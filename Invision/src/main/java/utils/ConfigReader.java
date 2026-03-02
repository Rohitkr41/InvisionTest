//package utils;
//
//import java.io.FileInputStream;
//import java.util.Properties;
//
//public class ConfigReader {
//
//    static Properties prop;
//
//    static {
//        try {
//            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
//            prop = new Properties();
//            prop.load(fis);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String getProperty(String key) {
//        return prop.getProperty(key);
//    }
//}

package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config.properties"
            );
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}