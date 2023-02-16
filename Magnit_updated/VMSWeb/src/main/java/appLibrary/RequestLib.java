package appLibrary;

import io.qameta.allure.Step;
import objectRepository.RequestObjects;
import utilities.reports.Reports;
import utilities.web.WebController;
import utilities.web.WebDriverLib;
import utilities.web.WebRunSettings;

import java.util.Set;

public class RequestLib extends WebDriverLib {
    @Step("select Option For Request")
    public static void selectOptionForRequest()
    {
        Reports.testStep(new Object() {}.getClass().getEnclosingMethod().getName() + " method started");
        WebController.waitforElement(RequestObjects.txtManagerInput, "Manager Input Filed");
        WebController.inputText(String.format(RequestObjects.txtManagerInput, WebRunSettings.dataRow.get("Manager")), WebRunSettings.dataRow.get("Manager"), "Input manager " + WebRunSettings.dataRow.get("Manager"));
        WebController.waitforElement(String.format(RequestObjects.lblFilteredManagerForRequest, WebRunSettings.dataRow.get("Manager")), "Manager Input Filed");
        // WebController.clickOnObject(RequestObjects.lblFilteredManagerForRequest,"Clicked on manager");
        WebController.clickOnObject(String.format(RequestObjects.lblFilteredManagerForRequest, WebRunSettings.dataRow.get("Manager")), "Click On manager");
        WebController.waitforElement(String.format(String.format(RequestObjects.lblReqTypeComboSelection, WebRunSettings.dataRow.get("ReqType"))), "Manager Input Filed");
        WebController.clickOnObject(RequestObjects.txtReqTypeCombo,"Clicked on req type drop down");
        WebController.clickOnObject(String.format(RequestObjects.lblReqTypeComboSelection, WebRunSettings.dataRow.get("ReqType")), "Req type Selected");
        WebController.clickOnObject(RequestObjects.btnGo, "Clicked on Go Button");
    }
    @Step("Request Project Description by selecting category")
    public static void requestProjectDescription()  {
        Reports.testStep(new Object() {}.getClass().getEnclosingMethod().getName() + " method started");
        WebController.waitforElement(RequestObjects.txtRequestProjectTitle, "Wait for Request Project Title");
        CommonLib.handlePageLoad(1000);
        WebController.clickOnObject(RequestObjects.ddlRequestProjectCategory,"Click on ProjCategorydd");
        WebController.waitforElement(String.format(RequestObjects.ddlReqJobCateogryOption,WebRunSettings.dataRow.get("JobCategory")),"Click on Project Category drop down");
        WebController.selectValueFromDropDownByText(RequestObjects.ddlRequestProjectCategory, WebRunSettings.dataRow.get("JobCategory"));
        WebController.inputText(RequestObjects.txtRequestProjectDescription, " Request Description", "Req Description");
        WebRunSettings.requestName = "Request" + CommonLib.randomName();
        WebController.inputText(RequestObjects.txtRequestProjectTitle, WebRunSettings.requestName, "Provided Project Title");
        WebController.inputText(RequestObjects.txtRequestProjectScope, " Request Scope", "Req Scope");
        WebController.inputText(RequestObjects.txtRequestProjectDeliverable, " Request Deliverable", "Req Deliverable");
    }
    @Step("Enter Request Project Details")
    public static void requestProjectDetails(){
        Reports.testStep(new Object() {
        }.getClass().getEnclosingMethod().getName() + " method started");
        CommonLib.setDateText(RequestObjects.txtRequestProjectStartDate, "MM/d/uuuu", 1, "minus");
        CommonLib.setDateText(RequestObjects.txtRequestProjectEndDate, "MM/d/uuuu", 1, "add");
        WebController.selectFromDropDownByIndex(RequestObjects.ddlReqProjReason, 1);
        WebController.inputText(RequestObjects.txtReqProjOtherClientLoc, "1100", "ClientLocation");
        WebController.waitforElement(RequestObjects.lblReqProjOthClientFilteredLoc, "Wait For Loc result");
        WebController.clickOnObject(RequestObjects.lblReqProjOthClientFilteredLoc, "Select Location");
    }
    @Step("Enter Request Project Financial Details")
    public static void reqProjectFinancial() {
        Reports.testStep(new Object() {
        }.getClass().getEnclosingMethod().getName() + " method started");
        String parentwindow = driver.getWindowHandle();
        WebController.clickOnObject(RequestObjects.linkReqProjFinancialDepartmentAddNew, "Add New Link department");
        Set<String> windowcount = driver.getWindowHandles();
        for (String str : windowcount) {
            driver.switchTo().window(str);
            if (driver.getTitle().toLowerCase().equals("PRO Unlimited".toLowerCase())) {
                try {
                    WebController.clickOnObject(RequestObjects.txtReqProjFinancialDepartSearch, "Search Option");
                    WebController.waitforElement(RequestObjects.linkReqProjFinancialDepartSelection, "Wait for select link");
                    WebController.clickOnObject(RequestObjects.linkReqProjFinancialDepartSelection, "Click on Select Option");
                    driver.switchTo().window(parentwindow);
                    break;
                } catch (Exception e) {
                    Reports.Verification("Fail", "Department Selection Failed");
                }
            }
        }
        WebController.waitforElement(RequestObjects.ddlReqProjFinancialDepartCurrency, "Wait for currency");
        WebController.selectValueFromDropDownByText(RequestObjects.ddlReqProjFinancialDepartCurrency, WebRunSettings.dataRow.get("Currency"));
        WebController.selectFromDropDownByIndex(RequestObjects.ddlReqGLCode, 1);
        WebController.inputText(RequestObjects.txtReqProjFinancialDepartmentEstimation, WebRunSettings.dataRow.get("Estimation"), "Estimation Field");
    }
    @Step("Select Supplier from supplier not shown link")
    public static void reqSourcingByRequestSuppliersNotShownOption(){
        Reports.testStep(new Object() {
        }.getClass().getEnclosingMethod().getName() + " method started");
        WebController.setValueForCheckbox(RequestObjects.chkBoxReqProjectSourcingSelectAll, false);
        WebController.clickOnObject(RequestObjects.linkRequestSupplierNotShown, "Click on Shown Link");
        WebController.inputText(RequestObjects.txtReqProjectSupplierNameSearchInput, WebRunSettings.dataRow.get("SupplierName"), "Provide SupplierID");
        WebController.clickOnObject(RequestObjects.btnReqProjectSupplierSearch, "Click on Search");
        WebController.waitforElement(RequestObjects.chkBoxReqProjectSupplierSelection,"Wait for Supplier Checkbox");
        WebController.setValueForCheckbox(RequestObjects.chkBoxReqProjectSupplierSelection, true);
        WebController.clickOnObject(RequestObjects.btnReqProjectSaveSupplierSearch,"Click ON Save Search");
    }

