package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewVendorPage {
	WebDriver driver;

	public CreateNewVendorPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "vendorname")
	private WebElement vendorNameTextEdit;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveButton;

	public WebElement getVendorNameTextEdit() {
		return vendorNameTextEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}

	public void createVendor(String vendName) {
		vendorNameTextEdit.sendKeys(vendName);
		saveButton.click();
	}
}
