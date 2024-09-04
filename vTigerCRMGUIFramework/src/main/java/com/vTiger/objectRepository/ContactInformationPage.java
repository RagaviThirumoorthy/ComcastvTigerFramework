package com.vTiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInformationPage {
	
	public ContactInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "dtlview_Last Name")
	private WebElement actlastName;
	
	@FindBy(id = "dtlview_Support Start Date")
	private WebElement actStartDate;
	
	@FindBy(id = "dtlview_Support End Date")
	private WebElement actEndDate;
	
	@FindBy(id = "mouseArea_Organization Name")
	private WebElement actOrganizationName;

	public WebElement getActlastName() {
		return actlastName;
	}

	public WebElement getActStartDate() {
		return actStartDate;
	}

	public WebElement getActEndDate() {
		return actEndDate;
	}

	public WebElement getActOrganizationName() {
		return actOrganizationName;
	}
	
	
	
}
