package com.demo.testScripts;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import com.demo.applLib.Common;
import com.demo.objects.CommonObjects;
import com.demo.utility.ExtentManager;
import com.demo.utility.ListenerClass;
import com.demo.utility.WebControls;
@Listeners(com.demo.utility.ListenerClass.class)
public class LoginPageTest {
	
	@Test
	public void loginTest() throws SQLException, IOException
	{
		Common.validateLogo();
		Common.login();
		Common.verifyUsername();
	}

}
