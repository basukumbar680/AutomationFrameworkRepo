package com.demoapp.ecom.generic.fileutility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {
	public String getDataFromPropertiesFile(String key) {
		
		//Path to your properties file
        String filePath="./configAppData/demoApp.properties";

        //Create a Properties object
        Properties p=new Properties();

        //Using try-catch to handle exceptions while reading the file
        try {
             FileInputStream fis=new FileInputStream(filePath);

             //Load properties from the file
             p.load(fis);

        } catch (IOException e) {
            //Handle any exceptions that may occur during file reading or loading
            System.err.println("Error reading the property file: " + e.getMessage());
            e.printStackTrace();
        }

        //Access properties by key
        String data = p.getProperty(key);

		return data;
	}
}
