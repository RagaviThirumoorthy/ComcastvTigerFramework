package com.vTiger.generic.BaseTest;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.vTiger.generic.databaseUtility.DatabaseUtility;
import com.vTiger.generic.fileUtility.FileUtility;
import com.vTiger.generic.listenerUtility.UtilityClassObject;
import com.vTiger.generic.webdriverUtility.ActionsUtility;
import com.vTiger.generic.webdriverUtility.WebDriverUtility;
import com.vTiger.objectRepository.HomePage;
import com.vTiger.objectRepository.LoginPage;

@Listeners(com.vTiger.generic.listenerUtility.ListenerUtility.class)
public class BaseClass {
	
	public DatabaseUtility dbUtil = new DatabaseUtility();
	public FileUtility fUtil = new FileUtility();
	public WebDriverUtility wUtil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver = null;	
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws SQLException {
		System.out.println("Connect to DB and Configure Report");
		dbUtil.getDBConnection();
	}
	
//	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void beforeClass() throws IOException {						//String bname
		System.out.println("Launching browser");
//		String BROWSER = bname;
//		String BROWSER = fUtil.getDataFromProperties("browser");
//		String URL = fUtil.getDataFromProperties("url");
		
		String BROWSER = System.getProperty("browser",fUtil.getDataFromProperties("browser"));
		String URL = System.getProperty("url",fUtil.getDataFromProperties("url"));
		
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
		wUtil.maximizeBrowser(driver);
		wUtil.waitForPageToLoad(driver);
		sdriver = driver;
		UtilityClassObject.setDriver(driver);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeTest() throws IOException {
		System.out.println("Login Application");
//		String USERNAME = fUtil.getDataFromProperties("username");
//		String PASSWORD = fUtil.getDataFromProperties("password");
		String USERNAME = System.getProperty("UN",fUtil.getDataFromProperties("username"));
		String PASSWORD = System.getProperty("PWD",fUtil.getDataFromProperties("password"));
		LoginPage lp = new LoginPage(driver);
		lp.loginApplication(USERNAME, PASSWORD);
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterTest() throws InterruptedException {
		System.out.println("Logout");
		HomePage hp = new HomePage(driver);
		ActionsUtility au = new ActionsUtility();
		au.mouseHover(driver, hp.getProfileIcon());
		Thread.sleep(1000);
		hp.getSignOutLink().click();
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("Close browser");
		driver.quit();
	}
	
	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws SQLException {
		
		System.out.println("Close DB Connection and backup report");
		dbUtil.closeDBConnection();

	}

}
