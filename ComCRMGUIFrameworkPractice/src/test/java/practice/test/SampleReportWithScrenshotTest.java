package practice.test;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleReportWithScrenshotTest {

	@Test
	public void createContactTest() {

		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");

		// code to take screenshot
		TakesScreenshot ts = (TakesScreenshot) driver;
		String filePath = ts.getScreenshotAs(OutputType.BASE64);

		// spark report configuration
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add environment information and create test
		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Operating System", "Windows-10");
		report.setSystemInfo("Browser", "Chrome");
		ExtentTest test = report.createTest("createContactTest");

		test.log(Status.INFO, "Login to appliaction");
		test.log(Status.INFO, "Navigate to contact page");
		test.log(Status.INFO, " Create contact");
		if ("Jack".equals("Jacksparrow")) {
			test.log(Status.PASS, "Contact is created");
		} else {
			test.addScreenCaptureFromBase64String(filePath, "ErrorFile");
		}
		report.flush();
	}
}
