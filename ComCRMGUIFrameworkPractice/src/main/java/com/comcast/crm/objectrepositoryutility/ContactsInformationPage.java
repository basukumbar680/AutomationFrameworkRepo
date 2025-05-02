package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsInformationPage {
	WebDriver driver;

	public ContactsInformationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;

	@FindBy(xpath = "//span[@id='dtlview_Last Name']")
	private WebElement lastNameTextField;
	
	@FindBy(xpath = "//span[@id='dtlview_Support Start Date']")
	private WebElement supportStartDate;
	
	@FindBy(xpath = "//span[@id='dtlview_Support End Date']")
	private WebElement supportEndDate;
	
	public WebElement getSupportStartDate() {
		return supportStartDate;
	}

	public WebElement getSupportEndDate() {
		return supportEndDate;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}

	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}
}
