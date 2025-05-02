package com.crm.comcast.producttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.CreateNewProductPage;
import com.comcast.crm.objectrepositoryutility.CreateNewVendorPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.ProductsPage;
import com.comcast.crm.objectrepositoryutility.VendorsPage;
import com.crm.comcast.genericutility.BaseClass;

public class ProductModuleTest extends BaseClass {
	@Test
	public void productWithVendorTest() {

		// Get the first sheet from the workbook
		String prodName = null;
		String vendName = null;
		try {
			prodName = eLib.getDataFromExcelFile("Products", 1, 1) + jLib.getRandomNumber();
			vendName = eLib.getDataFromExcelFile("Products", 1, 2);
		} catch (EncryptedDocumentException | IOException e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
		// Navigate to Vendor module
		HomePage hp = new HomePage(driver);
		hp.navigateToVendorVendorPage();

		// Click on "Create Vendor" btn
		VendorsPage vp = new VendorsPage(driver);
		vp.getCreateNewVendorsImg().click();

		// Enter all the details & create new vendor
		CreateNewVendorPage cnvp = new CreateNewVendorPage(driver);
		cnvp.createVendor(vendName);

		// Navigate to product module
		hp.getProductsLink().click();

		// Click on "Create products" btn
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateNewProductsImg().click();

		// create product with vendor
		CreateNewProductPage cnpp = new CreateNewProductPage(driver);
		cnpp.createProducts(prodName, vendName);
	}

	@Test
	public void ProductWithVendorWithUnitWithGroup() {

		// Get the first sheet from the workbook
		String prodName = null;
		String vendName = null;
		String usageUnit = null;
		String group = null;
		try {
			prodName = eLib.getDataFromExcelFile("Products", 7, 1) + jLib.getRandomNumber();
			vendName = eLib.getDataFromExcelFile("Products", 7, 2);
			usageUnit = eLib.getDataFromExcelFile("Products", 7, 3);
			group = eLib.getDataFromExcelFile("Products", 7, 4);
		} catch (EncryptedDocumentException | IOException e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}

		// Navigate to Vendor module
		HomePage hp = new HomePage(driver);
		hp.navigateToVendorVendorPage();

		// Click on "Create Vendor" btn
		VendorsPage vp = new VendorsPage(driver);
		vp.getCreateNewVendorsImg().click();

		// Enter all the details & create new vendor
		CreateNewVendorPage cnvp = new CreateNewVendorPage(driver);
		cnvp.createVendor(vendName);

		// Navigate to product module
		hp.getProductsLink().click();

		// Click on "Create products" btn
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateNewProductsImg().click();

		// create product with vendor
		CreateNewProductPage cnpp = new CreateNewProductPage(driver);
		cnpp.createProducts(prodName, vendName, usageUnit, group);

	}

	@Test
	public void ProductWithVendorWithUser() {

		// Get the first sheet from the workbook
		String prodName = null;
		String vendName = null;
		String user = null;
		try {
			prodName = eLib.getDataFromExcelFile("Products", 4, 1) + jLib.getRandomNumber();
			vendName = eLib.getDataFromExcelFile("Products", 4, 2);
			user = eLib.getDataFromExcelFile("Products", 4, 3);
		} catch (EncryptedDocumentException | IOException e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}

		// Navigate to Vendor module
		HomePage hp = new HomePage(driver);
		hp.navigateToVendorVendorPage();

		// Click on "Create Vendor" btn
		VendorsPage vp = new VendorsPage(driver);
		vp.getCreateNewVendorsImg().click();

		// Enter all the details & create new vendor
		CreateNewVendorPage cnvp = new CreateNewVendorPage(driver);
		cnvp.createVendor(vendName);

		// Navigate to product module
		hp.getProductsLink().click();

		// Click on "Create products" btn
		ProductsPage pp = new ProductsPage(driver);
		pp.getCreateNewProductsImg().click();

		// create product with vendor
		CreateNewProductPage cnpp = new CreateNewProductPage(driver);
		cnpp.createProducts(prodName, vendName, user);

	}
}
