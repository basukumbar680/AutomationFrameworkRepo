package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	WebDriver driver;

	public OrganizationInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement organizationInformationHeader;

	@FindBy(xpath = "//span[@id='dtlview_Organization Name']")
	private WebElement organizationNameHeader;

	@FindBy(xpath = "//span[@id='dtlview_Industry']/child::font")
	private WebElement industryDropDown;

	@FindBy(xpath = "//span[@id='dtlview_Type']/child::font")
	private WebElement typeDropDown;
	
	@FindBy(xpath = "//span[@id='dtlview_Phone']")
	private WebElement phoneNumberTextField;

	public WebElement getOrganizationInformationHeader() {
		return organizationInformationHeader;
	}

	public WebElement getOrganizationNameHeader() {
		return organizationNameHeader;
	}

	public WebElement getIndustryDropDown() {
		return industryDropDown;
	}

	public WebElement getTypeDropDown() {
		return typeDropDown;
	}

	public WebElement getPhoneNumberTextField() {
		return phoneNumberTextField;
	}
	
}
