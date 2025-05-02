package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateCantactWithSupportDateTest {
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

		String lastName = eLib.getDataFromExcelFile("contact", 4, 2);

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
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
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

		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDateYYYYMMDD(30);

		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);

		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();

		// step 5: Verify the start & end Date formate info expected result
		String startDateInfo = driver.findElement(By.xpath("//span[@id='dtlview_Support Start Date']")).getText();
		if (startDateInfo.equals(startDate)) {
			System.out.println(startDate + " :information is created ==>PASS");
		} else {
			System.out.println(startDate + " :information is not created ==>FAIL");
		}
		String endDateInfo = driver.findElement(By.xpath("//span[@id='dtlview_Support End Date']")).getText();
		if (endDateInfo.equals(endDate)) {
			System.out.println(endDate + " :information is created ==>PASS");
		} else {
			System.out.println(endDate + " :information is not created ==>FAIL");
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
