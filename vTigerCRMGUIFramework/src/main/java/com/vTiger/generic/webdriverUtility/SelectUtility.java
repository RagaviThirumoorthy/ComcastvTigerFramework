package com.vTiger.generic.webdriverUtility;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectUtility {
	
	public void selectDropdownByIndex(WebDriver driver, WebElement dropdown, int index) {
		Select select = new Select(dropdown);
		select.selectByIndex(index);
	}
	
	public void selectDropdownByValue(WebDriver driver, WebElement dropdown, String value) {
		Select select = new Select(dropdown);
		select.selectByValue(value);
	}
	
	public void selectDropdownByVisibleText(WebDriver driver, WebElement dropdown, String text) {
		Select select = new Select(dropdown);
		select.selectByVisibleText(text);
	}
	
	public boolean isMultipleDD(WebDriver driver, WebElement dropdown) {
		Select select = new Select(dropdown);
		boolean result = select.isMultiple();
		return result;
	}
	
	public void deSelectDropdownByIndex(WebDriver driver, WebElement dropdown, int index) {
		Select select = new Select(dropdown);
		select.deselectByIndex(index);
	}
	
	public void deSelectDropdownByValue(WebDriver driver, WebElement dropdown, String value) {
		Select select = new Select(dropdown);
		select.deselectByValue(value);
	}
	
	public void deSelectDropdownByVisibleText(WebDriver driver, WebElement dropdown, String text) {
		Select select = new Select(dropdown);
		select.deselectByVisibleText(text);
	}
	
	public void deSelectAllOptions(WebDriver driver, WebElement dropdown) {
		Select select = new Select(dropdown);
		select.deselectAll();
	}
	
	public List<WebElement> getOptions(WebDriver driver, WebElement dropdown) {
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		return options;
	}
	
	public WebElement getFirstSelectedOption(WebDriver driver, WebElement dropdown) {
		Select select = new Select(dropdown);
		WebElement firstOption = select.getFirstSelectedOption();
		return firstOption;
	}
	
	public List<WebElement> getAllSelectedOptions(WebDriver driver, WebElement dropdown) {
		Select select = new Select(dropdown);
		List<WebElement> firstOption = select.getAllSelectedOptions();
		return firstOption;
	}

}
