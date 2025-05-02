package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewContactsPage {
	WebDriver driver;

	public CreateNewContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/* Create Object */
	WebDriverUtility wLib = new WebDriverUtility();

	@FindBy(name = "lastname")
	private WebElement lastNameTextEdit;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement organizationNameImg;

	@FindBy(name = "support_start_date")
	private WebElement supportStartDateDD;

	@FindBy(name = "support_end_date")
	private WebElement supportEndDateDD;

	@FindBy(xpath = "//input[@name='search_text']")
	private WebElement searchTextBoxEdit;

	@FindBy(xpath = "//input[@name='search']")
	private WebElement searchTextBtnEdit;

	@FindBy(xpath = "//a[@href='javascript:window.close();']")
	private WebElement orgLinkEdit;

	/*
	 * @FindBy(xpath = "//a[text()='" + orgName + "']") private WebElement
	 * orgLinkEdit;
	 */

	public WebElement getLastNameTextEdit() {
		return lastNameTextEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public WebElement getOrganizationNameImg() {
		return organizationNameImg;
	}

	public WebElement getSupportStartDateDD() {
		return supportStartDateDD;
	}

	public WebElement getSupportEndDateDD() {
		return supportEndDateDD;
	}

	public WebElement getSearchTextBoxEdit() {
		return searchTextBoxEdit;
	}

	public WebElement getSearchTextBtnEdit() {
		return searchTextBtnEdit;
	}

	public WebElement getOrgLinkEdit() {
		return orgLinkEdit;
	}

	public void createContact(String lastName) {
		lastNameTextEdit.sendKeys(lastName);
		saveButton.click();
	}

	public void createContact(String lastName, String orgName) {
		lastNameTextEdit.sendKeys(lastName);
		organizationNameImg.click();
		wLib.switchToTabOnUrl(driver, "module=Accounts&action");
		searchTextBoxEdit.sendKeys(orgName);
		searchTextBtnEdit.click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		wLib.switchToTabOnUrl(driver, "module=Contacts&action");
		saveButton.click();
	}

	public void createContact(String lastName, String startDate, String endDate) {
		lastNameTextEdit.sendKeys(lastName);
		supportStartDateDD.clear();
		supportStartDateDD.sendKeys(startDate);
		supportEndDateDD.clear();
		supportEndDateDD.sendKeys(endDate);
		saveButton.click();
	}
}
