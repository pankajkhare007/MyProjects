package appLibrary;

import io.qameta.allure.Step;
import objectRepository.CommonObjects;
import objectRepository.RequestObjects;
import utilities.reports.Reports;
import utilities.web.WebController;
import utilities.web.WebRunSettings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class CommonLib {
    @Step("Login  with username and  password")
    public static void login()
    {
        Reports.testStep(new Object(){}.getClass().getEnclosingMethod().getName() + " method started");
        WebController.inputText(CommonObjects.txtUserName, WebRunSettings.loginUsername,"User Name");
        WebController.inputText(CommonObjects.txtPassword, WebRunSettings.loginPassword,"Password");
        WebController.clickOnObject(CommonObjects.btnLoginButton,"Login button");
        WebController.waitforElement(CommonObjects.linkProfile,"Profile link");
        Reports.screenshot("Login successfully");
        Reports.Verification("Pass","Login successfully");
    }
    public static void handlePageLoad(int timeInMilliSeconds)
    {
        try {
            Thread.sleep(timeInMilliSeconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Click on Exit Configuration")
    public static void exitConfiguration()  {
        Reports.testStep(new Object(){}.getClass().getEnclosingMethod().getName() + " method started");
        if(WebController.createObject(CommonObjects.linkConfiguration)==null) {
            WebController.waitforElement(CommonObjects.linkExitConfiguration, "Exit Configuration Link");
            WebController.clickOnObject(CommonObjects.linkExitConfiguration, "Exit Configuration");
            WebController.waitforElement(RequestObjects.txtManagerInput, "Manager Input Filed");
        }
        // Reports.TestStep("We are already on Home Page");
    }

    public static String randomName()
    {
        String RandomName ;
        Random random = new Random();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("DDMMYYYYHHmmss");
        LocalDateTime now = LocalDateTime.now();
        return  "AutoName" + (dtf.format(now));

    }
    public static String setDate(String dateformat, int months , String operation) {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateformat);
        LocalDate Date1 ;
        LocalDate newDate;
        if(operation.equals("add"))
        {
            Date1 = LocalDate.now();
            newDate = Date1.plusMonths(months);
            return  dtf.format(newDate);
        }
        else if (operation.equals("minus"))
        {
            Date1 = LocalDate.now();
            newDate = Date1.minusMonths(months);
            return  dtf.format(newDate);
        }

        return  "Enter valid variable";

    }
    @Step("Set Date")
    public static void setDateText(String elementProperties, String dateformat, int months , String operation)
    {
        Reports.testStep(new Object(){}.getClass().getEnclosingMethod().getName() + " method started");
        String Date = CommonLib.setDate(dateformat,months,operation);
        WebController.inputText(elementProperties,Date,"Date has been set");

    }

    @Step("Logout")
    public static void logOut() {
        Reports.testStep(new Object(){}.getClass().getEnclosingMethod().getName() + " method started");
        WebController.waitforElement(CommonObjects.linkProfile , "Profile Name Link");
        WebController.clickOnObject(CommonObjects.linkProfile,"Profile Name Link");
        WebController.waitforElement(CommonObjects.linkLogout,"Logout Link Appearing");
        WebController.clickOnObject(CommonObjects.linkLogout,"Logout Link");
        WebController.waitforElement(CommonObjects.txtUserName,"Log Out Seccessfully");

    }
}
