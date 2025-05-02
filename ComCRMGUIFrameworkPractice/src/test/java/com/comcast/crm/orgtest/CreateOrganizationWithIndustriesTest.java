package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;

public class CreateOrganizationWithIndustriesTest {
	public static void main(String[] args) throws IOException {

		/* Create Object */
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();

		// Access properties by key
		String browser = fLib.getDataFromPropertiesFile("Browser");
		String url = fLib.getDataFromPropertiesFile("Url");
		String un = fLib.getDataFromPropertiesFile("Username");
		String pwd = fLib.getDataFromPropertiesFile("Password");

		String orgName = eLib.getDataFromExcelFile("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcelFile("org", 4, 3);
		String type = eLib.getDataFromExcelFile("org", 4, 4);

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

		// step 2: Navigate to Organizations module
		driver.findElement(By.xpath("//a[text()='Organizations'][1]")).click();

		// step 2: Click on "Create Organizations" btn
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step 4: Enter all the details & create new Organizations
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		WebElement industryDrop = driver.findElement(By.name("industry"));
		Select s = new Select(industryDrop);
		s.selectByVisibleText(industry);

		WebElement typeDrop = driver.findElement(By.name("accounttype"));
		Select s1 = new Select(typeDrop);
		s1.selectByVisibleText(type);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();

		// step 5: Verify the industries and type info expected result
		String industryInfo = driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if (industryInfo.equals(industry)) {
			System.out.println(industry + " :information is created ==>PASS");
		} else {
			System.out.println(industry + " :information is not created ==>FAIL");
		}

		String typeInfo = driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if (typeInfo.equals(type)) {
			System.out.println(type + " :information is created ==>PASS");
		} else {
			System.out.println(type + " :information is not created ==>FAIL");
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
