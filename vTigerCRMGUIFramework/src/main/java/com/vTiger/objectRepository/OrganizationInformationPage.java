package com.vTiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {
	
	public OrganizationInformationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerText;
	
	@FindBy(id = "dtlview_Organization Name")
	private WebElement actOrgName;
	
	@FindBy(xpath = "//span[@id='dtlview_Industry']/font")
	private WebElement actIndustry;
	
	@FindBy(xpath = "//span[@id='dtlview_Type']/font")
	private WebElement actType;
	
	@FindBy(id = "dtlview_Phone")
	private WebElement actPhoneNumber;

	public WebElement getHeaderText() {
		return headerText;
	}

	public WebElement getActOrgName() {
		return actOrgName;
	}

	public WebElement getActIndustry() {
		return actIndustry;
	}

	public WebElement getActType() {
		return actType;
	}

	public WebElement getActPhoneNumber() {
		return actPhoneNumber;
	}

}
