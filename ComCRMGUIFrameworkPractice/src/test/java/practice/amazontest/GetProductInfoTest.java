package practice.amazontest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName,String productName,String delivaryDate) {
		
		WebDriver driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		//Search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
		
		//Capture product info
		String path="//span[text()='"+productName+"']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span[1]/span[2]/span[2]";
		String price = driver.findElement(By.xpath(path)).getText();
		
		String path1="//span[text()='"+delivaryDate+"']/../../../../div[3]/div[1]/div[1]/div[2]/div[1]/div[2]/span/span[2]";
		String free_delivary = driver.findElement(By.xpath(path1)).getText();
		System.out.println(free_delivary);
	}
	
	@DataProvider
	public Object[][] getData() {
		Object [][] objArr=new Object[3][3];
		objArr[0][0]="iphone";
		objArr[0][1]="Apple iPhone 15 (128 GB) - Black";
		objArr[0][2]="Sat, 19 Apr";
		
		objArr[1][0]="iphone";
		objArr[1][1]="Apple iPhone 15 (128 GB) - Blue";
		objArr[1][2]="Sat, 19 Apr";
		
		objArr[2][0]="iphone";
		objArr[2][1]="Apple iPhone 13 (128GB) - Starlight";
		objArr[2][2]="Sat, 19 Apr";
		
		return objArr;
	}
}
