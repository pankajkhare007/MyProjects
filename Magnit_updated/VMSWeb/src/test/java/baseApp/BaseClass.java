package baseApp;


import appLibrary.CommonLib;
import appLibrary.RequestLib;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import utilities.dataManagers.ExcelManager;
import utilities.reports.Reports;
import utilities.web.WebListeners;
import utilities.web.WebRunSettings;

import java.io.IOException;
import java.util.*;
@TestInstance(org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS)

public class BaseClass extends WebListeners {
    public static List<Map<String, String>> TestMethodData;

    @ParameterizedTest
    @MethodSource({"getTestData"})
    void LoginasMspAdmin(Map<String, String> dataRow) throws IOException {


        if (!dataRow.get("sTestCaseId").equalsIgnoreCase(WebRunSettings.testInfo.getTestMethod().get().getName())) {

            return;
        }

        CommonLib.login();
        CommonLib.exitConfiguration();
        WebRunSettings.dataRow=dataRow;
        RequestLib.selectOptionForRequest();
        RequestLib.requestProjectDescription();
        RequestLib.requestProjectDetails();
        RequestLib.reqProjectFinancial();
        CommonLib.handlePageLoad(5000);
        RequestLib.reqSourcingByRequestSuppliersNotShownOption();
        Reports.startTestStep("Save and submit project");
        RequestLib.reqProjectSaveAsDraftAction();
        RequestLib.getReqProjectID();
        RequestLib.reqProjectContinuetoConfirmation();
        RequestLib.reqSubmitAction();
        Reports.startTestStep("Logout");
        CommonLib.logOut();

    }



    public Iterator<Object[]> getTestData() {


        //TestMethodData = ExcelManager.getExcelRowsdata("TestData.xlsx", "MSP", "sTestCaseId=" + "LoginasMspAdmin");
        TestMethodData = ExcelManager.getExcelRowsdata("TestData.xlsx", "MSP", "Execute=Y");
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (Map<String, String> map : TestMethodData) {
            dp.add(new Object[]{map});
        }
        if (TestMethodData.size() == 0) {
            System.out.println("No Such Case Found");
        }
        return dp.iterator();
    }


    public Iterator<Object[]> getTestDataRows() {


        //TestMethodData = ExcelManager.getExcelRowsdata("TestData.xlsx", "MSP", "sTestCaseId=" + "LoginasMspAdmin");
        var tNum = ExcelManager.getRowIndex("TestData.xlsx", "MSP", "Execute=Y");
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for (Map<String, String> map : TestMethodData) {
            dp.add(new Object[]{map});
        }
        if (TestMethodData.size() == 0) {
            System.out.println("No Such Case Found");
        }
        return dp.iterator();
    }

}
