package com.demoapp.ecom.generic.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "Email")
	private WebElement emailTextBoxEdit;

	@FindBy(id = "Password")
	private WebElement passwordTextBoxEdit;

	@FindBy(xpath = "//input[@type='submit' and @value='Log in']")
	private WebElement loginBotton;

	public WebElement getEmailTextBoxEdit() {
		return emailTextBoxEdit;
	}

	public WebElement getPasswordTextBoxEdit() {
		return passwordTextBoxEdit;
	}

	public WebElement getLoginBotton() {
		return loginBotton;
	}

	public void loginToApplication(String url,String email, String Pwd) {
		driver.get(url);
		emailTextBoxEdit.sendKeys(email);
		passwordTextBoxEdit.sendKeys(Pwd);
		loginBotton.click();
	}
}
