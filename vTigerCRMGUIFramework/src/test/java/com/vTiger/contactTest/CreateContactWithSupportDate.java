package com.vTiger.contactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

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
import com.vTiger.objectRepository.HomePage;
import com.vTiger.objectRepository.LoginPage;

public class CreateContactWithSupportDate {

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
		String lastName = eLib.getDataFromExcel("Sheet3", 6, 2) + jLib.getRandomNumber();

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
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		ContactInformationPage cip = new ContactInformationPage(driver);

		//Login to application
		lp.loginApplication("admin", "admin");

		//Navigate to Contacts and create new
		hp.getContactsLink().click();
		cp.getCreateContactLink().click();

		//Getting dates		
		String startDate = jLib.getSystemDate();
		String endDate = jLib.getRequiredDate(30);

		//Enter mandatory fields
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
		if(actStartDate.contains(startDate)) {
			System.out.println(actStartDate+ " information is verified == PASS");
		}
		else {
			System.out.println(actStartDate+ " information is not verified == FAIL");
		}

		String actEndDate = cip.getActEndDate().getText();
		if(actEndDate.contains(endDate)) {
			System.out.println(actEndDate+ " information is verified == PASS");
		}
		else {
			System.out.println(actEndDate+ " information is not verified == FAIL");
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