    @Step("Save As Draft Request Project")
    public static void reqProjectSaveAsDraftAction() {
        Reports.testStep(new Object() {
        }.getClass().getEnclosingMethod().getName() + " method started");
        WebController.clickOnObject(RequestObjects.btnReqProjectSaveAsDraft,"Cancel Request for Project");
        WebController.waitforElement(RequestObjects.txtReqProjectConfirm,"Waiting for saved");
        WebController.scrollintoView(RequestObjects.txtReqProjectConfirm);
        Reports.screenshot("Request Project Saved");
    }
    @Step("Fetched Request ID")
    public static String getReqProjectID() {
        Reports.testStep(new Object() {
        }.getClass().getEnclosingMethod().getName() + " method started");
        WebRunSettings.requestID=WebController.createObject(RequestObjects.txtReqProjectConfirm).getText().split("#")[1].toString();
        return WebRunSettings.requestID;
    }

    @Step("Continue to Confirmation Request Project")
    public static void reqProjectContinuetoConfirmation() {
        Reports.testStep(new Object() {
        }.getClass().getEnclosingMethod().getName() + " method started");
        WebController.clickOnObject(RequestObjects.btnReqProejctContniuetoConfirmation,"Continue to Confirmation Request for Project");
        WebController.waitforElement(RequestObjects.lblReqProjectContinueOnConfirmationlandingPage,"Continue on COnfirmation page");
    }
    @Step("Submit Request ID")
    public static void reqSubmitAction() {
        Reports.testStep(new Object() {
        }.getClass().getEnclosingMethod().getName() + " method started");
        WebController.waitforElement(RequestObjects.btnReqProjectSubmit,"Submit Request Project");
        WebController.clickOnObject(RequestObjects.btnReqProjectSubmit,"Submit Request Project");
        WebController.waitforElement(RequestObjects.tabReqProjectDetails,"Request has been submitted");
        if(WebController.createObject(RequestObjects.tabReqProjectDetails)!=null) {
            Reports.Verification("Pass", "Request Project has been submitted");
            Reports.screenshot("Request has been submitted");

        }
        else
        {
            Reports.Verification("Fail","Request Project has been submitted");
            Reports.screenshot("Request has not been submitted");
        }

    }
}
