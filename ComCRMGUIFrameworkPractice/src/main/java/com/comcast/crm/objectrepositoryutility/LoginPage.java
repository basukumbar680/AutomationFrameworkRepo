package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement userNameEdit;

	@FindBy(name = "user_password")
	private WebElement passwordNameEdit;

	@FindBy(id = "submitButton")
	private WebElement loginButtonLink;

	public WebElement getUserNameEdit() {
		return userNameEdit;
	}

	public WebElement getPasswordNameEdit() {
		return passwordNameEdit;
	}

	public WebElement getLoginButtonLink() {
		return loginButtonLink;
	}
	public void loginToApp(String url,String un,String pwd) {
		waitForPageLoad(driver, 20);
		driver.get(url);
		userNameEdit.sendKeys(un);
		passwordNameEdit.sendKeys(pwd);
		loginButtonLink.click();
	}
}
