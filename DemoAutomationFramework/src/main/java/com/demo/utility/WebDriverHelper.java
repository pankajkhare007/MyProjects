package com.demo.utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.demo.objects.CommonObjects;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverHelper {
	
	public static WebDriver driver;
	
	public static void launchSession()
	{
		String browserName=ReadPropertiesFile.prop.getProperty("browser");
		String url=ReadPropertiesFile.prop.getProperty("url");
		
		switch(browserName)
		{
			case  "firefox" :
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				break;
			case  "chrome" :
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				break;
			case  "edge" :
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
				break;
		}
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public static void waitForElement(String sObj, int seconds)
	{
		By by=WebControls.getByElement(sObj);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
	public static void scrollElementIntoView(WebElement ele)
	{
		
		Actions actions = new Actions(driver);
		actions.moveToElement(ele);
		actions.perform();
	}
	
	public static boolean isElementPresent(String sObj)
	{
		boolean isEle=false;
		try
		{
			WebElement elePopup=WebControls.getWebElement(sObj);
			if(elePopup.isDisplayed())
			{
				if(elePopup.isEnabled())
				{
					isEle=true;
				}
			}
		}
		catch(Exception e)
		{
			isEle=false;
		}
		return isEle;
	}

}
