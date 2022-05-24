package com.demo.applLib;

import java.io.IOException;
import java.sql.SQLException;

import com.demo.data.Global;
import com.demo.objects.CommonObjects;
import com.demo.objects.CreateAccountObjects;
import com.demo.utility.ExtentManager;
import com.demo.utility.WebControls;
import com.demo.utility.WebDriverHelper;

public class CreateAccount extends WebDriverHelper
{
	public static void validateCreateAccountSection() throws IOException
	{
		if(WebControls.getWebElement(CommonObjects.lblCreateAccount).isDisplayed())
		{
			ExtentManager.verificationStep("pass", "Create Account section is available ");
		}
		else
		{
			ExtentManager.verificationStep("fail", "Create Account section is not available ");
		}
	}
	
	public static void createNewAccount() throws IOException, SQLException
	{
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		ExtentManager.stepName("method "+name+" started");
		WebControls.clickOnWebButton(CommonObjects.linkSignIn,"Sign link");
		waitForElement(CreateAccountObjects.editEmailAddress, 20);
		WebControls.setValueOnWebEdit(CreateAccountObjects.editEmailAddress, Global.rs.getString("Email"), "Email Address");
		CreateAccount.validateCreateAccountSection();
		WebControls.clickOnWebButton(CreateAccountObjects.btnCreateAccount,"Sign link");
		waitForElement(CreateAccountObjects.editAliasAddress, 20);
		clickOnGenderRdoBtn(Global.rs.getString("Gender"));
		WebControls.setValueOnWebEdit(CreateAccountObjects.editCustFirstName, Global.rs.getString("FirstName"), "Custmer First Name");
		WebControls.setValueOnWebEdit(CreateAccountObjects.editCustLastName, Global.rs.getString("LastName"), "Custmer Last Name");
		WebControls.setValueOnWebEdit(CreateAccountObjects.editPassword, Global.rs.getString("SetPassword"), "Password");
		WebControls.setValueOnWebListByValue(CreateAccountObjects.ddlDay, Global.rs.getString("Day"), "Day");
		WebControls.setValueOnWebListByValue(CreateAccountObjects.ddlMonth, Global.rs.getString("Month"), "Month");
		WebControls.setValueOnWebListByValue(CreateAccountObjects.ddlYear, Global.rs.getString("Year"), "Year");
		WebControls.setValueOnWebEdit(CreateAccountObjects.editAddress, Global.rs.getString("Address"), "Address");
		WebControls.setValueOnWebEdit(CreateAccountObjects.editCity, Global.rs.getString("City"), "City");
		WebControls.setValueOnWebListByText(CreateAccountObjects.ddlState, Global.rs.getString("State"), "State");
		WebControls.setValueOnWebEdit(CreateAccountObjects.editPostalCode, Global.rs.getString("ZipCode"), "Zip Code");
		WebControls.setValueOnWebEdit(CreateAccountObjects.editMobilePhone, Global.rs.getString("MobileNumber"), "Mobile Number");
		WebControls.setValueOnWebEdit(CreateAccountObjects.editAliasAddress, Global.rs.getString("Allias Address"), "Future Refrence");
		WebControls.clickOnWebButton(CreateAccountObjects.btnRegister,"Register");
		waitForElement(CreateAccountObjects.lblAccountCreateMessage, 20);
	}
	
	public static void clickOnGenderRdoBtn(String labelName) throws IOException
	{
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		ExtentManager.stepName("method "+name+" started");
		if(labelName.trim().toLowerCase().equals("Mr.".toLowerCase()))
		{
			WebControls.clickOnWebButton(CreateAccountObjects.rdoBtnGenderMr,"Mr");
		}
		else if(labelName.trim().toLowerCase().equals("Mrs.".toLowerCase()))
		{
			WebControls.clickOnWebButton(CreateAccountObjects.rdoBtnGenderMrs,"Mrs");
		}
			
	}

}
