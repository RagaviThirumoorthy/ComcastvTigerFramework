package com.vTiger.organizationTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vTiger.generic.fileUtility.ExcelUtility;
import com.vTiger.generic.fileUtility.FileUtility;
import com.vTiger.generic.webdriverUtility.ActionsUtility;
import com.vTiger.generic.webdriverUtility.JavaUtility;
import com.vTiger.generic.webdriverUtility.WebDriverUtility;
import com.vTiger.objectRepository.CreatingNewOrganizationPage;
import com.vTiger.objectRepository.HomePage;
import com.vTiger.objectRepository.LoginPage;
import com.vTiger.objectRepository.OrganizationInformationPage;
import com.vTiger.objectRepository.OrganizationsPage;

public class createOrganizationWithPhoneNumber {

	public static void main(String[] args) throws InterruptedException, IOException {

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
		String orgName = eLib.getDataFromExcel("Sheet3", 3, 2) + jLib.getRandomNumber();
		String phNumber = eLib.getDataFromExcel("Sheet3", 3, 5);

		//Launching browser
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
		OrganizationsPage op = new OrganizationsPage(driver);
		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		OrganizationInformationPage oip = new OrganizationInformationPage(driver);

		//Login to application
		lp.loginApplication("admin", "admin");

		//Navigate to Organization
		hp.getOrganizationLink().click();
		op.getCreateOrganizationLink().click();

		//Enter mandatory fields
		cnop.getOrgNameTextfield().sendKeys(orgName);
		cnop.getPhoneTextfield().sendKeys(phNumber);		
		cnop.getSaveButton().click();
		Thread.sleep(2000);

		//Verify the Phone Number
		String actPhNumber = oip.getActPhoneNumber().getText();
		if(actPhNumber.equalsIgnoreCase(phNumber)) {
			System.out.println(actPhNumber+" information is verified == PASS");
		}else {
			System.out.println(actPhNumber+" information is not verified == FAIL");
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
