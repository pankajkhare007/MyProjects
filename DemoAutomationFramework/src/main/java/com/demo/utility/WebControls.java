/**
 * 
 */
package com.demo.utility;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * @author pkhare
 *
 */
public class WebControls extends WebDriverHelper {
	
	public static WebElement getWebElement(String locator)
	{
		String arr[]=locator.split("\\|");
		String locatorType = arr[0].trim();
		String locatorValue = arr[1].trim();
		WebElement ele = null;
		if(locatorType.equals("xpath"))
		{
			ele = driver.findElement(By.xpath(locatorValue));
		}
		else if(locatorType.equals("id"))
		{
			ele = driver.findElement(By.id(locatorValue));
		}
		else if(locatorType.equals("name"))
		{
			ele = driver.findElement(By.name(locatorValue));
		}
		return ele;
	}
	
	public static By getByElement(String locator)
	{
		String arr[]=locator.split("\\|");
		String locatorType = arr[0].trim();
		String locatorValue = arr[1].trim();
		By by = null;
		if(locatorType.equals("xpath"))
		{
			by = By.xpath(locatorValue);
		}
		else if(locatorType.equals("id"))
		{
			by = By.id(locatorValue);
		}
		else if(locatorType.equals("name"))
		{
			by = By.name(locatorValue);
		}
		return by;
	}
	
	public static void setValueOnWebEdit(String sObject,String sText,String sReportText) 
	{
		WebElement obj = getWebElement(sObject);
		if(obj!=null)
		{
			if(obj.isEnabled())
			{
				obj.clear();
				obj.click();
				obj.sendKeys(sText);
				ExtentManager.stepName("Entered "+sText);
			}
			else
			{
				ExtentManager.stepName("Entered "+sText+" unsuccessfully ");
				ExtentManager.verificationStep(sReportText, "Object is not enabled, "+ "can not insert value "+sReportText);
			}
				
			
		}
		else
		{
			ExtentManager.stepName("Entered "+sText+" unsuccessfully ");
			ExtentManager.verificationStep(sReportText, "Object is not exist, "+ "can not insert value "+sReportText);
		}
		
	}
	
	public static void clickOnWebButton(String sObject,String sReportText) 
	{
		WebElement obj = getWebElement(sObject);
		if(obj!=null)
		{
			if(obj.isEnabled())
			{
				
				obj.click();
				
				ExtentManager.stepName("Clicked on "+sReportText);
			}
			else
			{
				ExtentManager.stepName("Clicked on "+sReportText+" unsuccessfully ");
				ExtentManager.verificationStep(sReportText, "Object is not enabled, "+ "can not insert value "+sReportText);
			}
				
			
		}
		else
		{
			ExtentManager.stepName("Clicked on "+sReportText+" unsuccessfully ");
			ExtentManager.verificationStep(sReportText, "Object is not exist, "+ "can not click on "+sReportText);
		}
		
	}
	public static void setValueOnWebListByText(String sObject,String sText,String sReportText) throws IOException
	{
		WebElement obj = getWebElement(sObject);
		Select sel = new Select(obj);
		
		if(obj!=null)
		{
			if(obj.isEnabled())
			{
				
				sel.selectByVisibleText(sText);
				
				ExtentManager.stepName(sReportText+" selected successfully");
			}
			else
			{
				ExtentManager.stepName("item "+sReportText+" is not selected ");
				ExtentManager.verificationStep(sReportText, "Object is not enabled, "+ "can not be selected "+sReportText);
			}
				
			
		}
		else
		{
			ExtentManager.stepName("item "+sReportText+" is not selected ");
			ExtentManager.verificationStep(sReportText, "Object is not exist, "+ "can not be selected "+sReportText);
		}
		
	}
	
	public static void setValueOnWebListByValue(String sObject,String sText,String sReportText) throws IOException
	{
		WebElement obj = getWebElement(sObject);
		Select sel = new Select(obj);
		
		if(obj!=null)
		{
			if(obj.isEnabled())
			{
				
				sel.selectByValue(sText);
				
				ExtentManager.stepName(sReportText+" selected successfully");
			}
			else
			{
				ExtentManager.stepName("item "+sReportText+" is not selected ");
				ExtentManager.verificationStep(sReportText, "Object is not enabled, "+ "can not be selected "+sReportText);
			}
				
			
		}
		else
		{
			ExtentManager.stepName("item "+sReportText+" is not selected ");
			ExtentManager.verificationStep(sReportText, "Object is not exist, "+ "can not be selected "+sReportText);
		}
		
	}
	
}
