package com.demo.testScripts;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demo.applLib.Common;
import com.demo.objects.CommonObjects;
import com.demo.utility.ExcelManager;
import com.demo.utility.WebControls;
import com.demo.utility.WebDriverHelper;
@Listeners(com.demo.utility.ListenerClass.class)
public class PageLoadTime {
	
	@Test
	public void pageLoadTest()
	{
		
		
		//WebDriverHelper.launchSession();
		List<Map<String, String>> excelData=ExcelManager.getExcelRowsdata("SOWAttachement.xlsx","SOWAttachement_Performance","Execute=y");
		
		
		//System.out.println(reqid);
		for(int i=0;i<excelData.size();i++)
		{
			WebControls.setValueOnWebEdit(CommonObjects.editUsername, excelData.get(i).get("User"), "Username");
			WebControls.setValueOnWebEdit(CommonObjects.editPassword,"Ro$ewar1461", "Password");
			WebControls.clickOnWebButton(CommonObjects.btnSignIn,"Sign button");
			if(WebDriverHelper.isElementPresent(CommonObjects.continuePopup))
			{
				WebControls.clickOnWebButton(CommonObjects.continuePopup, "Popup");
			}
			String str = excelData.get(i).get("Status");
			double d=Double.parseDouble(excelData.get(i).get("Req ID"));
			DecimalFormat df = new DecimalFormat("#");
			String reqid=df.format(d);
			Common.quickSearch("Requisition ID",reqid);
			Common.createChromeSession();
			WebControls.clickOnWebButton(CommonObjects.btnSowAttachement, "Sow attachement step");
			
			WebControls.clickOnWebButton(CommonObjects.lblUserProfile, "User Profile");
			WebControls.clickOnWebButton(CommonObjects.linkLogout, "Logout");
			
			
		}
		
	}

}
