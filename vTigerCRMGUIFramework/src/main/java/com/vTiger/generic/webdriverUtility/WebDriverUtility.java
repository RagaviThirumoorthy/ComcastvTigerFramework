package com.vTiger.generic.webdriverUtility;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	
	public void goTo(WebDriver driver, String url) {
		driver.get(url);
	}
	
	public void maximizeBrowser(WebDriver driver) {
		driver.manage().window().maximize();
	}	
	
	public void waitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void waitForElementToBePresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void switchToChildWindowWithURL(WebDriver driver, String url) {
		Set<String> allId = driver.getWindowHandles();

		//Switch to child window
		for(String id : allId) {
			driver.switchTo().window(id);
			if(driver.getCurrentUrl().contains(url)) {
				break;
			}
		}
	}
	
	public void switchToChildWindowWithTitle(WebDriver driver, String title) {
		Set<String> allId = driver.getWindowHandles();

		//Switch to child window
		for(String id : allId) {
			driver.switchTo().window(id);
			if(driver.getTitle().contains(title)) {
				break;
			}
		}
	}
	
	public void switchBackToParentWindow(WebDriver driver, String parentId) {
		driver.switchTo().window(parentId);
	}
	
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}
	
	public void switchToFrame(WebDriver driver, String value) {
		driver.switchTo().frame(value);
	}
	
	public void switchToFrame(WebDriver driver, WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}
	
	public Alert switchToAlert(WebDriver driver) {
		Alert alert = driver.switchTo().alert();
		return alert;
	}
	
	public void switchToAlertandAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void switchToAlertandCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void switchToAlertandSendKeys(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}
	


}
