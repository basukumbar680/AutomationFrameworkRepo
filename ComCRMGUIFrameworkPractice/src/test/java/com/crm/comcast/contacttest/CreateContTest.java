package com.crm.comcast.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.objectrepositoryutility.ContactsInformationPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.crm.comcast.genericutility.BaseClass;

public class CreateContTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createContTest() throws EncryptedDocumentException, IOException {

		Reporter.log("Create contact and verify",true);

		String lastName = eLib.getDataFromExcelFile("contact", 1, 2);

		// Navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// Click on "Create Contacts" btn
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactsImg().click();

		// Enter all the details & create new Contacts
		CreateNewContactsPage cncp = new CreateNewContactsPage(driver);
		cncp.createContact(lastName);

		// Verify the last Name info expected result
		ContactsInformationPage cip = new ContactsInformationPage(driver);
		String actualHeader = cip.getHeaderMsg().getText();
		boolean status = actualHeader.contains(lastName);
		Assert.assertEquals(status, true);

		String acualLastName = cip.getLastNameTextField().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(acualLastName, lastName);
	}

	@Test(groups = { "regressionTest" })
	public void createContSupportWithDateTest() throws EncryptedDocumentException, IOException {
		Reporter.log("Create contact with date and verify",true);

		String lastName = eLib.getDataFromExcelFile("contact", 4, 2);

		// Navigate to Contacts module
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();

		// Click on "Create Contacts" btn
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactsImg().click();

		// Enter all the details & create new Contacts
		CreateNewContactsPage cncp = new CreateNewContactsPage(driver);

		String startDate = jLib.getSystemDateYYYYMMDD();
		String endDate = jLib.getRequiredDateYYYYMMDD(30);

		cncp.createContact(lastName, startDate, endDate);

		// step 5: Verify the start & end Date formate info expected result
		ContactsInformationPage cip = new ContactsInformationPage(driver);
		String actualStartDate = cip.getSupportStartDate().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualStartDate, true);

		String actualEndDate = cip.getSupportEndDate().getText();
		sa.assertEquals(actualEndDate, true);
	}

	@Test(groups = { "regressionTest" })
	public void createContWithOrgTest() throws EncryptedDocumentException, IOException {
		Reporter.log("Create contact with date and verify",true);

		String orgName = eLib.getDataFromExcelFile("org", 7, 2) + jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcelFile("contact", 7, 3);

		// Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		// Click on "Create Organization" btn
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrganizationImg().click();

		// Enter all the details & create new organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(orgName);

		// step 5: Verify header msg expected result
		OrganizationInformationPage cip = new OrganizationInformationPage(driver);
		String organizationHeader = cip.getOrganizationInformationHeader().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(organizationHeader, true);

		// Navigate to Contacts module
		hp.getContactsLink().click();

		// Click on "Create Contacts" btn
		ContactsPage cp = new ContactsPage(driver);
		cp.getCreateNewContactsImg().click();

		// Enter all the details & create new Contacts
		CreateNewContactsPage cncp = new CreateNewContactsPage(driver);
		cncp.createContact(lastName, orgName);
	}
}
