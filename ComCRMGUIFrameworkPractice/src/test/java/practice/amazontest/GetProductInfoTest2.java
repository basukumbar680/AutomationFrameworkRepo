package practice.amazontest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetProductInfoTest2 {
	
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
	}
	
	@DataProvider
	public Object[][] getData() {
		Object [][] objArr=new Object[3][2];
		objArr[0][0]="iphone";
		objArr[0][1]="Apple iPhone 15 (128 GB) - Black";
		
		objArr[1][0]="iphone";
		objArr[1][1]="Apple iPhone 15 (128 GB) - Blue";
		
		objArr[2][0]="iphone";
		objArr[2][1]="Apple iPhone 13 (128GB) - Starlight";
		
		return objArr;
	}
}
