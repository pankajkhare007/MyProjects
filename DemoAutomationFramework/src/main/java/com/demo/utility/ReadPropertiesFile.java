package com.demo.utility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	public static Properties prop;
	public static FileInputStream bis;
	public static void loadConfig() 
	{
		String filePath=System.getProperty("user.dir")+"\\Configuration\\Config.properties";
		
		try {
			bis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		prop = new Properties();
		try {
			prop.load(bis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
