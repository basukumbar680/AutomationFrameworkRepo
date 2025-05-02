 package practice.amazontest;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest3 {
	 
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName) {
		
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//Search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		//Capture product info
		String path="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span[1]/span[2]/span[2]";
		String price = driver.findElement(By.xpath(path)).getText();
		System.out.println(price);
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException {
		ExcelUtility eLib=new ExcelUtility();
		int rowCount = eLib.getRowCount("Mobile");
		Object [][] objArr=new Object[rowCount][2];
		
		for (int i = 0; i <rowCount; i++) {
			objArr[i][0]=eLib.getDataFromExcelFile("Mobile", i+1, 0);
			objArr[i][1]=eLib.getDataFromExcelFile("Mobile", i+1, 1);
		}
		return objArr;
	}
}
