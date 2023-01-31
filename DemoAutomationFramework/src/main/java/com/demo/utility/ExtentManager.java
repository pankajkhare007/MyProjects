/**
 * 
 */
package com.demo.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * @author pkhare
 *
 */
public class ExtentManager extends WebDriverHelper {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest childTest;

	public static void setExtent() {
		String reportPath = getReportPath();
		htmlReporter = new ExtentHtmlReporter(reportPath + "\\DemoReport.html");
		htmlReporter.config().setDocumentTitle("Automation Test Report");
		htmlReporter.config().setReportName("OrageHRM Test Automation Report");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", "OrangeHRM");
		extent.setSystemInfo("Testter", "Pankaj");
		extent.setSystemInfo("OS", "Win10");
		extent.setSystemInfo("Browser", "Firfox");

	}

	public static void endReport() {
		extent.flush();
	}

	public static void startTest(String methodName) {
		test = extent.createTest(methodName);
	}

	public static void testStepName(String testStepName) {
		childTest = test.createNode(testStepName);
	}

	public static void stepName(String stepName) {
		if (childTest == null)
			test.log(Status.INFO, stepName);
		else
			childTest.log(Status.INFO, stepName);

	}

	public static void verificationStep(String result, String stepName) {
		MediaEntityBuilder md = null;
		try {
			md = MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to attach screenshot");
		}

		if (result.toLowerCase().trim().equals("fail")) {
			if (childTest == null) {
				// test.log(Status.INFO, MarkupHelper.createLabel(stepName, ExtentColor.RED));
				test.fail(stepName, md.build());
			} else {
				// childTest.log(Status.FAIL, MarkupHelper.createLabel(stepName,
				// ExtentColor.RED));
				childTest.fail(stepName, md.build());
			}

			/*
			 * try { // test.addScreenCaptureFromPath(getScreenshot()); } catch (IOException
			 * e) { // TODO Auto-generated catch block
			 * System.out.println("Unable to attach screenshot");
			 * 
			 * }
			 */
		}

		else if (result.toLowerCase().trim().equals("pass")) {
			if (childTest == null) {
				// test.log(Status.PASS, stepName);
				test.pass(stepName, md.build());
			} else {
				// childTest.log(Status.FAIL, stepName);
				childTest.pass(stepName, md.build());
			}

		}
	}

	public static String getScreenshot() {
		String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		TakesScreenshot takeScreeshot = (TakesScreenshot) driver;
		File source = takeScreeshot.getScreenshotAs(OutputType.FILE);
		String sDest = System.getProperty("user.dir") + "\\Screenshots\\" + fileName + ".png";
		File dest = new File(sDest);
		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return sDest;
	}

	public static String getReportPath() {

		String reportPath = System.getProperty("user.dir") + "\\Reports\\CustomReport" + randomName();
		File file = new File(reportPath);
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
		}
		return reportPath;
	}

	public static String randomName() {
		Calendar currentDate = Calendar.getInstance(); // gets current date instance.
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMYYYYHHmmss");
		String str = formatter.format(currentDate.getTime());
		return str;
	}

}
