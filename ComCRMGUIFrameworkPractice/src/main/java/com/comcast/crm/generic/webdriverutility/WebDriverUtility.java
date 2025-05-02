package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	public void minimizeWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}
	public void waitForPageLoad(WebDriver driver,int duration) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
	}
	public void waitForElementPresent(WebDriver driver,WebElement element,int duration) {
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	public void switchToTabOnUrl(WebDriver driver,String partialUrl) {
		Set<String> set = driver.getWindowHandles();
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
        	String windowID = it.next();
        	driver.switchTo().window(windowID);
        	String actUrl = driver.getCurrentUrl();
        	 if (actUrl.contains(partialUrl)) {
				break;
			}
        }
	}
	public void switchToTabOnTitle(WebDriver driver,String partialWindowTitle) {
		Set<String> set = driver.getWindowHandles();
        Iterator<String> it = set.iterator();
        while(it.hasNext()) {
        	String windowID = it.next();
        	driver.switchTo().window(windowID);
        	String currentWindowTitle = driver.getTitle();
        	 if (currentWindowTitle.contains(partialWindowTitle)) {
				break;
			}
        }
	}
	public void switchToFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	public void switchToFrame(WebDriver driver,String nameId) {
		driver.switchTo().frame(nameId);
	}
	public void switchToFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}
	public void switchToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}
	public void switchToDefaultFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	public void switchToAlertAndAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void switchToAlertAndDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	public void select(WebElement element,String text) {
		Select sel=new Select(element);
		sel.selectByVisibleText(text);
	}
	public void select(WebElement element,int index) {
		Select sel=new Select(element);
		sel.selectByIndex(index);
	}
	public void deSelect(WebElement element,int index) {
		Select sel=new Select(element);
		sel.deselectByIndex(index);
	}
	public void deSelect(WebElement element,String text) {
		Select sel=new Select(element);
		sel.deselectByVisibleText(text);
	}
	public void deSelectAll(WebElement element) {
		Select sel=new Select(element);
		sel.deselectAll();
	}
	public boolean isMultiple(WebElement element) {
		Select sel=new Select(element);
		return sel.isMultiple();
	}
	public List<WebElement>  getOptions(WebElement element) {
		Select sel=new Select(element);
		return sel.getOptions();
	}
	public void mouseMoveOnElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).build().perform();
	}
	public void doubleClick(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.doubleClick(element).build().perform();
	}
	public void dragAndDrop(WebDriver driver,WebElement element1,WebElement element2) {
		Actions action=new Actions(driver);
		action.dragAndDrop(element1, element2).build().perform();
	}
	public void rightClick(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.contextClick(element).build().perform();
	}
	public void takeScreenShot(WebDriver driver) {
        try {
            // Casting WebDriver to TakesScreenshot
            TakesScreenshot t = (TakesScreenshot) driver;
            
            // Capture screenshot as a File object
            File src = t.getScreenshotAs(OutputType.FILE);
            
            // Define the destination path where the screenshot will be saved
            File dest = new File("./screenshot.png");
            FileUtils.copyFile(src, dest);
            // Copy the screenshot to the destination
            //FileHandler.copy(src, dest);
        } catch (IOException e) {
            // Handle potential IOException
            System.out.println("Failed to save screenshot: " + e.getMessage());
        }
    }
	public void scrollTopToBottomUsingJS(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

  
    public void scrollBottomToTopUsingJS(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0);");
    }

}