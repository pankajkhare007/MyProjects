package utilities.web;

import com.aventstack.extentreports.Status;
import org.junit.jupiter.api.*;
import utilities.reports.Reports;
import java.io.IOException;

public class WebListeners {


    static boolean skipCurrentTest = false;
    @BeforeAll
    public void beforeAll(TestInfo testInfo) {

        Reports.startTestReport("Nxt-Gen VMS Automation Test Report");
        int j = 1 + 2;
        System.out.println(testInfo.getDisplayName() + " - Before All");
        WebConfig.loadConfig();
    }

    @BeforeEach
    public void beforeEach(TestInfo testInfo) {


        WebRunSettings.testInfo = testInfo;
        var dName = testInfo.getDisplayName();
        String[] dNames = dName.split(", ");
        for (String name : dNames) {

            if (name.contains("sTestCaseId")) {
                String[] sName = name.split("=");
                if (!sName[1].equals(testInfo.getTestMethod().get().getName())) {
                    skipCurrentTest = true;
                    return;
                }
                break;

            }
        }
        skipCurrentTest = false;
        Reports.StartTest(testInfo.getTestMethod().get().getName());


        WebDriverLib.LaunchBrowser(WebRunSettings.Browser,WebRunSettings.url,WebRunSettings.newSession);
        //WebDriverLib.driver.get("https://magnitglobal.com");



    }

    @AfterEach
    public void afterEach(TestInfo testInfo) {

        if(skipCurrentTest) return;



        if (WebRunSettings.builder != null) {

            // kill the process
            WebRunSettings.process.destroyForcibly();
            System.out.println("Process destroyed");
        }


        Reports.Verification("Pass", "Failed due to unhandled error");


    }

    @AfterAll
    public void afterAll() {
        Reports.flushProcess();
        Reports.moveAllureResult();

    }

    private static boolean condition() {
        var props = System.getProperties();
        String cond = props.getProperty("Execute");
        if (cond.equalsIgnoreCase("y"))
            return true;
        else
            return false;
    }

}
