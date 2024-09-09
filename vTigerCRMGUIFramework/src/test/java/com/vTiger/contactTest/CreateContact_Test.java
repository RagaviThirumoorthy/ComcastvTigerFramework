package com.vTiger.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.vTiger.generic.BaseTest.BaseClass;
import com.vTiger.generic.fileUtility.ExcelUtility;
import com.vTiger.generic.fileUtility.FileUtility;
import com.vTiger.generic.listenerUtility.ListenerUtility;
import com.vTiger.generic.listenerUtility.UtilityClassObject;
import com.vTiger.generic.webdriverUtility.ActionsUtility;
import com.vTiger.generic.webdriverUtility.JavaUtility;
import com.vTiger.generic.webdriverUtility.WebDriverUtility;
import com.vTiger.objectRepository.ContactInformationPage;
import com.vTiger.objectRepository.ContactsPage;
import com.vTiger.objectRepository.CreatingNewContactPage;
import com.vTiger.objectRepository.CreatingNewOrganizationPage;
import com.vTiger.objectRepository.HomePage;
import com.vTiger.objectRepository.LoginPage;
import com.vTiger.objectRepository.OrganizationInformationPage;
import com.vTiger.objectRepository.OrganizationsPage;
import com.vTiger.objectRepository.SelectOrganizationPage;

/**
 * author Ragavi
 * contains Create Contact test cases
 * 
 * 
 * 
 */
public class CreateContact_Test extends BaseClass {

	@Test(groups= {"smokeTest"})
	public void createContact_Test() throws IOException, InterruptedException {

		/* Read test script data from excel */
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		String lastName = eLib.getDataFromExcel("Sheet3", 5, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		ContactsPage cp = new ContactsPage(driver);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);

		// Navigate to Contacts and create new
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Contact page");
		hp.getContactsLink().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to Create Contact page");
		cp.getCreateContactLink().click();

		// Enter mandatory fields
		cncp.getLastNameTextfield().sendKeys(lastName);
		cncp.getSaveButton().click();
		Thread.sleep(2000);

		// verify header msg expected result
		String actLastName = cip.getActlastName().getText();
		if (actLastName.contains(lastName)) {
			System.out.println(actLastName + " information is verified == PASS");
			UtilityClassObject.getTest().log(Status.PASS, "Contact created");
		} else {
			System.out.println(actLastName + " information is not verified == FAIL");
			UtilityClassObject.getTest().log(Status.FAIL, "Contact created");
		}

	}

	@Test(groups= {"regressionTest"})
	public void createContactWithSupportDate_Test() throws EncryptedDocumentException, IOException, InterruptedException {
		
		// Read test script data from excel
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		String lastName = eLib.getDataFromExcel("Sheet3", 6, 2) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		ContactsPage cp = new ContactsPage(driver);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);

		// Navigate to Contacts and create new
		hp.getContactsLink().click();
		cp.getCreateContactLink().click();

		// Getting dates
		String startDate = jLib.getSystemDate();
		String endDate = jLib.getRequiredDate(30);

		// Enter mandatory fields
		cncp.getLastNameTextfield().sendKeys(lastName);
		WebElement supportSD = cncp.getSupportStartDateField();
		supportSD.clear();
		supportSD.sendKeys(startDate);
		WebElement supportED = cncp.getSupportEndDateField();
		supportED.clear();
		supportED.sendKeys(endDate);
		cncp.getSaveButton().click();
		Thread.sleep(2000);

		// Verify start date and end date expected result
		String actStartDate = cip.getActStartDate().getText();
		if (actStartDate.contains(startDate)) {
			System.out.println(actStartDate + " information is verified == PASS");
		} else {
			System.out.println(actStartDate + " information is not verified == FAIL");
		}

		String actEndDate = cip.getActEndDate().getText();
		if (actEndDate.contains(endDate)) {
			System.out.println(actEndDate + " information is verified == PASS");
		} else {
			System.out.println(actEndDate + " information is not verified == FAIL");
		}
	}
	
	@Test(groups= {"regressionTest"})
	public void createContactWithOrganization_Test() throws InterruptedException, EncryptedDocumentException, IOException {

		// Read test script data from excel
		ExcelUtility eLib = new ExcelUtility();
		JavaUtility jLib = new JavaUtility();
		String lastName = eLib.getDataFromExcel("Sheet3", 7, 2) + jLib.getRandomNumber();
		String orgName = eLib.getDataFromExcel("Sheet3", 7, 3) + jLib.getRandomNumber();

		HomePage hp = new HomePage(driver);
		ContactsPage cp = new ContactsPage(driver);
		OrganizationsPage op = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		SelectOrganizationPage sop = new SelectOrganizationPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);

		//Navigate to Organization and create new
		hp.getOrganizationLink().click();
		op.getCreateOrganizationLink().click();

		//Enter mandatory fields
		cnop.getOrgNameTextfield().sendKeys(orgName);
		cnop.getSaveButton().click();
		Thread.sleep(2000);

		// verify header msg expected result
		String header_info = oip.getHeaderText().getText();
		if(header_info.contains(orgName)) {
			System.out.println(orgName+ " name present == TC Passed");
		}
		else {
			System.out.println(orgName+ " name not present == TC Failed");
		}

		//Navigate to Contacts and create new
		hp.getContactsLink().click();
		cp.getCreateContactLink().click();

		//Enter mandatory fields
		cncp.getLastNameTextfield().sendKeys(lastName);
		cncp.getSelectOrganizationLink().click();

		Thread.sleep(2000);
		WebDriverUtility wUtil = new WebDriverUtility();
		String parentId = driver.getWindowHandle();
		wUtil.switchToChildWindowWithURL(driver, "modules=Accounts&action");

		sop.getSearchTextfield().sendKeys(orgName);
		sop.getSearchButton().click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

		//Switch to Parent window
		wUtil.switchBackToParentWindow(driver, parentId);

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(2000);

		// Verify organization name expected result
		String actOrgName =cip.getActOrganizationName().getText();
		System.out.println(actOrgName);
		if(actOrgName.trim().equals(orgName)) {
			System.out.println(actOrgName+ " information is verified == PASS");
		}
		else {
			System.out.println(actOrgName+ " information is not verified == FAIL");
		}
	}

}
