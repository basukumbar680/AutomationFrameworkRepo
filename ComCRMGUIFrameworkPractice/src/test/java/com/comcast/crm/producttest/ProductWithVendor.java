package com.comcast.crm.producttest;

import java.io.IOException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ProductWithVendor {
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
		String prodName = eLib.getDataFromExcelFile("Products", 1, 1) + jLib.getRandomNumber();
		String vendName = eLib.getDataFromExcelFile("Products", 1, 2);

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

		driver.findElement(By.xpath("//a[text()='More']")).click();
		WebElement vendorOpt = driver.findElement(By.xpath("//a[@name='Vendors']"));
		Actions action = new Actions(driver);
		action.moveToElement(vendorOpt).build().perform();
		vendorOpt.click();

		driver.findElement(By.xpath("//img[@title='Create Vendor...']")).click();
		driver.findElement(By.xpath("//input[@name='vendorname']")).sendKeys(vendName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();

		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(prodName);

		driver.findElement(By.xpath("//input[@name='vendor_name']/following-sibling::img")).click();

		// Switch to child window
		wLib.switchToTabOnUrl(driver, "module=Vendors&action");

		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(vendName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[text()='" + vendName + "']")).click();

		// Switch to parent window
		wLib.switchToTabOnUrl(driver, "module=Products&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]'][1]")).click();
		driver.quit();
	}
}
