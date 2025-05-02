package practice.screenshottest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SampleScreenshotTest {

	@Test
	public void amazonTest() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://amazon.com");

		// step:1 create an object to EventFiringWebDriver
		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./screenshot/test.png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			System.out.println("Error :" + e.getMessage());
			e.printStackTrace();
		}
	}
}
