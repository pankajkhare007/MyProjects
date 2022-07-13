package com.demo.utility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertiesFile {
	public static Properties prop;
	
	public static void loadConfig() throws IOException
	{
		String filePath=System.getProperty("user.dir")+"\\Configuration\\Config.properties";
		FileInputStream bis= new FileInputStream(filePath);
		prop = new Properties();
		prop.load(bis);
	}

}
