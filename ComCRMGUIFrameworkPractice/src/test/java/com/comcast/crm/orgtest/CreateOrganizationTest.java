package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;

public class CreateOrganizationTest {
	public static void main(String[] args) throws IOException {

		/* Create Object */
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();

		// Access properties by key
		String browser = fLib.getDataFromPropertiesFile("Browser");
		String url = fLib.getDataFromPropertiesFile("Url");
		String un = fLib.getDataFromPropertiesFile("Username");
		String pwd = fLib.getDataFromPropertiesFile("Password");

		String orgName = eLib.getDataFromExcelFile("org", 1, 2) + jLib.getRandomNumber();

		WebDriver driver = null;

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		//step 1: login to appliaction
		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver, 20);
		driver.get(url);
		
		LoginPage lp = PageFactory.initElements(driver, LoginPage.class);
		lp.loginToApp(url, un, pwd);
		
		// step 2: Navigate to organization module
		HomePage hp=new HomePage(driver);
		hp.getOrganizationsLink().click();

		// step 2: Click on "Create Organization" btn
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateNewOrganizationImg().click();

		// step 4: Enter all the details & create new organization
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrganization(orgName);

		// step 5: Verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " :is created ==>PASS");
		} else {
			System.out.println(orgName + " :is not created ==>FAIL");
		}

		// step 5: Verify header org name info expected result
		//OrganizationInformationPage oip=new OrganizationInformationPage(driver);
		//soip.getOrganizationInformationHeader().getText();
		String actualOrgNameInfo = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		if (actualOrgNameInfo.equals(orgName)) {
			System.out.println(orgName + " :is created ==>PASS");
		} else {
			System.out.println(orgName + " :is not created ==>FAIL");
		}

		/*
		 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).
		 * click();
		 * 
		 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).
		 * click(); WebElement signOutLink =
		 * driver.findElement(By.xpath("//a[text()='Sign Out']")); Actions action=new
		 * Actions(driver); action.moveToElement(signOutLink).build().perform();
		 * signOutLink.click();
		 */
		driver.quit();
	}
}
