package com.demoapp.ecom;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.demoapp.ecom.basetest.BaseClass;
import com.demoapp.ecom.generic.objectrepo.HomePage;

public class AddProductToCartAndValidate extends BaseClass {
	@Test
	public void addBookToCartAndValidateTest() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getBookCatogoriesLink().click();

		String book = eLib.getDataFromExcelFile("Sheet1", 1, 1);
		driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(book, Keys.ENTER);
		driver.findElement(By.xpath("//input[@type='button' and @value='Add to cart']")).click();

		driver.findElement(By.xpath(
				"//div[@class='header-links']/child::ul/child::li[@id='topcartlink']/child::a/child::span[@class='cart-label']"))
				.click();

		List<WebElement> allOptions = driver.findElements(
				By.xpath("//tr[@class='cart-item-row']/parent::tbody/descendant::td/child::a[@class='product-name']"));
		for (WebElement element : allOptions) {
			String text = element.getText();
			if (text.equals(book)) {
				System.out.println(text);
				Assert.assertEquals(book, text);
			}
		}

		/*
		 * List<WebElement> allOptions = driver.findElements(By.xpath(
		 * "//tr[@class='cart-item-row']/parent::tbody/descendant::td[@class='product']"
		 * )); for (WebElement element : allOptions) { String text = element.getText();
		 * System.out.println(text); }
		 * 
		 * driver.findElement( By.xpath(
		 * "//h1[text()='Books']/ancestor::div/div[2]/div[3]/div[1]/div/div[2]/div[3]/div[2]/input"
		 * )) .click();
		 * 
		 * 
		 * hp.getShoppingCartLink().click(); List<WebElement> allElement =
		 * driver.findElements( By.
		 * xpath("//div[@class='page shopping-cart-page']/div[2]/div[1]/form/table/tbody/tr/td[3]/a"
		 * ));
		 * 
		 * for (WebElement we : allElement) { String text = we.getText();
		 * 
		 * if (text.equals(book)) { Assert.assertEquals(book, text); } else if
		 * (text.equals(book)) { Assert.assertEquals(book, text); } else if
		 * (text.equals(book)) { Assert.assertEquals(book, text); } else {
		 * System.out.println("Fail"); } }
		 * 
		 * Select s = new Select(listbox); List<WebElement> allOptions = s.getOptions();
		 * int count = allOptions.size(); int flag = 0; for (int i = 0; i < count; i++)
		 * { String s1 = allOptions.get(i).getText(); if (book.equals(s1)) { flag++;
		 * break; } } if (flag == 1) { System.out.println(book +
		 * ": is available and PASS"); } else { System.out.println(book +
		 * ": is not available and FAIL"); } // Assert.assertEquals(false, false);
		 */
	}

	@Test
	public void addComputerToCartAndValidateTest() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getBookCatogoriesLink().click();

		String computer = eLib.getDataFromExcelFile("Sheet1", 1, 2);
		driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(computer, Keys.ENTER);
		driver.findElement(By.xpath("//input[@type='button' and @value='Add to cart']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath(
				"//h1[@itemprop='name']/ancestor::div[@class='overview']/child::div[@class='add-to-cart']/div/input[@type='button']"))
				.click();

		driver.findElement(By.xpath(
				"//div[@class='header-links']/child::ul/child::li[@id='topcartlink']/child::a/child::span[@class='cart-label']"))
				.click();

		List<WebElement> allOptions = driver.findElements(
				By.xpath("//tr[@class='cart-item-row']/parent::tbody/descendant::td/child::a[@class='product-name']"));
		for (WebElement element : allOptions) {
			String text = element.getText();
			if (text.equals(computer)) {
				System.out.println(text);
				Assert.assertEquals(computer, text);
			}
		}
	}

	@Test
	public void addElectonicsToCartAndValidateTest() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getBookCatogoriesLink().click();

		String electonics = eLib.getDataFromExcelFile("Sheet1", 1, 3);
		driver.findElement(By.xpath("//input[@id='small-searchterms']")).sendKeys(electonics, Keys.ENTER);

		driver.findElement(By.xpath("//input[@type='button' and @value='Add to cart']")).click();

		driver.findElement(By.xpath(
				"//div[@class='header-links']/child::ul/child::li[@id='topcartlink']/child::a/child::span[@class='cart-label']"))
				.click();

		List<WebElement> allOptions = driver.findElements(
				By.xpath("//tr[@class='cart-item-row']/parent::tbody/descendant::td/child::a[@class='product-name']"));
		for (WebElement element : allOptions) {
			String text = element.getText();
			if (text.equals(electonics)) {
				System.out.println(text);
				Assert.assertEquals(electonics, text);
			}
		}

		/*
		 * hp.getShoppingCartLink().click(); List<WebElement> allElement =
		 * driver.findElements( By.
		 * xpath("//div[@class='page shopping-cart-page']/div[2]/div[1]/form/table/tbody/tr/td[3]/a"
		 * ));
		 * 
		 * for (WebElement we : allElement) { String text = we.getText(); if
		 * (text.equals(electonics)) { Assert.assertEquals(electonics, text); } else if
		 * (text.equals(electonics)) { Assert.assertEquals(electonics, text); } else if
		 * (text.equals(electonics)) { Assert.assertEquals(electonics, text); } else {
		 * System.out.println("Fail"); } }
		 */
	}
}
