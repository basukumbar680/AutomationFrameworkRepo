package com.demoapp.ecom;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.demoapp.ecom.generic.fileutility.ExcelUtility;

public class Example {
	@Test
	public void test() throws EncryptedDocumentException, IOException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/login");
		driver.findElement(By.id("Email")).sendKeys("basukumbar680@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("Basu@123");
		driver.findElement(By.xpath("//input[@type='submit' and @value='Log in']")).click();

		ExcelUtility eLib = new ExcelUtility();
		String computer = eLib.getDataFromExcelFile("Sheet1", 1, 2);
		driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(computer, Keys.ENTER);
		driver.findElement(By.xpath("//input[@type='button' and @value='Add to cart']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath(
				"//h1[@itemprop='name']/ancestor::div[@class='overview']/child::div[@class='add-to-cart']/div/input[@type='button']"))
				.click();

	}
}
