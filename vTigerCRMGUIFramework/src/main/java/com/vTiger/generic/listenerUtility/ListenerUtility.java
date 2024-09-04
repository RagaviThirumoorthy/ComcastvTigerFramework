package com.vTiger.generic.listenerUtility;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vTiger.generic.BaseTest.BaseClass;
import com.vTiger.generic.webdriverUtility.JavaUtility;

public class ListenerUtility implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Report configuration", true);
		// Spark Report Config
		String time = new Date().toString().replace(' ', '_').replace(':', '_');
		spark = new ExtentSparkReporter("./Reports/CRMreport"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// Add Env information and create Test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Report backup", true);
		//Report backup
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "Test started", true);
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "===> STARTED <====");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "Test success", true);
		test.log(Status.PASS, result.getMethod().getMethodName() + "===> COMPLETED <====");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log(result.getMethod().getMethodName() + "Test failed", true);
		test.log(Status.FAIL, result.getMethod().getMethodName() + "===> FAILED <====");
		String testName = result.getMethod().getMethodName();
		
//		JavaUtility jUtil = new JavaUtility();
//		String time = jUtil.getCurrentDateTime();
		
		String time = new Date().toString().replace(' ', '_').replace(':', '_');
		TakesScreenshot ts = (TakesScreenshot)UtilityClassObject.getDriver();
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		
		test.addScreenCaptureFromBase64String(filePath, testName+"\t"+time);

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName() + "===> SKIPPED <====");
		Reporter.log(result.getMethod().getMethodName() + "Test skipped", true);
	}

}
