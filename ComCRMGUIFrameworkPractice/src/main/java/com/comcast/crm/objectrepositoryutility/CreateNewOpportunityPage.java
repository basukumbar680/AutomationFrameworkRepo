package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOpportunityPage {
	WebDriver driver;

	public CreateNewOpportunityPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void createOpportunities(String orgName) {
		//organizationNameTextEdit.sendKeys(orgName);
		//saveButton.click();
	}
}
