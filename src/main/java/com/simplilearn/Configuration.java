package com.simplilearn;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {

    final static String PROJECT_PROPERTY_FILE = "project.properties";

    static Properties properties = null;

    static void loadPropertiesIfNotLoadedAlready(){

        if(properties == null){
            properties = new Properties();
            String propFilePath = System.getProperty("user.dir") + "/src/test/resources/" +  PROJECT_PROPERTY_FILE;

            try {
                InputStream inputStream = new FileInputStream(propFilePath);
                properties.load(inputStream);
                System.out.println(properties.getProperty("BASEURI"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getProperty(String name){
        loadPropertiesIfNotLoadedAlready();
        return properties.getProperty(name);
    }
}
