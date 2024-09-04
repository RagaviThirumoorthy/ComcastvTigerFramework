package TestNGPracticeTest;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SampleExtentReport {

	@Test
	public void createContactTest() {

		// Spark Report Config
		ExtentSparkReporter spark = new ExtentSparkReporter("./Reports/report.html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add Env information 
		ExtentReports report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser", "Chrome");
		
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/index.php?action=index&module=Home");
		
		//Take screenshot
		TakesScreenshot ts = (TakesScreenshot)driver;
		String filePath = ts.getScreenshotAs(OutputType.BASE64);		
		
		//create Test
		ExtentTest test = report.createTest("Create Contact Test");
		test.log(Status.INFO, "Login app");
		test.log(Status.INFO, "Navigate to contact");
		test.log(Status.INFO, "Create contact");
		if("HDFC".equals("HFDC")) {
			test.log(Status.PASS, "Contact created");
		}else {
			test.log(Status.FAIL, "Contact is not created");
			test.addScreenCaptureFromBase64String(filePath, "Failed Test Case");
		}
		
		driver.quit();
		report.flush();
		
	}

}
