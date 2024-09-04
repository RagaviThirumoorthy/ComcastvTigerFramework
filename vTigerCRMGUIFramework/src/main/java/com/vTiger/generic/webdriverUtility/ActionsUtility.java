package com.vTiger.generic.webdriverUtility;

import java.awt.Desktop.Action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput.ScrollOrigin;

public class ActionsUtility {
	
	public void mouseHover(WebDriver driver, WebElement target) {
		Actions act = new Actions(driver);
		act.moveToElement(target).perform();
	}
	
	public void clickOnTarget(WebDriver driver, WebElement target) {
		Actions act = new Actions(driver);
		act.click(target).perform();
	}
	
	public void rightClick(WebDriver driver, WebElement target) {
		Actions act = new Actions(driver);
		act.contextClick(target).perform();
	}
	
	public void doubleClick(WebDriver driver, WebElement target) {
		Actions act = new Actions(driver);
		act.doubleClick(target).perform();
	}
	
	public void scrollToElement(WebDriver driver, WebElement target) {
		Actions act = new Actions(driver);
		act.scrollToElement(target).perform();
	}
	
	public void scrollByAmount(WebDriver driver, int x, int y) {
		Actions act = new Actions(driver);
		act.scrollByAmount(x, y).perform();
	}
	
	public void scrollOnElement(WebDriver driver,ScrollOrigin sc, int x, int y) {
		Actions act = new Actions(driver);
		act.scrollFromOrigin(sc, x, y).perform();
	}

	public void dragAndDrop(WebDriver driver, WebElement source, WebElement target) {
		Actions act = new Actions(driver);
		act.dragAndDrop(source, target).perform();
	}
	
	public void clickAndHold(WebDriver driver, WebElement target) {
		Actions act = new Actions(driver);
		act.clickAndHold(target).perform();
	}
	
	public void release(WebDriver driver, WebElement target) {
		Actions act = new Actions(driver);
		act.release(target).perform();
	}
}
