package com.vTiger.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vTiger.generic.fileUtility.ExcelUtility;
import com.vTiger.generic.fileUtility.FileUtility;
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

public class createContactWithOrganization {

	public static void main(String[] args) throws IOException, InterruptedException {

		// Read common data from Property file
		FileUtility fLib = new FileUtility();
		JavaUtility jLib = new JavaUtility();
		WebDriverUtility wLib = new WebDriverUtility();

		String BROWSER = fLib.getDataFromProperties("browser");
		String URL = fLib.getDataFromProperties("url");
		String USERNAME = fLib.getDataFromProperties("username");
		String PASSWORD = fLib.getDataFromProperties("password");

		// Read test script data from excel
		ExcelUtility eLib = new ExcelUtility();
		String lastName = eLib.getDataFromExcel("Sheet3", 7, 2) + jLib.getRandomNumber();
		String orgName = eLib.getDataFromExcel("Sheet3", 7, 3) + jLib.getRandomNumber();

		//Launch the browser
		WebDriver driver = null;

		if (BROWSER.contains("firefox")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.get(URL);
		wLib.maximizeBrowser(driver);
		wLib.waitForPageToLoad(driver);

		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		
		ContactsPage cp = new ContactsPage(driver);
		OrganizationsPage op = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		SelectOrganizationPage sop = new SelectOrganizationPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);

		//Login to application
		lp.loginApplication("admin", "admin");

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
		String parentId = driver.getWindowHandle();
		wLib.switchToChildWindowWithURL(driver, "modules=Accounts&action");

		sop.getSearchTextfield().sendKeys(orgName);
		sop.getSearchButton().click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

		//Switch to Parent window
		wLib.switchBackToParentWindow(driver, parentId);

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

		//Sign out
		ActionsUtility au = new ActionsUtility();
		au.mouseHover(driver, hp.getProfileIcon());
		Thread.sleep(1000);
		hp.getSignOutLink().click();

		//Close the browser
		driver.quit();


	}

}
