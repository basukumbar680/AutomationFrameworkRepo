package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.genericutility.BaseClass;
@Listeners(com.comcast.crm.generic.listenerutility.ListenerImplementatiomTest.class)
public class InvoiceTest3 extends BaseClass {
	@Test(retryAnalyzer = com.comcast.crm.generic.listenerutility.RetryImplementation.class)
	public void craeteInvoiceTest() {
		System.out.println("Execute craeteInvoiceTest");
		String actTitle = driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("Step1");
		System.out.println("Step2");
	}

	@Test
	public void craeteInvoiceWithContactTest() {
		System.out.println("Execute craeteInvoiceWithContactTest");
		System.out.println("Step1");
		System.out.println("Step2");
	}
}
