package wandui.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {
	
	 public static WebDriver driver;

	    public static void launchBrowser(String browser, String url)
	    {
	        switch(browser)
	        {
	            case "CH" :
	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver();
	                break;

	            case "FF":
	                WebDriverManager.firefoxdriver().setup();
	                driver = new FirefoxDriver();
	                break;
	            case "EDGE":
	                WebDriverManager.edgedriver().setup();
	                driver = new EdgeDriver();
	                break;
	            case "IE":
	                WebDriverManager.iedriver().setup();
	                driver = new InternetExplorerDriver();
	                break;
	            case "CHI":
	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver();
	                break;
	            default :
	                WebDriverManager.chromedriver().setup();
	                driver = new ChromeDriver();
	                break;

	        }
	        driver.manage().window().maximize();
	        driver.get(url);
	    }

}
