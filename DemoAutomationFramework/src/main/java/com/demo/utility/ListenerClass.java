/**
 * 
 */
package com.demo.utility;

import java.io.IOException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * @author pkhare
 *
 */
public class ListenerClass extends WebDriverHelper implements ITestListener, ISuiteListener {
	public static int row;
	public void onStart(ISuite suite) {

		ReadPropertiesFile.loadConfig();
		ExtentManager.setExtent();
	}

	public void onFinish(ISuite suite) {
		ExtentManager.endReport();
		//driver.quit();
	}

	public void onTestStart(ITestResult result) {
		launchSession();
		ExtentManager.startTest(result.getName() + " started");
	}

	public void onTestSuccess(ITestResult result) {
		
		  try { 
			  ExtentManager.verificationStep("pass", result.getMethod().getMethodName()+" got passed"); 
		  } 
		  catch (Exception e) {
			  e.printStackTrace();
		  }
		
		 
		driver.close();
	}

	public void onTestFailure(ITestResult result) {
		 try { 
			  ExtentManager.verificationStep("fail", result.getMethod().getMethodName()+" got failed"); 
		  } 
		  catch (Exception e) {
			  e.printStackTrace();
		  }
		driver.close();
		
	}
}
