package utilities.web;

import utilities.dataManagers.ExcelManager;
import utilities.dataManagers.JasonFileReader;

import java.util.HashMap;

public class WebConfig {

    public static void loadConfig() {
        String en= JasonFileReader.getJSONObjectContent("Environment");
        WebRunSettings.Browser= JasonFileReader.getJSONObjectContent("Browser");
        WebRunSettings.newSession= JasonFileReader.getJSONObjectContent("NewSession");
        WebRunSettings.url=JasonFileReader.getJsonMatchingObjectContent(en, "Environments", "url");
        //RunSettings.ConnectionString=JasonFileReader.getJsonMatchingObjectContent(en, "Environments", "dburl");
        WebRunSettings.dbUrl=JasonFileReader.getJsonMatchingObjectContent(en, "Environments", "dburl");

        WebRunSettings.dbUsername=JasonFileReader.getJsonMatchingObjectContent(en, "Environments", "dbusername");
        WebRunSettings.dbPassword=JasonFileReader.getJsonMatchingObjectContent(en, "Environments", "dbpassword");
        WebRunSettings.driverClassName=JasonFileReader.getJsonMatchingObjectContent(en, "Environments", "driverclassname");

        int rowNumber = ExcelManager.getRowNumber(ExcelConstants.testData, ExcelConstants.sheetLogin,"sRole=MSP","sEnvironment="+en);
        HashMap<String,String> cred=ExcelManager.getExcelData(rowNumber);

//		var data=ExcelManager.getExcelRowsdata(ExcelConstants.testData,ExcelConstants.sheetLogin,"sRole=MSP");
//		System.out.println(data.get(1).get("Username"));
        WebRunSettings.loginUsername=cred.get("Username").trim();
        WebRunSettings.loginPassword=cred.get("Password").trim();



    }
}
