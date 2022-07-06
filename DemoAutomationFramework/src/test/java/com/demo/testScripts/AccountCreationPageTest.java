package com.demo.testScripts;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demo.applLib.Common;
import com.demo.applLib.CreateAccount;
import com.demo.data.Global;
import com.demo.objects.CreateAccountObjects;
import com.demo.utility.ExtentManager;
import com.demo.utility.MSAccessConnection;


@Listeners(com.demo.utility.ListenerClass.class)
public class AccountCreationPageTest {
	
	@Test
	public void verifyEmailAccount() throws IOException, SQLException
	{
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		ExtentManager.stepName("method "+name+" started");
		ResultSet res=MSAccessConnection.getRecords("select * from CreateAccount");
		Global.rs=res;
		CreateAccount.createNewAccount();
		String expectedMsg="Welcome to your account. Here you can manage all of your personal information and orders.";
		Common.verifySuccessMessage(CreateAccountObjects.lblAccountCreateMessage, expectedMsg);
	
	}
	
	public void loginTest() throws SQLException, IOException
	{
		Common.validateLogo();
		Common.login();
		Common.verifyUsername();
	}
	
	
	

}
