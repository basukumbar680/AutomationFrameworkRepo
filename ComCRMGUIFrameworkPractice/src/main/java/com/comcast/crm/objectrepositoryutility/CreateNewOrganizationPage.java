package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreateNewOrganizationPage {
	
	WebDriver driver;
	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	/*Create Object*/
	WebDriverUtility wLib=new WebDriverUtility();
	
	@FindBy(name="accountname")
	private WebElement organizationNameTextEdit;
	
	@FindBy(name="industry")
	private WebElement industryDropDown;
	
	@FindBy(name="accounttype")
	private WebElement accountTypeDropDown;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(id="phone")
	private WebElement phoneNumberTextEdit;
	
	public WebElement getOrganizationNameTextEdit() {
		return organizationNameTextEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public WebElement getPhoneNumberTextEdit() {
		return phoneNumberTextEdit;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getAccountTypeDropDown() {
		return accountTypeDropDown;
	}

	public void createOrganization(String orgName) {
		organizationNameTextEdit.sendKeys(orgName);
		saveButton.click();
	}
	
	public void createOrganization(String orgName,String phoneNum) {
		organizationNameTextEdit.sendKeys(orgName);
		phoneNumberTextEdit.sendKeys(phoneNum);
		saveButton.click();
	}
	
	public void createOrganization(String orgName,String industry,String type) {
		organizationNameTextEdit.sendKeys(orgName);
		wLib.select(industryDropDown, industry);
		wLib.select(accountTypeDropDown, type);
		saveButton.click();
	}
}
