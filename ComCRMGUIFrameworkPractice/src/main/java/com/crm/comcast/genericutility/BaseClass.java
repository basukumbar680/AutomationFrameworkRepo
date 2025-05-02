package com.crm.comcast.genericutility;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	public WebDriver driver = null;
	public static WebDriver sdriver = null;

	/* Create Object */
	public DataBaseUtility dbLib = new DataBaseUtility();
	public FileUtility fLib = new FileUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriverUtility wLib = new WebDriverUtility();

	@BeforeSuite(groups = { "smokeTest", "regressionTest" })
	public void configBS() {
		System.out.println("Before Suite Annotation : Connect to DataBase, Report config");
		try {
			dbLib.getDbConnection();
		} catch (SQLException e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
	}

	// @Parameters("Browser")
	@BeforeClass(groups = { "smokeTest", "regressionTest" })
	public void configBC() {
		System.out.println("Before Class Annotation : Launch the browser");
		// String browser = browser1;
		String browser = System.getProperty("Browser", fLib.getDataFromPropertiesFile("Browser"));

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver, 20);
	}

	@BeforeMethod(groups = { "smokeTest", "regressionTest" })
	public void configBM() {
		System.out.println("Before Method Annotation : login to application");
		// Access properties by key

		String url = System.getProperty("Url", fLib.getDataFromPropertiesFile("Url"));
		String un = System.getProperty("Username", fLib.getDataFromPropertiesFile("Username"));
		String pwd = System.getProperty("Password", fLib.getDataFromPropertiesFile("Password"));
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(url, un, pwd);
	}

	@AfterMethod(groups = { "smokeTest", "regressionTest" })
	public void configAM() {
		System.out.println("After Method Annotation : logout from application");
		HomePage hp = new HomePage(driver);
		hp.logout();
	}

	@AfterClass(groups = { "smokeTest", "regressionTest" })
	public void configAC() {
		System.out.println("After Class Annotation : Close the browser");
		driver.quit();
	}

	@AfterSuite(groups = { "smokeTest", "regressionTest" })
	public void configAS() {
		System.out.println("After Suite Annotation : Close DataBase connection, Report backup");
		try {
			dbLib.closeDbConnection();
		} catch (SQLException e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
	}
}
