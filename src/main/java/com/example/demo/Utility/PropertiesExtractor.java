package com.example.demo.Utility;

import com.example.demo.runner.ConsoleRunner;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesExtractor {

    private final static Logger LOGGER = Logger.getLogger(ConsoleRunner.class.getName());
    private static final Properties properties;

    static {
        properties = new Properties();
        URL url = PropertiesExtractor.class.getClassLoader().getResource("application.properties");
        try {
            properties.load(new FileInputStream(url.getPath()));
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.toString());
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}