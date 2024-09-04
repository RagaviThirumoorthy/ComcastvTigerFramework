package com.vTiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SelectOrganizationPage {
	
	public SelectOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "search_txt")
	private WebElement searchTextfield;
	
	@FindBy(name = "search")
	private WebElement searchButton;

	public WebElement getSearchTextfield() {
		return searchTextfield;
	}

	public WebElement getSearchButton() {
		return searchButton;
	}

}
