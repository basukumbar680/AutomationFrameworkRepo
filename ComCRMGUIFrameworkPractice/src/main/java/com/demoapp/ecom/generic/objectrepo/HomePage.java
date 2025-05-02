package com.demoapp.ecom.generic.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(xpath = "//div[@class='master-wrapper-content']/child::div[2]/ul/li/a")
	private WebElement categoriesLink;

	@FindBy(xpath = "//div[@class='master-wrapper-content']/child::div[2]/ul/li/a[1]")
	private WebElement bookCatogoriesLink;

	@FindBy(xpath = "//div[@class='master-wrapper-content']/child::div[2]/ul/li/a[2]")
	private WebElement computersCatogoriesLink;

	@FindBy(xpath = "//div[@class='master-wrapper-content']/child::div[2]/ul/li/a[3]")
	private WebElement electronicsCatogoriesLink;

	@FindBy(xpath = "//a[text()='Log out']")
	private WebElement logoutLink;
	
	@FindBy(xpath = "//span[text()='Shopping cart']")
	private WebElement shoppingCartLink;

	public WebElement getShoppingCartLink() {
		return shoppingCartLink;
	}

	public WebElement getCategoriesLink() {
		return categoriesLink;
	}

	public WebElement getBookCatogoriesLink() {
		return bookCatogoriesLink;
	}

	public WebElement getComputersCatogoriesLink() {
		return computersCatogoriesLink;
	}

	public WebElement getElectronicsCatogoriesLink() {
		return electronicsCatogoriesLink;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}

	public void selectCategories(String categories) {
		if (categories.contains("books")) {
			bookCatogoriesLink.click();
		} else if (categories.contains("computers")) {
			computersCatogoriesLink.click();
		} else if (categories.contains("electronics")) {
			electronicsCatogoriesLink.click();
		}
	}

	public void logoutFromApp() {
		wLib.mouseMoveOnElement(driver, logoutLink);
		logoutLink.click();
	}
}
