package com.comcast.crm.contacttest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateCantactWithOrgTest {
	public static void main(String[] args) throws IOException {

		/* Create Object */
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		// Access properties by key
		String browser = fLib.getDataFromPropertiesFile("Browser");
		String url = fLib.getDataFromPropertiesFile("Url");
		String un = fLib.getDataFromPropertiesFile("Username");
		String pwd = fLib.getDataFromPropertiesFile("Password");

		String orgName = eLib.getDataFromExcelFile("org", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcelFile("contact", 7, 3);

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

		// step 1: login to app
		driver.manage().window().maximize();
		wLib.waitForPageLoad(driver, 20);
		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();

		// step 2: Navigate to organization module
		driver.findElement(By.xpath("//a[text()='Organizations'][1]")).click();

		// step 2: Click on "Create Organization" btn
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step 4: Enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();

		// step 5: Verify header msg expected result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + " :is created ==>PASS");
		} else {
			System.out.println(orgName + " :is not created ==>FAIL");
		}

		// step : Navigate to Contacts module
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// step : Click on "Create Contacts" btn
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step : Enter all the details & create new Contacts
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// Switch to child window
		wLib.switchToTabOnUrl(driver, "module=Accounts&action");

		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// Switch to parent window
		wLib.switchToTabOnUrl(driver, "module=Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();

		/*
		 * //step : Verify the org name info expected result String orgNameInfo =
		 * driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']")).
		 * getText(); if (orgNameInfo.equals(orgName)) {
		 * System.out.println(orgName+" :information is created ==>PASS"); }else {
		 * System.out.println(orgName+" :information is not created ==>FAIL"); }
		 */
		driver.quit();
	}
}
