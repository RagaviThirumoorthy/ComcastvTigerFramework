package com.vTiger.organizationTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.vTiger.generic.BaseTest.BaseClass;
import com.vTiger.generic.fileUtility.ExcelUtility;
import com.vTiger.generic.fileUtility.FileUtility;
import com.vTiger.generic.webdriverUtility.ActionsUtility;
import com.vTiger.generic.webdriverUtility.JavaUtility;
import com.vTiger.generic.webdriverUtility.SelectUtility;
import com.vTiger.generic.webdriverUtility.WebDriverUtility;
import com.vTiger.objectRepository.CreatingNewOrganizationPage;
import com.vTiger.objectRepository.HomePage;
import com.vTiger.objectRepository.LoginPage;
import com.vTiger.objectRepository.OrganizationInformationPage;
import com.vTiger.objectRepository.OrganizationsPage;

public class CreateOrganization_Test extends BaseClass {

	@Test(groups = {"smokeTest"})
	public void createOrganization_Test() throws InterruptedException, FileNotFoundException, IOException, ParseException {

		JavaUtility jLib = new JavaUtility();

		// Read test script data from excel
		ExcelUtility eLib = new ExcelUtility();
		String orgName = eLib.getDataFromExcel("Sheet3", 1, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		OrganizationsPage op = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);

		// Navigate to Organization and create new
		hp.getOrganizationLink().click();
		op.getCreateOrganizationLink().click();

		// Enter mandatory fields
		cnop.getOrgNameTextfield().sendKeys(orgName);
		cnop.getSaveButton().click();
		Thread.sleep(2000);

		// verify header msg expected result
		String header_info = oip.getHeaderText().getText();
		Assert.assertTrue(header_info.contains(orgName));
		
//		if (header_info.contains(orgName)) {
//			System.out.println(orgName + " name present == TC Passed");
//		} else {
//			System.out.println(orgName + " name not present == TC Failed");
//		}

		// verify org name info expected result
		String actOrgName = oip.getActOrgName().getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(orgName, actOrgName);
		
//		if (actOrgName.equalsIgnoreCase(orgName)) {
//			System.out.println(orgName + " is created == PASS");
//		} else {
//			System.out.println(orgName + " is not created == FAIL");
//		}

	}

	@Test(groups= {"regressionTest"})
	public void createOrganizationWithIndustryType_Test()
			throws InterruptedException, EncryptedDocumentException, IOException {
		// Read test script data from excel
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		String orgName = eLib.getDataFromExcel("Sheet3", 2, 2) + jLib.getRandomNumber();
		String industry = eLib.getDataFromExcel("Sheet3", 2, 3);
		String type = eLib.getDataFromExcel("Sheet3", 2, 4);

		HomePage hp = new HomePage(driver);
		OrganizationsPage op = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);

		// Navigate to Organization
		hp.getOrganizationLink().click();
		op.getCreateOrganizationLink().click();

		// Enter mandatory fields
		cnop.getOrgNameTextfield().sendKeys(orgName);
		WebElement industrydd = cnop.getIndustryDropdown();
		SelectUtility sUtil = new SelectUtility();
		sUtil.selectDropdownByValue(driver, industrydd, industry);
		WebElement typedd = cnop.getTypeDropdown();
		sUtil.selectDropdownByValue(driver, typedd, type);
		cnop.getSaveButton().click();
		Thread.sleep(2000);

		// Verify the industry and type
		String actIndustry = oip.getActIndustry().getText();
		if (actIndustry.equalsIgnoreCase(industry)) {
			System.out.println(actIndustry + " information is verified == PASS");
		} else {
			System.out.println(actIndustry + " information is not verified == FAIL");
		}

		String actType = oip.getActType().getText();
		if (actType.equalsIgnoreCase(type)) {
			System.out.println(actType + " information is verified == PASS");
		} else {
			System.out.println(actType + " information is not verified == FAIL");
		}
	}

	@Test(groups= {"regressionTest"})
	public void createOrganizationWithPhoneNumber_Test() throws InterruptedException, EncryptedDocumentException, IOException {

		// Read test script data from excel
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		String orgName = eLib.getDataFromExcel("Sheet3", 3, 2) + jLib.getRandomNumber();
		String phNumber = eLib.getDataFromExcel("Sheet3", 3, 5);

		HomePage hp = new HomePage(driver);
		OrganizationsPage op = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);

		// Navigate to Organization
		hp.getOrganizationLink().click();
		op.getCreateOrganizationLink().click();

		// Enter mandatory fields
		cnop.getOrgNameTextfield().sendKeys(orgName);
		cnop.getPhoneTextfield().sendKeys(phNumber);
		cnop.getSaveButton().click();
		Thread.sleep(2000);

		// Verify the Phone Number
		String actPhNumber = oip.getActPhoneNumber().getText();
		if (actPhNumber.equalsIgnoreCase(phNumber)) {
			System.out.println(actPhNumber + " information is verified == PASS");
		} else {
			System.out.println(actPhNumber + " information is not verified == FAIL");
		}
	}

}
