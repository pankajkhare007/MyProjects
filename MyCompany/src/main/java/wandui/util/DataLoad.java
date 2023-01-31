package wandui.util;

import org.junit.Assert;
import properties.RunSettings;
import wandui.dataOne.ExcelSheets;
import wandui.dataOne.Global;

import java.util.List;
import java.util.Map;

import static wandui.dataOne.SQLBuilder.*;
public class DataLoad {
	
	 public static void initialization()
	    {
	        String en= JsonFileManager.getJSONObjectContent("Environment");
	        RunSettings.Browser =JsonFileManager.getJSONObjectContent("Browser");
	        RunSettings.URL =JsonFileManager.getJsonMatchingObjectContent(en, "Environments", "url");
	        RunSettings.dbUrl=JsonFileManager.getJsonMatchingObjectContent(en, "Environments", "dburl");

	        RunSettings.dbUsername=JsonFileManager.getJsonMatchingObjectContent(en, "Environments", "dbusername");
	        RunSettings.dbPassword=JsonFileManager.getJsonMatchingObjectContent(en, "Environments", "dbpassword");
	        RunSettings.driverClassName=JsonFileManager.getJsonMatchingObjectContent(en, "Environments", "driverclassname");
	        RunSettings.TestDataFile ="TestData.xlsx";


	        List<Map<String, String>> excelData=ExcelManager.getExcelRowsdata(RunSettings.TestDataFile, ExcelSheets.MSP.toString(),"sTestCaseId="+ RunSettings.scenarioName);
	        if(excelData.size() < 1)
	            Assert.fail();

	        Global.testData =excelData.get(0);
	        Global.clientName=Global.testData.get("ClientName");
	        Global.clientID=DatabaseManager.getQueryResultByParameters(SELECT_CLIENT_ID_BY_NAME,Global.clientName);

	        Global.userName =  DatabaseManager.getPersonListFromDatabas(SELECT_MSP_WITH_CLIENT_SERVICE_ROLE,USER_TEMPLATE, Global.clientID).get(0).getUserName();
	        Global.password = DatabaseManager.getPersonListFromDatabas(SELECT_MSP_WITH_CLIENT_SERVICE_ROLE,USER_TEMPLATE, Global.clientID).get(0).getPassword();
	    }

}
