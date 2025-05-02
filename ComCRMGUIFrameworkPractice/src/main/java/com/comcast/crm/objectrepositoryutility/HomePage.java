package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
		
		WebDriver driver;
		public HomePage(WebDriver driver) {
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		/*Create Object*/
		WebDriverUtility wLib=new WebDriverUtility();

		@FindBy(linkText = "Organizations")
		private WebElement organizationsLink;
		
		@FindBy(linkText = "Contacts")
		private WebElement contactsLink;
		
		@FindBy(linkText = "Products")
		private WebElement productsLink;
		
		@FindBy(linkText = "Opportunities")
		private WebElement opportunitiesLink;
		
		@FindBy(linkText = "More")
		private WebElement moreLink;
		
		@FindBy(linkText = "Vendors")
		private WebElement vendorsLink;
		
		@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
		private WebElement adminImg;
		
		@FindBy(linkText ="Sign Out")
		private WebElement signOutLink;

		public WebElement getOrganizationsLink() {
			return organizationsLink;
		}

		public WebElement getContactsLink() {
			return contactsLink;
		}
		
		public WebElement getProductsLink() {
			return productsLink;
		}

		public WebElement getOpportunitiesLink() {
			return opportunitiesLink;
		}

		public WebElement getMoreLink() {
			return moreLink;
		}

		public WebElement getVendorsLink() {
			return vendorsLink;
		}
		
		public void navigateToVendorVendorPage() {
			wLib.mouseMoveOnElement(driver, moreLink);
			vendorsLink.click();
		}
		public void logout() {
			Actions action=new Actions(driver);
			action.moveToElement(adminImg).build().perform();
			signOutLink.click();
		}
}
