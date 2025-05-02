package com.comcast.crm.opportunitiestest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class OpportunityWithUser {
	public static void main(String[] args) throws IOException, InterruptedException {

		/* Create Object */
		FileUtility fLib = new FileUtility();
		ExcelUtility eLib = new ExcelUtility();
		WebDriverUtility wLib = new WebDriverUtility();
		JavaUtility jLib = new JavaUtility();

		// Access properties by key
		String browser = fLib.getDataFromPropertiesFile("Browser");
		String url = fLib.getDataFromPropertiesFile("Url");
		String un = fLib.getDataFromPropertiesFile("Username");
		String pwd = fLib.getDataFromPropertiesFile("Password");

		// Get the first sheet from the workbook
		String orgName = eLib.getDataFromExcelFile("Opportunity ", 1, 1) + jLib.getRandomNumber();
		String OppName = eLib.getDataFromExcelFile("Opportunity ", 1, 2);
		String assiUser = eLib.getDataFromExcelFile("Opportunity ", 1, 3);
		String salesStage = eLib.getDataFromExcelFile("Opportunity ", 1, 4);

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

		// step 2: Navigate to organization module
		driver.findElement(By.xpath("//a[text()='Organizations'][1]")).click();

		// step 2: Click on "Create Organization" btn
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step 4: Enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Opportunities']")).click();
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		driver.findElement(By.xpath("//input[@name='potentialname']")).sendKeys(OppName);

		driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img")).click();

		// Switch to child window
		wLib.switchToTabOnUrl(driver, "module=Accounts&action");

		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// Switch to parent window
		wLib.switchToTabOnUrl(driver, "module=Potentials&action");

		/*
		 * WebElement typeDropdown =
		 * driver.findElement(By.xpath("//select[@name='opportunity_type']")); Select
		 * s1=new Select(typeDropdown); s1.selectByVisibleText(); WebElement
		 * leadSourceDropdown =
		 * driver.findElement(By.xpath("//select[@name='leadsource']")); Select s2=new
		 * Select(leadSourceDropdown); s2.selectByVisibleText();
		 */

		driver.findElement(By.xpath("//input[@name='assigntype'][1]")).click();
		WebElement user = driver.findElement(By.xpath("//select[@name='assigned_user_id']"));
		Select s3 = new Select(user);
		s3.selectByVisibleText(assiUser);

		WebElement salesStageDropdown = driver.findElement(By.xpath("//select[@name='sales_stage']"));
		Select s4 = new Select(salesStageDropdown);
		s4.selectByVisibleText(salesStage);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();
		driver.quit();
	}
}
