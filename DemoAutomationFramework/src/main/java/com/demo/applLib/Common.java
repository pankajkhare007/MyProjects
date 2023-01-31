package com.demo.applLib;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v101.network.Network;

import com.demo.objects.CommonObjects;
import com.demo.utility.ExtentManager;
import com.demo.utility.MSAccessConnection;
import com.demo.utility.WebControls;
import com.demo.utility.WebDriverHelper;
import com.google.common.collect.ImmutableMap;

public class Common extends WebDriverHelper
{
	
	public static void login() throws SQLException, IOException
	{
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		ExtentManager.stepName("method "+name+" started");
		ResultSet res=MSAccessConnection.getRecords("select * from Login");
		WebControls.clickOnWebButton(CommonObjects.linkSignIn,"Sign link");
		waitForElement(CommonObjects.editUsername, 20);
		WebControls.setValueOnWebEdit(CommonObjects.editUsername, res.getString("username"), "Username");
		WebControls.setValueOnWebEdit(CommonObjects.editPassword, res.getString("password"), "Password");
		WebControls.clickOnWebButton(CommonObjects.btnSignIn,"Sign button");
	}
	
	public static void validateLogo() throws IOException
	{
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		ExtentManager.stepName("method "+name+" started");
		if(WebControls.getWebElement(CommonObjects.imgLogo).isDisplayed())
		{
			ExtentManager.verificationStep("pass", "Verify Image logo");
		}
		else
		{
			ExtentManager.verificationStep("fail", "no image found");
		}
	}
	
	public static void verifyUsername() throws IOException
	{
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		ExtentManager.stepName("method "+name+" started");
		String loggedUsername=WebControls.getWebElement(CommonObjects.lblUsername).getText();
		if(loggedUsername.equals("admin xyz"))
		{
			ExtentManager.verificationStep("pass", "logged in as  username : "+loggedUsername);
		}
		else
		{
			ExtentManager.verificationStep("fail", "did not login with username : "+loggedUsername);
		}
	}
	
	public static void verifySuccessMessage(String sObj,String sMsg) throws IOException
	{
		String actualMsg=WebControls.getWebElement(sObj).getText();
		if(actualMsg.trim().contains(sMsg))
		{
			ExtentManager.verificationStep("pass", "Account created successfully");
		}
		else
		{
			ExtentManager.verificationStep("fail", "Account is not created");
		}
	}
	
	public static void quickSearch(String sQuickSearchOption,String sQuickSearchText)
	{
		
		waitForElement("xpath|//*[@class='icon-btn-search-blue zc-tooltip']", 10);
		WebControls.clickOnWebButton("xpath|//*[@class='icon-btn-search-blue zc-tooltip']", "Search");
		WebControls.clickOnWebButton("xpath|//*[@class='btn dropdown-toggle btn-style']", "QuickSearch Dropdown");
		WebElement litItem=driver.findElement(By.xpath("//*[@id='SearchDDUlAspx']//li[@class='SearchByLi']//a[text()='" + sQuickSearchOption + "']"));
		scrollElementIntoView(litItem);
	
		driver.findElement(By.xpath("//*[@id='SearchDDUlAspx']//li[@class='SearchByLi']//a[text()='" + sQuickSearchOption + "']")).click();
		WebControls.setValueOnWebEdit(CommonObjects.editQuickSearch, sQuickSearchText, "Quick search");
		WebControls.clickOnWebButton(CommonObjects.btnQuickSearch, "Quick search button");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void createChromeSession()
	{
		 DevTools devTools=((ChromeDriver) driver).getDevTools();
		 devTools.createSession();
		 getNetworkResponse( devTools);
	}
	
	 public static void getNetworkResponse(DevTools devTools)
	 {
		 
		 devTools.send(new Command<>("Network.enable", ImmutableMap.of()));
		 devTools.addListener(Network.responseReceived(), l -> {
	            System.out.print("Response URL: ");
	            System.out.println(l.getResponse().getUrl());
	            System.out.println("#######################################");
	           // System.out.println(l.getResponse().getTiming().get());
	            System.out.print(" --- " +l.getResponse().getResponseTime().get().toJson());
	        });
	        devTools.addListener(Network.requestWillBeSent(), l -> {
	            System.out.println("Request URL: ");
	            System.out.println(l.getRequest().getUrl());
	           
	        });

	      
	 }
	 
	 public static void enableNetworkOffline(DevTools devTools)
	 {
		 
	 }
	
}

