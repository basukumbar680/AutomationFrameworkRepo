package com.crm.comcast.orgtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInformationPage;
import com.comcast.crm.objectrepositoryutility.OrganizationPage;
import com.crm.comcast.genericutility.BaseClass;

public class CreateOrgTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrgTest() throws EncryptedDocumentException, IOException {
		Reporter.log("Create organization and verify", true);

		// Read the testscrit data from excel file
		UtilityClassObject.getTest().log(Status.INFO, "Read the data from Excel");
		String orgName = eLib.getDataFromExcelFile("org", 1, 2) + jLib.getRandomNumber();

		// Navigate to organization module
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to organization module");
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		// Click on "Create Organization" btn
		UtilityClassObject.getTest().log(Status.INFO, "Create Organization btn");
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrganizationImg().click();

		// Enter all the details & create new organization
		UtilityClassObject.getTest().log(Status.INFO, orgName + "===>Enter all the details & create new organization");
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(orgName);

		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String actualOrgNameInfo = oip.getOrganizationInformationHeader().getText();
		boolean status = actualOrgNameInfo.contains(orgName);
		Assert.assertEquals(status, true);
	}

	@Test(groups = { "regressionTest" })
	public void createOrgWithIndustriesTest() throws EncryptedDocumentException, IOException {
		Reporter.log("Create organization with industries and verify");

		String orgName = eLib.getDataFromExcelFile("org", 4, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcelFile("org", 4, 3);
		String type = eLib.getDataFromExcelFile("org", 4, 4);

		// Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		// Click on "Create Organization" btn
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrganizationImg().click();

		// Enter all the details & create new organization
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(orgName, industry, type);

		// Verify the industries and type info expected result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String industryInfo = oip.getIndustryDropDown().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(industryInfo, industry);

		String typeInfo = oip.getTypeDropDown().getText();
		sa.assertEquals(typeInfo, type);
		sa.assertAll();
	}

	@Test(groups = { "regressionTest" })
	public void createOrgWithPhoneNumberTest() throws EncryptedDocumentException, IOException {
		Reporter.log("Create organization with Phone Number and verify");

		String orgName = eLib.getDataFromExcelFile("org", 7, 2) + jLib.getRandomNumber();
		String phoneNum = eLib.getDataFromExcelFile("org", 7, 3);

		// Navigate to organization module
		HomePage hp = new HomePage(driver);
		hp.getOrganizationsLink().click();

		// Click on "Create Organization" btn
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateNewOrganizationImg().click();

		// Enter all the details & create new organization with Phone Number
		CreateNewOrganizationPage cnop = new CreateNewOrganizationPage(driver);
		cnop.createOrganization(orgName, phoneNum);

		// Verify the phone number info expected result
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		String phoneNumInfo = oip.getPhoneNumberTextField().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(phoneNumInfo, phoneNum);
		sa.assertAll();
	}
}
