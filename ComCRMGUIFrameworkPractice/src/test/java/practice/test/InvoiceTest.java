package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseClass;

public class InvoiceTest extends BaseClass {
	@Test
	public void craeteInvoiceTest() {
		System.out.println("Execute craeteInvoiceTest");
		System.out.println("Step1");
		System.out.println("Step2");
	}

	@Test
	public void craeteInvoiceWithContactTest() {
		System.out.println("Execute craeteInvoiceWithContactTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step1");
		System.out.println("Step2");
	}
}
