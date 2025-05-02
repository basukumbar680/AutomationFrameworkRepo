package com.demoapp.ecom.generic.objectrepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BookPage {
	WebDriver driver;

	public BookPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h1[text()='Books']/ancestor::div/div[2]/div[3]/div[1]/div/div[2]/div[3]/div[2]/input")
	private WebElement addCartLink;
	
	@FindBy(xpath = "//span[text()='Shopping cart']")
	private WebElement shopCartLink;

	public WebElement getAddCartLink() {
		return addCartLink;
	}
}
