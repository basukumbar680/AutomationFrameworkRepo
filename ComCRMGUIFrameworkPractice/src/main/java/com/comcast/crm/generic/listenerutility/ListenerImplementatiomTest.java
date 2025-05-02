package com.comcast.crm.generic.listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.crm.comcast.genericutility.BaseClass;

public class ListenerImplementatiomTest implements ITestListener, ISuiteListener {

	public static ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("===Report configuratio===");

		// spark report configuration
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_" + time + ".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add environment information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Operating System", "Windows-10");
		report.setSystemInfo("Browser", "Chrome");
	}

	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("===Report backup===");

		report.flush();
	}

	@Override
	public void onStart(ITestContext context) {
		Reporter.log("===onStart===");
	}

	@Override
	public void onFinish(ITestContext context) {
		Reporter.log("===onFinish===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String filePath = ts.getScreenshotAs(OutputType.BASE64);
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath, testName + "_" + time);
		UtilityClassObject.setTest(test);
		test.log(Status.FAIL, result.getMethod().getMethodName() + "===>FAILED<===");
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("===>" + result.getMethod().getMethodName() + "<===START===");
		test = report.createTest(result.getMethod().getMethodName());
		test.log(Status.INFO, result.getMethod().getMethodName() + "===>STARTED<===");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log("===>" + result.getMethod().getMethodName() + "<===END===");
		test.log(Status.PASS, result.getMethod().getMethodName() + "===>COMPLETED<===");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("===" + result.getMethod().getMethodName() + "===");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		Reporter.log("===" + result.getMethod().getMethodName() + "===");
	}
}
