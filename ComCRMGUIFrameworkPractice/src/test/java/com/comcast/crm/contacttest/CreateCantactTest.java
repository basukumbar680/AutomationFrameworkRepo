package com.comcast.crm.contacttest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateCantactTest {
	public static void main(String[] args) throws IOException {

		/* Create Object */
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		// Access properties by key
		String browser = fLib.getDataFromPropertiesFile("Browser");
		String url = fLib.getDataFromPropertiesFile("Url");
		String un = fLib.getDataFromPropertiesFile("Username");
		String pwd = fLib.getDataFromPropertiesFile("Password");

		String lastName = eLib.getDataFromExcelFile("contact", 1, 2);

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

		wLib.maximizeWindow(driver);
		wLib.waitForPageLoad(driver, 20);
		driver.get(url);

		driver.findElement(By.name("user_name")).sendKeys(un);
		driver.findElement(By.name("user_password")).sendKeys(pwd);
		driver.findElement(By.id("submitButton")).click();

		// step 2: Navigate to Contacts module
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();

		// step 2: Click on "Create Contacts" btn
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 4: Enter all the details & create new Contacts
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();

		// step 5: Verify the last Name info expected result
		String lastNameInfo = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		if (lastNameInfo.equals(lastName)) {
			System.out.println(lastName + " :information is created ==>PASS");
		} else {
			System.out.println(lastName + " :information is not created ==>FAIL");
		}

		/*
		 * driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).
		 * click();
		 * 
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
