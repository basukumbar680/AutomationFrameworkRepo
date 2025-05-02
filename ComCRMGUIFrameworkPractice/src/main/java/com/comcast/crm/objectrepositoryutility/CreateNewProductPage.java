package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewProductPage {
	WebDriver driver;

	public CreateNewProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/* Create Object */
	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(className = "detailedViewTextBox")
	private WebElement productNameTextEdit;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	@FindBy(xpath = "//input[@name='vendor_name']/following-sibling::img")
	private WebElement vendorNameImg;

	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchTextBoxEdit;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchTextBtnEdit;

	@FindBy(xpath = "//a[@href='javascript:window.close();']")
	private WebElement vendorLinkEdit;

	@FindBy(xpath = "//td[text()='Handler 			']/following-sibling::td[1]/child::input[2]")
	private WebElement userRadioButton;
	/*
	 * @FindBy(xpath = "(//input[@name='assigntype'])[1])") private WebElement
	 * userRadioButton;
	 */

	@FindBy(xpath = "//select[@name='assigned_user_id']")
	private WebElement handlerTextBoxEditForUser;

	@FindBy(xpath = "//select[@name='usageunit']")
	private WebElement usageUnitDropDown;

	@FindBy(xpath = "(//input[@name='assigntype'])[2]")
	private WebElement groupRadioButton;

	@FindBy(xpath = "//select[@name='assigned_group_id']")
	private WebElement handlerTestBoxEditForGroup;

	public WebElement getProductNameTextEdit() {
		return productNameTextEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getVendorNameImg() {
		return vendorNameImg;
	}

	public WebElement getSearchTextBoxEdit() {
		return searchTextBoxEdit;
	}

	public WebElement getSearchTextBtnEdit() {
		return searchTextBtnEdit;
	}

	public WebElement getVendorLinkEdit() {
		return vendorLinkEdit;
	}

	public WebElement getUserRadioButton() {
		return userRadioButton;
	}

	public WebElement getHandlerTextBoxEdit() {
		return handlerTextBoxEditForUser;
	}

	public WebElement getUsageUnitDropDown() {
		return usageUnitDropDown;
	}

	public WebElement getGroupRadioButton() {
		return groupRadioButton;
	}

	public WebElement getHandlerTestBoxEditForGroup() {
		return handlerTestBoxEditForGroup;
	}

	public void createProducts(String prodName, String vendName) {
		productNameTextEdit.sendKeys(prodName);
		wLib.waitForElementPresent(driver, productNameTextEdit, 20);
		vendorNameImg.click();
		wLib.switchToTabOnUrl(driver, "module=Accounts&action");
		searchTextBoxEdit.sendKeys(vendName);
		searchTextBtnEdit.click();
		driver.findElement(By.xpath("//a[text()='" + vendName + "']")).click();
		wLib.switchToTabOnUrl(driver, "module=Contacts&action");
		saveButton.click();
	}

	public void createProducts(String prodName, String vendName, String user) {
		productNameTextEdit.sendKeys(prodName);
		wLib.waitForElementPresent(driver, productNameTextEdit, 20);
		vendorNameImg.click();
		wLib.switchToTabOnUrl(driver, "module=Vendors&action");
		searchTextBoxEdit.sendKeys(vendName);
		searchTextBtnEdit.click();
		driver.findElement(By.xpath("//a[text()='" + vendName + "']")).click();
		wLib.switchToTabOnUrl(driver, "module=Products&action");
		try {
			userRadioButton.click();
			wLib.select(handlerTextBoxEditForUser, user);
		} catch (Exception e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
		saveButton.click();
	}

	public void createProducts(String prodName, String vendName, String usageUnit, String group) {
		productNameTextEdit.sendKeys(prodName);
		wLib.waitForElementPresent(driver, productNameTextEdit, 20);
		vendorNameImg.click();
		wLib.switchToTabOnUrl(driver, "module=Vendors&action");
		searchTextBoxEdit.sendKeys(vendName);
		searchTextBtnEdit.click();
		driver.findElement(By.xpath("//a[text()='" + vendName + "']")).click();
		wLib.switchToTabOnUrl(driver, "module=Products&action");
		wLib.select(usageUnitDropDown, usageUnit);
		groupRadioButton.click();
		wLib.select(handlerTestBoxEditForGroup, group);
		saveButton.click();
	}
}
