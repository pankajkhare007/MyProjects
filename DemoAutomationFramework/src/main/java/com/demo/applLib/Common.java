package com.demo.applLib;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.demo.objects.CommonObjects;
import com.demo.utility.ExtentManager;
import com.demo.utility.MSAccessConnection;
import com.demo.utility.WebControls;
import com.demo.utility.WebDriverHelper;

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
	
}

