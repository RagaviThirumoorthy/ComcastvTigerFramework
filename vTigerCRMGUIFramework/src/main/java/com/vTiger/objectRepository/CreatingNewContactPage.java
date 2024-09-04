package com.vTiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	
	public CreatingNewContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "lastname")
	private WebElement lastNameTextfield;
	
	@FindBy(name = "support_start_date")
	private WebElement supportStartDateField;
	
	@FindBy(name = "support_end_date")
	private WebElement supportEndDateField;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement selectOrganizationLink;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement saveButton;

	public WebElement getLastNameTextfield() {
		return lastNameTextfield;
	}

	public WebElement getSupportStartDateField() {
		return supportStartDateField;
	}

	public WebElement getSupportEndDateField() {
		return supportEndDateField;
	}

	public WebElement getSelectOrganizationLink() {
		return selectOrganizationLink;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
	

}
